package Main.Admin;

import Main.Models.User;
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
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminPackageGUI extends AdminGUI
{
    public AdminPackageGUI(Stage stage, Connection connection, User user)
    {
        super(stage,connection,user);
        setUser(user);

        Label usunPrzesylkeLabel = new Label("Usun przesylke");
        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER);
        Label idZamowieniaLabel = new Label("ID Zamowienia:");
        TextField idZamowieniaTextField = new TextField();
        row.getChildren().addAll(idZamowieniaLabel, idZamowieniaTextField);
        Button usunButton = new Button("Usuń");
        EventHandler<ActionEvent> usunButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                //TODO
                if(idZamowieniaTextField.getText().length() > 0)
                {
                    try
                    {
                        String query = "DELETE FROM Przesylki WHERE IDZamowienia = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1,idZamowieniaTextField.getText());
                        statement.executeQuery();
                        idZamowieniaTextField.clear();
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        usunButton.addEventHandler(ActionEvent.ACTION, usunButtonHandler);

        rightPane.setAlignment(Pos.CENTER);
        rightPane.getChildren().addAll(usunPrzesylkeLabel, row,usunButton);
    }
}
