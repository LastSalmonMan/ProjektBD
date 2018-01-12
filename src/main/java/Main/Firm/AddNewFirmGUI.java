package Main.Firm;

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

public class AddNewFirmGUI extends FirmGUI
{
    public AddNewFirmGUI(Stage stage, Connection connection, User user)
    {
        super(stage,connection,user);

        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);

        HBox row1 = new HBox(10);
        row1.setAlignment(Pos.CENTER);
        Label nipLabel = new Label(" NIP: ");
        TextField nipTextField = new TextField();
        row1.getChildren().addAll(nipLabel, nipTextField);

        HBox row2 = new HBox(10);
        row2.setAlignment(Pos.CENTER);
        Label nazwaLabel = new Label(" Nazwa: ");
        TextField nazwaTextField = new TextField();
        row2.getChildren().addAll(nazwaLabel,nazwaTextField);

        HBox row3 = new HBox(10);
        row3.setAlignment(Pos.CENTER);
        Label adresLabel = new Label(" Adres: ");
        TextField adresTextField = new TextField();
        row3.getChildren().addAll(adresLabel,adresTextField);

        HBox row4 = new HBox(10);
        row4.setAlignment(Pos.CENTER);
        Label kodPocztowyLabel = new Label(" Kod Pocztowy: ");
        TextField kodPocztowyTextField = new TextField();
        row4.getChildren().addAll(kodPocztowyLabel,kodPocztowyTextField);

        HBox row5 = new HBox(10);
        row5.setAlignment(Pos.CENTER);
        Label miejscowoscLabel = new Label(" Miejscowosc: ");
        TextField miejscowoscTextField = new TextField();
        row5.getChildren().addAll(miejscowoscLabel,miejscowoscTextField);

        Button dodajButton = new Button("Dodaj");
        EventHandler<ActionEvent> dodajButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (nipTextField.getText().length()>0 && kodPocztowyTextField.getText().length() > 0 && nazwaTextField.getText().length() >0 &&
                        adresTextField.getText().length() > 0 && miejscowoscTextField.getText().length()>0)
                {
                    if (nipTextField.getText().length() == 10)
                    {
                        if (kodPocztowyTextField.getText().length() == 5)
                        {
                            try
                            {
                                Statement statement = connection.createStatement();
                                String query = "INSERT INTO Firmy VALUES ('" + nipTextField.getText() + "','" + nazwaTextField.getText() + "','" + adresTextField.getText() + "','" + kodPocztowyTextField.getText() + "','" + miejscowoscTextField.getText() + "');";
                                statement.executeQuery(query);
                                nipTextField.clear();
                                nazwaTextField.clear();
                                adresTextField.clear();
                                kodPocztowyTextField.clear();
                                miejscowoscTextField.clear();
                            }
                            catch (SQLException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        else
                            new WarningWindow("Niepoprawny kod pocztowy.");
                    }
                    else
                        new WarningWindow("Niepoprawny NIP");
                }
                else
                    new WarningWindow("Puste pole");
            }
        };
        dodajButton.addEventHandler(ActionEvent.ACTION, dodajButtonHandler);

        root.getChildren().addAll(row1, row2, row3, row4, row5, dodajButton);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.getChildren().addAll(root);
    }
}
