package Main.Package;

import Main.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewPackageGUI extends PackageGUI
{
    public AddNewPackageGUI(Stage stage, Connection connection, User user)
    {
        super(stage, connection, user);
        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);

        Label zamowienieLabel = new Label("ID zamowienia");
        TextField zamowienieTextField = new TextField();
        Button utworzButton = new Button("utworz");
        EventHandler<ActionEvent> utworzButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if(zamowienieTextField.getText().length() > 0)
                {
                    try
                    {
                        Statement statement = connection.createStatement();
                        String query = "SELECT Klient FROM Zamowienia WHERE ID =" + Integer.parseInt(zamowienieTextField.getText()) + ";";
                        ResultSet resultSet = statement.executeQuery(query);
                        if(resultSet.next())
                        {
                            packageAdditionWindow(Integer.parseInt(zamowienieTextField.getText()), resultSet.getInt(1));
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        utworzButton.addEventHandler(ActionEvent.ACTION, utworzButtonHandler);

        root.getChildren().addAll(zamowienieLabel, zamowienieTextField, utworzButton);
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
}
