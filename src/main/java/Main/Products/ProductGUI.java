package Main.Products;

import Main.ApplicationMainGUI;
import Main.Client.AddNewClientGUI;
import Main.Client.ShowAllClientsGUI;
import Main.Models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;

public class ProductGUI extends ApplicationMainGUI
{
    public ProductGUI(Stage stage, Connection connection, User user)
    {
        super(stage, connection);
        setUser(user);

        VBox root = new VBox(10);
        Label infoLabel = new Label("Produkty:");
        Button pokazWszystkichButton = new Button("Pokaż wszystkie");
        Button dodajNowegoButton = new Button("Dodaj nowy");

        EventHandler<ActionEvent> dodajNowegoButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new AddNewProductGUI(stage,connection,user).getScene());
            }
        };
        dodajNowegoButton.addEventHandler(ActionEvent.ACTION, dodajNowegoButtonHandler);

        EventHandler<ActionEvent> pokazWszystkichButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new ShowAllProductsGUI(stage,connection,user).getScene());
            }
        };
        pokazWszystkichButton.addEventHandler(ActionEvent.ACTION, pokazWszystkichButtonHandler);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(infoLabel, pokazWszystkichButton, dodajNowegoButton);
        leftPane.getChildren().addAll(root);
    }
}
