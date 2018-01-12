package Main.Admin;

import Main.Admin.AdminGUI;
import Main.WarningWindow;
import Main.Models.User;
import Main.Utils.PasswordHashingManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminUserGUI extends AdminGUI
{
    public AdminUserGUI(Stage stage, Connection connection, User user)
    {
        super(stage,connection,user);
        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        Label dodajNazwaLabel = new Label("Nazwa");
        TextField dodajNazwaTextField = new TextField();
        GridPane.setConstraints(dodajNazwaLabel,0,0);
        GridPane.setConstraints(dodajNazwaTextField,1,0);
        gridPane.getChildren().addAll(dodajNazwaLabel, dodajNazwaTextField);

        Label hasloLabel = new Label("Haslo");
        TextField hasloTextField = new TextField();
        GridPane.setConstraints(hasloLabel,0,1);
        GridPane.setConstraints(hasloTextField,1,1);
        gridPane.getChildren().addAll(hasloLabel, hasloTextField);

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

        Label uprBiuroLabel = new Label("Uprawnienia Biuro");
        ObservableList<String> uprBiuroList = FXCollections.observableArrayList();
        uprBiuroList.addAll("Tak", "Nie");
        ChoiceBox uprBiuroChoiceBox = new ChoiceBox(uprBiuroList);
        GridPane.setConstraints(uprBiuroLabel,0,4);
        GridPane.setConstraints(uprBiuroChoiceBox,1,4);
        gridPane.getChildren().addAll(uprBiuroLabel, uprBiuroChoiceBox);

        Label uprMagazynLabel = new Label("Uprawnienia Magazyn");
        ObservableList<String> uprMagazynList = FXCollections.observableArrayList();
        uprMagazynList.addAll("Tak", "Nie");
        ChoiceBox uprMagazynChoiceBox = new ChoiceBox(uprMagazynList);
        GridPane.setConstraints(uprMagazynLabel,0,5);
        GridPane.setConstraints(uprMagazynChoiceBox,1,5);
        gridPane.getChildren().addAll(uprMagazynLabel, uprMagazynChoiceBox);

        Label uprPakowanieLabel = new Label("Uprawnienia Pakowanie");
        ObservableList<String> uprPakowanieList = FXCollections.observableArrayList();
        uprPakowanieList.addAll("Tak", "Nie");
        ChoiceBox uprPakowanieChoiceBox = new ChoiceBox(uprPakowanieList);
        GridPane.setConstraints(uprPakowanieLabel,0,6);
        GridPane.setConstraints(uprPakowanieChoiceBox,1,6);
        gridPane.getChildren().addAll(uprPakowanieLabel, uprPakowanieChoiceBox);

        Label uprAdminLabel = new Label("Uprawnienia Admin");
        ObservableList<String> uprAdminList = FXCollections.observableArrayList();
        uprAdminList.addAll("Tak", "Nie");
        ChoiceBox uprAdminChoiceBox = new ChoiceBox(uprAdminList);
        GridPane.setConstraints(uprAdminLabel,0,7);
        GridPane.setConstraints(uprAdminChoiceBox,1,7);
        gridPane.getChildren().addAll(uprAdminLabel, uprAdminChoiceBox);

        Button dodajButton = new Button("Dodaj");
        EventHandler<ActionEvent> dodajButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (uprBiuroChoiceBox.getSelectionModel().getSelectedItem().toString().length() > 0 && uprMagazynChoiceBox.getSelectionModel().getSelectedItem().toString().length() > 0
                        && uprPakowanieChoiceBox.getSelectionModel().getSelectedItem().toString().length() > 0 && uprAdminChoiceBox.getSelectionModel().getSelectedItem().toString().length() > 0)
                {
                    if (dodajNazwaLabel.getText().length() > 3 && hasloTextField.getText().length() > 3)
                    {
                        try
                        {
                            String salt = PasswordHashingManager.getInstance().getNewSalt();
                            String query = "INSERT INTO Uzytkownicy VALUES(?,?,?,?,?,?,?,?,?);";
                            PreparedStatement statement = connection.prepareStatement(query);
                            statement.setString(1, dodajNazwaTextField.getText());
                            statement.setString(2, PasswordHashingManager.getInstance().hashPassword(hasloTextField.getText(), salt));
                            statement.setString(3, salt);
                            statement.setString(4, imieTextField.getText());
                            statement.setString(5, nazwiskoTextField.getText());
                            if (uprBiuroChoiceBox.getSelectionModel().getSelectedItem().equals("Tak"))
                            {
                                statement.setBoolean(6, true);
                            }
                            else
                                statement.setBoolean(6,false);
                            if (uprMagazynChoiceBox.getSelectionModel().getSelectedItem().equals("Tak"))
                            {
                                statement.setBoolean(7, true);
                            }
                            else
                                statement.setBoolean(7,false);
                            if (uprPakowanieChoiceBox.getSelectionModel().getSelectedItem().equals("Tak"))
                            {
                                statement.setBoolean(8, true);
                            }
                            else
                                statement.setBoolean(8,false);
                            if (uprAdminChoiceBox.getSelectionModel().getSelectedItem().equals("Tak"))
                            {
                                statement.setBoolean(9, true);
                            }
                            else
                                statement.setBoolean(9,false);
                            statement.executeQuery();
                            dodajNazwaTextField.clear();
                            hasloTextField.clear();
                            imieTextField.clear();
                            nazwiskoTextField.clear();
                        }
                        catch (SQLException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                        new WarningWindow("Błędne wypełnienie pola");
                }
                else
                    new WarningWindow("Wybierz opcje");
            }
        };
        dodajButton.addEventHandler(ActionEvent.ACTION, dodajButtonHandler);
        GridPane.setConstraints(dodajButton,1,8);
        gridPane.getChildren().addAll(dodajButton);

        Label usunNazwaLabel = new Label("Nazwa");
        TextField usunNazwaTextField = new TextField();
        GridPane.setConstraints(usunNazwaLabel,0,9);
        GridPane.setConstraints(usunNazwaTextField,1,9);
        gridPane.getChildren().addAll(usunNazwaLabel, usunNazwaTextField);

        Button usunButton = new Button("Usuń");
        EventHandler<ActionEvent> usunButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if(usunNazwaTextField.getText().length() > 0)
                {
                    try
                    {
                        String query = "DELETE FROM Uzytkownicy WHERE Nazwa = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1,usunNazwaTextField.getText());
                        statement.executeQuery();
                        usunNazwaTextField.clear();
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        usunButton.addEventHandler(ActionEvent.ACTION, usunButtonHandler);
        GridPane.setConstraints(usunButton,1,10);
        gridPane.getChildren().addAll(usunButton);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        rightPane.setAlignment(Pos.CENTER);
        rightPane.getChildren().addAll(gridPane);
    }
}
