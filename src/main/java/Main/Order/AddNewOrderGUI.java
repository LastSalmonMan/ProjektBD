package Main.Order;

import Main.Client.AddNewClientGUI;
import Main.Models.Product;
import Main.Models.User;
import Main.Products.AddNewProductGUI;
import Main.WarningWindow;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewOrderGUI extends OrderGUI
{
    private Stage stage;
    private Connection connection;
    private Integer klientId = null;
    private Double wartosc = 0.0;
    private SimpleStringProperty wartoscLabelProperty = new SimpleStringProperty();
    private TableView<Product> table;

    public AddNewOrderGUI(Stage stage, Connection connection, User user)
    {
        super(stage,connection,user);
        this.stage = stage;
        this.connection = connection;
        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Label klientLabel = new Label("Klient:");
        Button pobierzButton = new Button("Pobierz");

        HBox hBox = new HBox(pobierzButton);
        //hBox.setAlignment(Pos.CENTER);
        GridPane.setConstraints(klientLabel, 0,0);
        GridPane.setConstraints(hBox, 1,0);
        gridPane.getChildren().addAll(klientLabel,hBox);

        Label imieLabel = new Label("Imie");
        TextField imieTextField = new TextField();
        GridPane.setConstraints(imieLabel,0,1);
        GridPane.setConstraints(imieTextField,1,1);
        gridPane.getChildren().addAll(imieLabel, imieTextField);

        Label nazwiskoLabel = new Label("Nazwisko");
        TextField nazwiskoTextField = new TextField();
        GridPane.setConstraints(nazwiskoLabel,0,2);
        GridPane.setConstraints(nazwiskoTextField,1,2);
        gridPane.getChildren().addAll(nazwiskoLabel, nazwiskoTextField);

        Label adresLabel = new Label("Adres");
        TextField adresTextField = new TextField();
        GridPane.setConstraints(adresLabel,0,3);
        GridPane.setConstraints(adresTextField,1,3);
        gridPane.getChildren().addAll(adresLabel, adresTextField);

        Label typRachunkuLabel = new Label("Typ Rachunku");
        ObservableList<String> typRachunkuList = FXCollections.observableArrayList();
        typRachunkuList.addAll("paragon", "FVAT");
        ChoiceBox typRachunkuChoiceBox = new ChoiceBox(typRachunkuList);
        GridPane.setConstraints(typRachunkuLabel,0,5);
        GridPane.setConstraints(typRachunkuChoiceBox,1,5);
        gridPane.getChildren().addAll(typRachunkuLabel, typRachunkuChoiceBox);

        Label wysylkaLabel = new Label("Wysyłka");
        ObservableList<String> wysylkaList = FXCollections.observableArrayList();
        wysylkaList.addAll("Tak", "Nie");
        ChoiceBox wysylkaChoiceBox = new ChoiceBox(wysylkaList);
        GridPane.setConstraints(wysylkaLabel,0,6);
        GridPane.setConstraints(wysylkaChoiceBox,1,6);
        gridPane.getChildren().addAll(wysylkaLabel, wysylkaChoiceBox);

        Label wartoscLabel = new Label("Wartosc");
        Label wartoscLabel2 = new Label(wartosc.toString());
        GridPane.setConstraints(wartoscLabel,0,7);
        GridPane.setConstraints(wartoscLabel2,1,7);
        gridPane.getChildren().addAll(wartoscLabel, wartoscLabel2);
        wartoscLabel2.textProperty().bindBidirectional(wartoscLabelProperty);

        EventHandler<ActionEvent> pobierzButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                clientAdditonWindow(imieTextField, nazwiskoTextField, adresTextField);
            }
        };
        pobierzButton.addEventHandler(ActionEvent.ACTION, pobierzButtonHandler);

        ObservableList<Product> list = FXCollections.observableArrayList();
        table = new TableView<>();
        table.setItems(list);
        TableColumn<Product, String> secondColumn = new TableColumn<>("Nazwa");
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        TableColumn<Product, String> thirdColumn = new TableColumn<>("Cena");
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("cena"));
        TableColumn<Product, String> fourthColumn = new TableColumn<>("Ilosc");
        fourthColumn.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
        table.getColumns().addAll(secondColumn, thirdColumn, fourthColumn);
        table.visibleProperty().setValue(true);
        ScrollPane scrollPane = new ScrollPane(table);

        Button dodajPrzedmiotButton = new Button("Dodaj przedmiot");
        EventHandler<ActionEvent> dodajPrzedmiotButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                productAdditionWindow(list);
            }
        };
        dodajPrzedmiotButton.addEventHandler(ActionEvent.ACTION, dodajPrzedmiotButtonHandler);
        GridPane.setConstraints(dodajPrzedmiotButton,1,8);
        gridPane.getChildren().addAll(dodajPrzedmiotButton);

        Button usunPrzedmiotButton = new Button("Usuń zaznaczony przedmiot");
        EventHandler<ActionEvent> usunPrzedmiotButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                wartosc = wartosc - (table.getSelectionModel().getSelectedItem().getCena() * table.getSelectionModel().getSelectedItem().getIlosc());
                wartoscLabelProperty.setValue(wartosc.toString());
                list.remove(table.getSelectionModel().getSelectedItem());
            }
        };
        usunPrzedmiotButton.addEventHandler(ActionEvent.ACTION, usunPrzedmiotButtonHandler);
        GridPane.setConstraints(usunPrzedmiotButton,1,9);
        gridPane.getChildren().addAll(usunPrzedmiotButton);

        Button dodajZamowienieButton = new Button("Dodaj zamowienie");
        EventHandler<ActionEvent> dodajZamowienieButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (klientId != null)
                {
                    try
                    {
                        boolean wysylka;
                        if(wysylkaChoiceBox.getSelectionModel().getSelectedItem().equals("Tak"))
                        {
                            wysylka = true;
                        }
                        else
                        {
                            wysylka = false;
                        }
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Date date = new Date();
                        Statement statement = connection.createStatement();
                        String query = "INSERT INTO Zamowienia VALUES (null,'" + dateFormat.format(date) + "'," + wartosc.toString() + "," + klientId.toString() + ",'"
                                + typRachunkuChoiceBox.getSelectionModel().getSelectedItem().toString() + "'," + wysylka + ",'w trakcie');";
                        statement.executeQuery(query);

                        query = "SELECT ID FROM Zamowienia WHERE DataZamowienia = '" + dateFormat.format(date) + "' AND Klient = '" + klientId.toString() + "';";
                        ResultSet resultSet = statement.executeQuery(query);
                        int idZamowienia = 0;
                        while(resultSet.next())
                        {
                            idZamowienia = resultSet.getInt(1);
                        }
                        if(idZamowienia > 0)
                        {
                            for (Product product : list)
                            {
                                query = "INSERT INTO ZamowieniaToPrzedmioty VALUES (" + idZamowienia + "," + product.getId() + "," + product.getIlosc() + ");";
                                statement.executeQuery(query);
                            }
                        }
                        if(wysylka)
                        {
                            packageAdditionWindow(idZamowienia, klientId);
                        }
                        imieTextField.clear();
                        nazwiskoTextField.clear();
                        adresTextField.clear();
                        list.clear();
                        wartosc = 0.0;
                        wartoscLabelProperty.setValue(wartosc.toString());
                        klientId = null;
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        dodajZamowienieButton.addEventHandler(ActionEvent.ACTION, dodajZamowienieButtonHandler);
        GridPane.setConstraints(dodajZamowienieButton,1,10);
        gridPane.getChildren().addAll(dodajZamowienieButton);

        root.getChildren().addAll(gridPane, scrollPane);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.getChildren().addAll(root);
    }

    private void packageAdditionWindow(int idZamowienia, int klientId)
    {
        Stage window = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setMaxHeight(500);
        gridPane.setMinHeight(500);
        gridPane.setMaxWidth(400);
        gridPane.setMinWidth(400);

        Label iloscPaczekLabel = new Label("Ilosc Paczek");
        TextField iloscPaczekTextField = new TextField();
        GridPane.setConstraints(iloscPaczekLabel,0,0);
        GridPane.setConstraints(iloscPaczekTextField,1,0);
        gridPane.getChildren().addAll(iloscPaczekLabel, iloscPaczekTextField);

        Label imieLabel = new Label("Imie");
        TextField imieTextField = new TextField();
        GridPane.setConstraints(imieLabel,0,2);
        GridPane.setConstraints(imieTextField,1,2);
        gridPane.getChildren().addAll(imieLabel, imieTextField);

        Label nazwiskoLabel = new Label("Nazwisko");
        TextField nazwiskoTextField = new TextField();
        GridPane.setConstraints(nazwiskoLabel,0,3);
        GridPane.setConstraints(nazwiskoTextField,1,3);
        gridPane.getChildren().addAll(nazwiskoLabel, nazwiskoTextField);

        Label adresLabel = new Label("Adres");
        TextField adresTextField = new TextField();
        GridPane.setConstraints(adresLabel,0,4);
        GridPane.setConstraints(adresTextField,1,4);
        gridPane.getChildren().addAll(adresLabel, adresTextField);

        Label kodPocztowyLabel = new Label("Kod pocztowy");
        TextField kodPocztowyTextField = new TextField();
        GridPane.setConstraints(kodPocztowyLabel,0,5);
        GridPane.setConstraints(kodPocztowyTextField,1,5);
        gridPane.getChildren().addAll(kodPocztowyLabel, kodPocztowyTextField);

        Label miejscowoscLabel = new Label("Miejscowosc");
        TextField miejscowoscTextField = new TextField();
        GridPane.setConstraints(miejscowoscLabel,0,6);
        GridPane.setConstraints(miejscowoscTextField,1,6);
        gridPane.getChildren().addAll(miejscowoscLabel, miejscowoscTextField);

        Label telefonLabel = new Label("Telefon");
        TextField telefonTextField = new TextField();
        GridPane.setConstraints(telefonLabel,0,7);
        GridPane.setConstraints(telefonTextField,1,7);
        gridPane.getChildren().addAll(telefonLabel, telefonTextField);

        Label mailLabel = new Label("Mail");
        TextField mailTextField = new TextField();
        GridPane.setConstraints(mailLabel,0,8);
        GridPane.setConstraints(mailTextField,1,8);
        gridPane.getChildren().addAll(mailLabel, mailTextField);

        Label dodatkoweInformacjeLabel = new Label("Dodatkowe informacje");
        TextField dodatkoweInformacjeTextField = new TextField();
        GridPane.setConstraints(dodatkoweInformacjeLabel,0,9);
        GridPane.setConstraints(dodatkoweInformacjeTextField,1,9);
        gridPane.getChildren().addAll(dodatkoweInformacjeLabel, dodatkoweInformacjeTextField);

        Label dostawcaLabel = new Label("Dostawca");
        ObservableList<String> dostawcaList = FXCollections.observableArrayList();
        dostawcaList.addAll("GLS", "Poczta Polska", "INPost");
        ChoiceBox dostawcaChoiceBox = new ChoiceBox(dostawcaList);
        GridPane.setConstraints(dostawcaLabel,0,10);
        GridPane.setConstraints(dostawcaChoiceBox,1,10);
        gridPane.getChildren().addAll(dostawcaLabel,dostawcaChoiceBox);

        Label typDostawyLabel = new Label("Typ dostawy");
        ObservableList<String> typDostawyList = FXCollections.observableArrayList();
        typDostawyList.addAll("normalna przesylka", "pobranie");
        ChoiceBox typDostawyChoiceBox = new ChoiceBox(typDostawyList);
        GridPane.setConstraints(typDostawyLabel,0,11);
        GridPane.setConstraints(typDostawyChoiceBox,1,11);
        gridPane.getChildren().addAll(typDostawyLabel, typDostawyChoiceBox);

        Button dodajPrzesylkeButton = new Button("Dodaj przesyłkę");
        EventHandler<ActionEvent> dodajPrzesylkeButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                //TODO sprawdzanie wypelnienia pol
                if(iloscPaczekTextField.getText().length()>0)
                {
                    try
                    {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Date date = new Date();
                        Statement statement = connection.createStatement();
                        String query = "INSERT INTO Przesylki VALUES ("+idZamowienia+"," + Integer.parseInt(iloscPaczekTextField.getText()) + ", 'do spakowania', '" + imieTextField.getText() + "','"
                                + nazwiskoTextField.getText() + "','" + adresTextField.getText() + "','" + kodPocztowyTextField.getText() + "','" + miejscowoscTextField.getText() + "','"
                                + telefonTextField.getText() + "','" + mailTextField.getText() + "','" + dodatkoweInformacjeTextField.getText() + "','"
                                + dateFormat.format(date) + "','" + dostawcaChoiceBox.getSelectionModel().getSelectedItem() + "','" + typDostawyChoiceBox.getSelectionModel().getSelectedItem() + "');";
                        statement.executeQuery(query);
                        window.close();
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        dodajPrzesylkeButton.addEventHandler(ActionEvent.ACTION, dodajPrzesylkeButtonHandler);
        GridPane.setConstraints(dodajPrzesylkeButton,1,12);
        gridPane.getChildren().addAll(dodajPrzesylkeButton);

        Button pobierzButton = new Button("Pobierz klienta z zamówienia");
        EventHandler<ActionEvent> pobierzButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    Statement statement = connection.createStatement();
                    String query = "SELECT Imie, Nazwisko, Adres, KodPocztowy, Miejscowosc, Telefon, Mail FROM Klienci WHERE ID = " + klientId + ";";
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next())
                    {
                        imieTextField.setText(resultSet.getString(1));
                        nazwiskoTextField.setText(resultSet.getString(2));
                        adresTextField.setText(resultSet.getString(3));
                        kodPocztowyTextField.setText(resultSet.getString(4));
                        miejscowoscTextField.setText(resultSet.getString(5));
                        telefonTextField.setText(resultSet.getString(6));
                        mailTextField.setText(resultSet.getString(7));
                    }

                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }

            }
        };
        pobierzButton.addEventHandler(ActionEvent.ACTION, pobierzButtonHandler);
        GridPane.setConstraints(pobierzButton,1,1);
        gridPane.getChildren().addAll(pobierzButton);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        Scene secondScene = new Scene(gridPane);

        VBox firstSceneRoot = new VBox(10);
        firstSceneRoot.setAlignment(Pos.CENTER);
        HBox row2 = new HBox(10);
        row2.setAlignment(Pos.CENTER);
        Label infoLabel = new Label("Czy chcesz utworzyć nową przesyłkę dla tego zamówienia?");
        Button takButton = new Button("Tak");
        EventHandler<ActionEvent> takButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                window.setScene(secondScene);
            }
        };
        takButton.addEventHandler(ActionEvent.ACTION, takButtonHandler);
        Button nieButton = new Button("Nie");
        EventHandler<ActionEvent> nieButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                window.close();
            }
        };
        nieButton.addEventHandler(ActionEvent.ACTION, nieButtonHandler);
        row2.getChildren().addAll(takButton,nieButton);
        firstSceneRoot.getChildren().addAll(infoLabel,row2);
        firstSceneRoot.setMaxHeight(100);
        firstSceneRoot.setMinHeight(100);
        firstSceneRoot.setMaxWidth(400);
        firstSceneRoot.setMinWidth(400);
        Scene firstScene = new Scene(firstSceneRoot);
        window.setScene(firstScene);
        window.show();
    }

    private void productAdditionWindow(ObservableList<Product> list)
    {
        final Product product = new Product(0,"",0);

        Stage window = new Stage();
        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);
        root.setMaxHeight(170);
        root.setMinHeight(170);
        root.setMaxWidth(300);
        root.setMinWidth(300);

        HBox row1 = new HBox(10);
        row1.setAlignment(Pos.CENTER);
        Label nazwaLabel = new Label("Nazwa");
        TextField nazwaTextField = new TextField();
        row1.getChildren().addAll(nazwaLabel, nazwaTextField);

        HBox row2 = new HBox(10);
        row2.setAlignment(Pos.CENTER);
        Label idLabel = new Label("ID");
        TextField idTextField = new TextField();
        row2.getChildren().addAll(idLabel, idTextField);

        HBox row3 = new HBox(10);
        row3.setAlignment(Pos.CENTER);
        Label iloscLabel = new Label("Ilosc");
        TextField iloscTextField = new TextField();
        row3.getChildren().addAll(iloscLabel, iloscTextField);

        Button szukajButton = new Button("Szukaj");
        EventHandler<ActionEvent> szukajButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {

                    Statement statement = connection.createStatement();
                    String query = "SELECT * FROM Przedmioty WHERE id LIKE '%" + idTextField.getText() + "%' AND Nazwa LIKE '%" + nazwaTextField.getText() + "%';";
                    ResultSet resultSet = statement.executeQuery(query);

                    /*
                    String query = "SELECT * FROM Przedmioty WHERE id LIKE %?% AND Nazwa LIKE %?%;";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, idTextField.getText());
                    preparedStatement.setString(2, nazwaTextField.getText());
                    ResultSet resultSet = preparedStatement.executeQuery();*/
                    while (resultSet.next())
                    {
                        product.setId(resultSet.getInt(1));
                        product.setNazwa(resultSet.getString(2));
                        product.setCena(resultSet.getFloat(3));
                        nazwaTextField.setText(product.getNazwa());
                        idTextField.setText(new Integer(product.getId()).toString());
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        };
        szukajButton.addEventHandler(ActionEvent.ACTION, szukajButtonHandler);

        Button dodajButton = new Button("Dodaj do zamówienia");
        EventHandler<ActionEvent> dodajButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if(!product.getNazwa().equals(""))
                {
                    wartosc = wartosc + (Integer.parseInt(iloscTextField.getText()) * product.getCena());
                    wartoscLabelProperty.setValue(wartosc.toString());
                    Product tmp = new Product(product.getId(), product.getNazwa(), product.getCena());
                    tmp.setIlosc(Integer.parseInt(iloscTextField.getText()));
                    list.addAll(tmp);

                    int ilosc = 0;
                    try
                    {
                        Statement statement = connection.createStatement();
                        String query = "SELECT Ilosc FROM Magazyn WHERE Przedmiot = " + tmp.getId() + ";";
                        ResultSet resultSet = statement.executeQuery(query);
                        while (resultSet.next())
                        {
                            ilosc = resultSet.getInt(1);
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                    if((ilosc - tmp.getIlosc()) < 0)
                    {
                        new WarningWindow("Po dodaniu zamowienia\nstan magazynowy bedzie\n< 0");
                    }

                    product.setNazwa("");
                    nazwaTextField.clear();
                    idTextField.clear();
                    iloscTextField.clear();
                }
            }
        };
        dodajButton.addEventHandler(ActionEvent.ACTION, dodajButtonHandler);

        root.getChildren().addAll(row2, row1, row3, szukajButton, dodajButton);
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    private void clientAdditonWindow(TextField imieField, TextField nazwiskoField, TextField adresField)
    {
        Stage window = new Stage();
        window.setResizable(false);

        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);
        root.setMaxHeight(400);
        root.setMinHeight(400);
        root.setMaxWidth(300);
        root.setMinWidth(300);

        HBox row1 = new HBox(10);
        row1.setAlignment(Pos.CENTER);
        Label imieLabel = new Label("Imie");
        TextField imieTextField = new TextField();
        row1.getChildren().addAll(imieLabel, imieTextField);

        HBox row2 = new HBox(10);
        row2.setAlignment(Pos.CENTER);
        Label nazwiskoLabel = new Label("Nazwisko");
        TextField nazwiskoTextField = new TextField();
        row2.getChildren().addAll(nazwiskoLabel, nazwiskoTextField);

        HBox row3 = new HBox(10);
        row3.setAlignment(Pos.CENTER);
        Label adresLabel = new Label("Adres");
        TextField adresTextField = new TextField();
        row3.getChildren().addAll(adresLabel, adresTextField);

        HBox row4 = new HBox(10);
        row4.setAlignment(Pos.CENTER);
        Label kodPocztowyLabel = new Label("Kod pocztowy");
        TextField kodPocztowyTextField = new TextField();
        row4.getChildren().addAll(kodPocztowyLabel, kodPocztowyTextField);

        HBox row5 = new HBox(10);
        row5.setAlignment(Pos.CENTER);
        Label miejscowoscLabel = new Label("Miejscowosc");
        TextField miejscowoscTextField = new TextField();
        row5.getChildren().addAll(miejscowoscLabel, miejscowoscTextField);

        HBox row6 = new HBox(10);
        row6.setAlignment(Pos.CENTER);
        Label telefonLabel = new Label("Telefon");
        TextField telefonTextField = new TextField();
        row6.getChildren().addAll(telefonLabel, telefonTextField);

        HBox row7 = new HBox(10);
        row7.setAlignment(Pos.CENTER);
        Label mailLabel = new Label("Mail");
        TextField mailTextField = new TextField();
        row7.getChildren().addAll(mailLabel, mailTextField);

        HBox row8 = new HBox(10);
        row8.setAlignment(Pos.CENTER);
        Label firmaLabel = new Label("Firma");
        row8.getChildren().addAll(firmaLabel);

        HBox row9 = new HBox(10);
        row9.setAlignment(Pos.CENTER);
        Label NIPLabel = new Label("NIP");
        TextField NIPTextField = new TextField();
        row9.getChildren().addAll(NIPLabel, NIPTextField);

        HBox row10 = new HBox(10);
        row10.setAlignment(Pos.CENTER);
        Label nazwaFirmyLabel = new Label("Nazwa firmy");
        TextField nazwaFirmyTextField = new TextField();
        row10.getChildren().addAll(nazwaFirmyLabel, nazwaFirmyTextField);

        EventHandler<ActionEvent> pobierzHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                new AddNewClientGUI(stage,connection,getUser()).additionWindow(NIPTextField, nazwaFirmyTextField);
            }
        };

        HBox row11 = new HBox(10);
        row11.setAlignment(Pos.CENTER);
        Button pobierzButton = new Button("Pobierz Firmę");
        pobierzButton.addEventHandler(ActionEvent.ACTION, pobierzHandler);
        row11.getChildren().addAll(pobierzButton);

        Button dodajButton = new Button("Dodaj i pobierz");
        EventHandler<ActionEvent> dodajButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    String NIP = "'" + NIPTextField.getText() + "'";
                    if(NIP.length() == 2)
                    {
                        NIP = "null";
                    }
                    if(imieTextField.getText().length()>0 && nazwiskoTextField.getText().length()>0 && adresTextField.getText().length()>0 && kodPocztowyTextField.getText().length()>0 &&
                            miejscowoscTextField.getText().length()>0 && telefonTextField.getText().length()>0 && mailTextField.getText().length()>0)
                    {
                        if(kodPocztowyTextField.getText().length() == 5)
                        {
                            if(telefonTextField.getText().length() == 9)
                            {
                                //TODO zastap regexem
                                if(mailTextField.getText().length() > 0)
                                {
                                    Statement statement = connection.createStatement();
                                    String query = "INSERT INTO Klienci VALUES (null,'" + imieTextField.getText() + "','" + nazwiskoTextField.getText() + "','" + adresTextField.getText() + "','" + kodPocztowyTextField.getText() + "','" + miejscowoscTextField.getText() + "'," + NIP + ",'" + telefonTextField.getText() + "','" + mailTextField.getText() + "')";
                                    statement.executeQuery(query);
                                    /*
                                    imieTextField.clear();
                                    nazwiskoTextField.clear();
                                    adresTextField.clear();
                                    kodPocztowyTextField.clear();
                                    miejscowoscTextField.clear();
                                    NIPTextField.clear();
                                    nazwaFirmyTextField.clear();
                                    telefonTextField.clear();
                                    mailTextField.clear();*/

                                    query = "SELECT ID FROM Klienci WHERE Imie LIKE '" + imieTextField.getText() +"' AND Nazwisko LIKE '" + nazwiskoTextField.getText()
                                            +"' AND Adres LIKE '" + adresTextField.getText() + "';";
                                    ResultSet resultSet = statement.executeQuery(query);
                                    while (resultSet.next())
                                    {
                                        klientId = resultSet.getInt(1);
                                    }
                                    System.out.println(klientId);
                                    imieField.setText(imieTextField.getText());
                                    nazwiskoField.setText(nazwiskoTextField.getText());
                                    adresField.setText(adresTextField.getText());
                                    window.close();
                                }
                            }
                            else
                                new WarningWindow("Bledny numer telefonu");
                        }
                        else
                            new WarningWindow("Bledny kod pocztowy");
                    }
                    else
                        new WarningWindow("Puste pole");
                }
                catch (SQLException e)
                {
                    new WarningWindow("Taki klient juz istnieje");
                    //e.printStackTrace();
                }
            }
        };
        dodajButton.addEventHandler(ActionEvent.ACTION, dodajButtonHandler);

        HBox row12 = new HBox(10);
        row12.setAlignment(Pos.CENTER);
        Button szukajButton = new Button("Szukaj");
        EventHandler<ActionEvent> szukajButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    Statement statement = connection.createStatement();
                    String query = "SELECT Klienci.ID, Klienci.Imie, Klienci.Nazwisko, Klienci.Adres, Klienci.KodPocztowy, Klienci.Miejscowosc, Klienci.Firma, " +
                            "Klienci.Telefon, Klienci.Mail, Firmy.Nazwa FROM Klienci LEFT JOIN Firmy ON Klienci.firma = Firmy.NIP WHERE Imie LIKE '%" + imieTextField.getText() +"%' AND Nazwisko LIKE '%" + nazwiskoTextField.getText()
                            +"%' AND Klienci.Adres LIKE '%" + adresTextField.getText() + "%';";
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next())
                    {
                        klientId = resultSet.getInt(1);
                        imieTextField.setText(resultSet.getString(2));
                        nazwiskoTextField.setText(resultSet.getString(3));
                        adresTextField.setText(resultSet.getString(4));
                        kodPocztowyTextField.setText(resultSet.getString(5));
                        miejscowoscTextField.setText(resultSet.getString(6));
                        NIPTextField.setText(resultSet.getString(7));
                        telefonTextField.setText(resultSet.getString(8));
                        mailTextField.setText(resultSet.getString(9));
                        nazwaFirmyTextField.setText(resultSet.getString(10));
                        System.out.println(klientId);
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        };
        szukajButton.addEventHandler(ActionEvent.ACTION, szukajButtonHandler);
        row12.getChildren().addAll(szukajButton, dodajButton);

        Button pobierzKlientaButton = new Button("Pobierz klienta");
        EventHandler<ActionEvent> pobierzKlientaButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                imieField.setText(imieTextField.getText());
                nazwiskoField.setText(nazwiskoTextField.getText());
                adresField.setText(adresTextField.getText());
                try
                {
                    Statement statement = connection.createStatement();
                    String query = "SELECT ID FROM Klienci WHERE Imie LIKE '" + imieTextField.getText() +"' AND Nazwisko LIKE '" + nazwiskoTextField.getText()
                            +"' AND Adres LIKE '" + adresTextField.getText() + "';";
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next())
                    {
                        klientId = resultSet.getInt(1);
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }

                window.close();
            }
        };
        pobierzKlientaButton.addEventHandler(ActionEvent.ACTION, pobierzKlientaButtonHandler);

        root.getChildren().addAll(row1, row2, row3, row4, row5, row6,row7,row8,row9, row10, row11, row12, pobierzKlientaButton);
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}
