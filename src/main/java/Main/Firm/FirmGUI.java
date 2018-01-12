package Main.Firm;

import Main.ApplicationMainGUI;
import Main.Models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.sql.Connection;

public class FirmGUI extends ApplicationMainGUI
{
    public FirmGUI(Stage stage, Connection connection, User user)
    {
        super(stage, connection);
        setUser(user);
        VBox root = new VBox(10);
        Label infoLabel = new Label("Firmy:");
        Button pokazWszystkieButton = new Button("Pokaż wszystkie");
        Button dodajNowaButton = new Button("Dodaj nową");

        EventHandler<ActionEvent> dodajNowaButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new AddNewFirmGUI(stage,connection,user).getScene());
            }
        };
        dodajNowaButton.addEventHandler(ActionEvent.ACTION, dodajNowaButtonHandler);

        EventHandler<ActionEvent> pokazWszystkieButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new ShowAllFirmGUI(stage,connection,user).getScene());
            }
        };
        pokazWszystkieButton.addEventHandler(ActionEvent.ACTION, pokazWszystkieButtonHandler);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(infoLabel, pokazWszystkieButton, dodajNowaButton);
        leftPane.getChildren().addAll(root);
    }
}
