package Main.Order;

import Main.ApplicationMainGUI;
import Main.Models.User;
import Main.Products.AddNewProductGUI;
import Main.Products.ShowAllProductsGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;

public class OrderGUI extends ApplicationMainGUI
{
    public OrderGUI(Stage stage, Connection connection, User user)
    {
        super(stage, connection);
        setUser(user);

        VBox root = new VBox(10);
        Label infoLabel = new Label("Zamówienia:");
        Button pokazWszystkieButton = new Button("Pokaż wszystkie");
        Button dodajNoweButton = new Button("Dodaj nowe");

        EventHandler<ActionEvent> dodajNowegoButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new AddNewOrderGUI(stage,connection,user).getScene());
            }
        };
        dodajNoweButton.addEventHandler(ActionEvent.ACTION, dodajNowegoButtonHandler);

        EventHandler<ActionEvent> pokazWszystkichButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new ShowAllOrdersGUI(stage,connection,user).getScene());
            }
        };
        pokazWszystkieButton.addEventHandler(ActionEvent.ACTION, pokazWszystkichButtonHandler);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(infoLabel, pokazWszystkieButton, dodajNoweButton);
        leftPane.getChildren().addAll(root);
    }
}
