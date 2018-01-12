package Main.Products;

import Main.Models.User;
import Main.WarningWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddNewProductGUI extends ProductGUI
{
    public AddNewProductGUI(Stage stage, Connection connection, User user)
    {
        super(stage,connection,user);
        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);

        HBox row1 = new HBox(10);
        row1.setAlignment(Pos.CENTER);
        Label nazwaLabel = new Label("Nazwa");
        TextField nazwaTextField = new TextField();
        row1.getChildren().addAll(nazwaLabel, nazwaTextField);

        HBox row2 = new HBox(10);
        row2.setAlignment(Pos.CENTER);
        Label cenaLabel = new Label("Cena");
        TextField cenaTextField = new TextField();
        row2.getChildren().addAll(cenaLabel, cenaTextField);

        Button dodajButton = new Button("Dodaj");
        EventHandler<ActionEvent> dodajButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    if(nazwaTextField.getText().length() > 0 && cenaTextField.getText().length() > 0)
                    {
                        Statement statement = connection.createStatement();
                        String query = "INSERT INTO Przedmioty VALUES (null,'"+ nazwaTextField.getText() +"',"+ cenaTextField.getText()+ ")";
                        statement.executeQuery(query);
                        nazwaTextField.clear();
                        cenaTextField.clear();
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        };
        dodajButton.addEventHandler(ActionEvent.ACTION, dodajButtonHandler);

        root.getChildren().addAll(row1, row2, dodajButton);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.getChildren().addAll(root);
    }
}
