package Main.Package;

import Main.ApplicationMainGUI;
import Main.Models.Package;
import Main.Models.PackingView;
import Main.Models.User;
import Main.Storage.ShowStorageGUI;
import Main.WarningWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PackingGUI extends ApplicationMainGUI
{
    private PackingView pack;
    private TableView<PackingView> table;

    private ObservableList<PackingView> getPackages()
    {
        ObservableList<PackingView> packages = FXCollections.observableArrayList();
        try
        {
            int id = 0;
            Statement statement = connection.createStatement();
            String query = "SELECT Zamowienie FROM PakowanieView LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next())
            {
                id = resultSet.getInt(1);
            }

            query = "SELECT * FROM PakowanieView WHERE Zamowienie =" + id + ";";
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                pack = new PackingView(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getString(5),
                        resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11));
                packages.addAll(pack);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return packages;
    }

    public PackingGUI(Stage stage, Connection connection, User user)
    {
        super(stage,connection);
        setUser(user);

        VBox root = new VBox(10);
        Label infoLabel = new Label("Dział Pakowania:");
        Button pobierzButton = new Button("Pobierz przesyłkę");

        HBox row1 = new HBox(5);
        row1.setAlignment(Pos.CENTER);
        Label idZamowieniaLabel = new Label("ID Zamowienia:");
        Label idZamowieniaInfoLabel = new Label();
        idZamowieniaInfoLabel.setTextFill(Color.RED);
        Label iloscPaczekLabel = new Label("Ilosc paczek:");
        Label iloscPaczekInfoLabel = new Label();
        iloscPaczekInfoLabel.setTextFill(Color.RED);
        row1.getChildren().addAll(idZamowieniaLabel, idZamowieniaInfoLabel, iloscPaczekLabel, iloscPaczekInfoLabel);

        HBox row2 = new HBox(5);
        row2.setAlignment(Pos.CENTER);
        Label imieLabel = new Label("Imie");
        Label imieInfoLabel = new Label();
        imieInfoLabel.setTextFill(Color.RED);
        Label nazwiskoLabel = new Label("Nazwisko:");
        Label nazwiskoInfoLabel = new Label();
        nazwiskoInfoLabel.setTextFill(Color.RED);
        row2.getChildren().addAll(imieLabel, nazwiskoLabel, imieInfoLabel, nazwiskoInfoLabel);

        HBox row3 = new HBox(5);
        row3.setAlignment(Pos.CENTER);
        Label adresLabel = new Label("Adres:");
        Label adresInfoLabel = new Label();
        adresInfoLabel.setTextFill(Color.RED);
        row3.getChildren().addAll(adresLabel, adresInfoLabel);

        HBox row4 = new HBox(5);
        row4.setAlignment(Pos.CENTER);
        Label kodPocztowyLabel = new Label("Kod pocztowy:");
        Label kodPocztowyInfoLabel = new Label();
        kodPocztowyInfoLabel.setTextFill(Color.RED);
        Label miejscowoscLabel = new Label("Miejscowosc:");
        Label miejscowoscInfoLabel = new Label();
        miejscowoscInfoLabel.setTextFill(Color.RED);
        row4.getChildren().addAll(kodPocztowyLabel, kodPocztowyInfoLabel, miejscowoscLabel, miejscowoscInfoLabel);

        HBox row5 = new HBox(5);
        row5.setAlignment(Pos.CENTER);
        Label dostawcaLabel = new Label("Dostawca:");
        Label dostawcaInfoLabel = new Label();
        dostawcaInfoLabel.setTextFill(Color.RED);
        Label typDostawyLabel = new Label("Typ dostawy:");
        Label typDostawyInfoLabel = new Label();
        typDostawyInfoLabel.setTextFill(Color.RED);
        row5.getChildren().addAll(dostawcaLabel, dostawcaInfoLabel, typDostawyLabel, typDostawyInfoLabel);

        HBox row6 = new HBox(5);
        row6.setAlignment(Pos.CENTER);
        Button zakonczButton = new Button("Zakończ");
        EventHandler<ActionEvent> zakonczButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if(pack != null)
                {
                    try
                    {
                        Statement statement = connection.createStatement();
                        String query = "UPDATE Przesylki SET StatusPrzesylki = 'spakowana' WHERE IDZamowienia = " + pack.getIdZamowienia() + ";";
                        statement.executeQuery(query);
                        idZamowieniaInfoLabel.setText("");
                        iloscPaczekInfoLabel.setText("");
                        imieInfoLabel.setText("");
                        nazwiskoInfoLabel.setText("");
                        adresInfoLabel.setText("");
                        kodPocztowyInfoLabel.setText("");
                        miejscowoscInfoLabel.setText("");
                        dostawcaInfoLabel.setText("");
                        typDostawyInfoLabel.setText("");
                        pack = null;
                        table.getItems().clear();
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        zakonczButton.addEventHandler(ActionEvent.ACTION, zakonczButtonHandler);
        Button zakonczPobierzButton = new Button("Zakończ i pobierz nową");
        EventHandler<ActionEvent> zakonczPobierzButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if(pack != null)
                {
                    try
                    {
                        Statement statement = connection.createStatement();
                        String query = "UPDATE Przesylki SET StatusPrzesylki = 'pakowana' WHERE IDZamowienia = " + pack.getIdZamowienia() + ";";
                        statement.executeQuery(query);
                        idZamowieniaInfoLabel.setText("");
                        iloscPaczekInfoLabel.setText("");
                        imieInfoLabel.setText("");
                        nazwiskoInfoLabel.setText("");
                        adresInfoLabel.setText("");
                        kodPocztowyInfoLabel.setText("");
                        miejscowoscInfoLabel.setText("");
                        dostawcaInfoLabel.setText("");
                        typDostawyInfoLabel.setText("");
                        pack = null;
                        table.getItems().clear();
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
                ObservableList<PackingView> list = getPackages();
                if(list.size() > 0)
                {
                    pack = list.get(0);

                    idZamowieniaInfoLabel.setText(new Integer(pack.getIdZamowienia()).toString());
                    iloscPaczekInfoLabel.setText(new Integer(pack.getIloscPaczek()).toString());
                    imieInfoLabel.setText(pack.getImie());
                    nazwiskoInfoLabel.setText(pack.getNazwisko());
                    adresInfoLabel.setText(pack.getAdres());
                    kodPocztowyInfoLabel.setText(pack.getKodPocztowy());
                    miejscowoscInfoLabel.setText(pack.getMiejscowosc());
                    dostawcaInfoLabel.setText(pack.getDostawca());
                    typDostawyInfoLabel.setText(pack.getTypDostawy());

                    table.setItems(list);

                    try
                    {
                        Statement statement = connection.createStatement();
                        String query = "UPDATE Przesylki SET StatusPrzesylki = 'pakowana' WHERE IDZamowienia = " + pack.getIdZamowienia() + ";";
                        statement.executeQuery(query);
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }


                }
                else
                    new WarningWindow("Brak niespakowanych przesyłek");
            }
        };
        zakonczPobierzButton.addEventHandler(ActionEvent.ACTION, zakonczPobierzButtonHandler);
        row6.getChildren().addAll(zakonczButton, zakonczPobierzButton);

        TableColumn<PackingView, String> firstColumn = new TableColumn<>("Nazwa");
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        TableColumn<PackingView, String> secondColumn = new TableColumn<>("Ilosc");
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
        table = new TableView<>();
        table.getColumns().addAll(firstColumn, secondColumn);

        EventHandler<ActionEvent> pobierzButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                ObservableList<PackingView> list = getPackages();
                if(list.size() > 0)
                {
                    pack = list.get(0);

                    idZamowieniaInfoLabel.setText(new Integer(pack.getIdZamowienia()).toString());
                    iloscPaczekInfoLabel.setText(new Integer(pack.getIloscPaczek()).toString());
                    imieInfoLabel.setText(pack.getImie());
                    nazwiskoInfoLabel.setText(pack.getNazwisko());
                    adresInfoLabel.setText(pack.getAdres());
                    kodPocztowyInfoLabel.setText(pack.getKodPocztowy());
                    miejscowoscInfoLabel.setText(pack.getMiejscowosc());
                    dostawcaInfoLabel.setText(pack.getDostawca());
                    typDostawyInfoLabel.setText(pack.getTypDostawy());

                    table.setItems(list);

                    try
                    {
                        Statement statement = connection.createStatement();
                        String query = "UPDATE Przesylki SET StatusPrzesylki = 'pakowana' WHERE IDZamowienia = " + pack.getIdZamowienia() + ";";
                        statement.executeQuery(query);
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                    new WarningWindow("Brak niespakowanych przesyłek");
            }
        };
        pobierzButton.addEventHandler(ActionEvent.ACTION, pobierzButtonHandler);

        rightPane.setAlignment(Pos.CENTER);
        rightPane.getChildren().addAll(row1, row2, row3, row4, row5,row6, table);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(infoLabel, pobierzButton);
        leftPane.getChildren().addAll(root);
    }
}
