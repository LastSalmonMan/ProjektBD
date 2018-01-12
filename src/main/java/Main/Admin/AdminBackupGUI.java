package Main.Admin;

import Main.Models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminBackupGUI extends AdminGUI
{
    public AdminBackupGUI(Stage stage, Connection connection, User user)
    {
        super(stage,connection,user);

        Label nazwaLabel = new Label("Nazwa pliku");
        TextField nazwaTextField = new TextField();
        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER);
        Button zapiszButton = new Button("Zapisz");
        EventHandler<ActionEvent> zapiszButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                //TODO tworzenie kopii bazy danych
            }
        };
        zapiszButton.addEventHandler(ActionEvent.ACTION, zapiszButtonHandler);
        Button wczytajButton = new Button("Zapisz");
        EventHandler<ActionEvent> wczytajButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                //TODO wczytanie kopii bazy danych
            }
        };
        wczytajButton.addEventHandler(ActionEvent.ACTION, wczytajButtonHandler);
        row.getChildren().addAll(zapiszButton, wczytajButton);

        rightPane.setAlignment(Pos.CENTER);
        rightPane.getChildren().addAll(nazwaLabel,nazwaTextField,row);
    }
}
