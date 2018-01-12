package Main.Admin;

import Main.Main;
import Main.Models.User;
import Main.ApplicationMainGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;

public class AdminGUI extends ApplicationMainGUI
{
    public AdminGUI(Stage stage, Connection connection, User user)
    {
        super(stage,connection);
        setUser(user);

        VBox root = new VBox(10);
        Label infoLabel = new Label("Admin Panel:");
        Button uzytkownicyButton = new Button("Użytkownicy");
        Button przesylkiButton = new Button("Przesyłki");
        EventHandler<ActionEvent> przesylkiButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new AdminPackageGUI(stage,connection,user).getScene());
            }
        };
        przesylkiButton.addEventHandler(ActionEvent.ACTION, przesylkiButtonHandler);

        EventHandler<ActionEvent> uzytkownicyButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new AdminUserGUI(stage,connection,user).getScene());
            }
        };
        uzytkownicyButton.addEventHandler(ActionEvent.ACTION, uzytkownicyButtonHandler);

        Button backupButton = new Button("Backup");
        EventHandler<ActionEvent> backupButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new AdminBackupGUI(stage,connection,user).getScene());
            }
        };
        backupButton.addEventHandler(ActionEvent.ACTION, backupButtonHandler);
        Button magazynLogiButton = new Button("Magazyn Logi");
        EventHandler<ActionEvent> magazynLogiButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new StorageLogsGUI(stage,connection,user).getScene());
            }
        };
        magazynLogiButton.addEventHandler(ActionEvent.ACTION, magazynLogiButtonHandler);

        Button przesylkiArchiwumButton = new Button("Przesyłki Archiwum");
        EventHandler<ActionEvent> przesylkiArchiwumButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new PackageArchiveGUI(stage,connection,user).getScene());
            }
        };
        przesylkiArchiwumButton.addEventHandler(ActionEvent.ACTION, przesylkiArchiwumButtonHandler);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(infoLabel, uzytkownicyButton, przesylkiButton, backupButton, magazynLogiButton, przesylkiArchiwumButton);
        leftPane.getChildren().addAll(root);
    }
}
