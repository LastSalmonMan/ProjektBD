package Main.Storage;

import Main.ApplicationMainGUI;
import Main.Models.User;
import Main.Order.AddNewOrderGUI;
import Main.Order.ShowAllOrdersGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;

public class StorageGUI extends ApplicationMainGUI
{
    public StorageGUI(Stage stage, Connection connection, User user)
    {
        super(stage,connection);
        setUser(user);

        VBox root = new VBox(10);
        Label infoLabel = new Label("Magazyn:");
        Button wyswietlStanButton = new Button("Wyswietl stan");

        EventHandler<ActionEvent> wyswietlStanButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new ShowStorageGUI(stage,connection,user).getScene());
            }
        };
        wyswietlStanButton.addEventHandler(ActionEvent.ACTION, wyswietlStanButtonHandler);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(infoLabel, wyswietlStanButton);
        leftPane.getChildren().addAll(root);
    }
}
