package Main.Client;

import Main.Models.User;
import Main.WarningWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.sql.*;

public class AddNewClientGUI extends ClientGUI
{
    public AddNewClientGUI(Stage stage, Connection connection, User user)
    {
        super(stage, connection, user);

        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);

        HBox row1 = new HBox(10);
        row1.setAlignment(Pos.CENTER);
        Label imieLabel = new Label("Imie");
        TextField imieTextField = new TextField();
        row1.getChildren().addAll(imieLabel, imieTextField);

        HBox row2 = new HBox(10);
        row2.setAlignment(Pos.CENTER);
        Label nazwiskoLabel = new Label("Nazwisko");
        TextField nazwiskoTextField = new TextField();
        row2.getChildren().addAll(nazwiskoLabel, nazwiskoTextField);

        HBox row3 = new HBox(10);
        row3.setAlignment(Pos.CENTER);
        Label adresLabel = new Label("Adres");
        TextField adresTextField = new TextField();
        row3.getChildren().addAll(adresLabel, adresTextField);

        HBox row4 = new HBox(10);
        row4.setAlignment(Pos.CENTER);
        Label kodPocztowyLabel = new Label("Kod pocztowy");
        TextField kodPocztowyTextField = new TextField();
        row4.getChildren().addAll(kodPocztowyLabel, kodPocztowyTextField);

        HBox row5 = new HBox(10);
        row5.setAlignment(Pos.CENTER);
        Label miejscowoscLabel = new Label("Miejscowosc");
        TextField miejscowoscTextField = new TextField();
        row5.getChildren().addAll(miejscowoscLabel, miejscowoscTextField);

        HBox row6 = new HBox(10);
        row6.setAlignment(Pos.CENTER);
        Label telefonLabel = new Label("Telefon");
        TextField telefonTextField = new TextField();
        row6.getChildren().addAll(telefonLabel, telefonTextField);

        HBox row7 = new HBox(10);
        row7.setAlignment(Pos.CENTER);
        Label mailLabel = new Label("Mail");
        TextField mailTextField = new TextField();
        row7.getChildren().addAll(mailLabel, mailTextField);

        HBox row8 = new HBox(10);
        row8.setAlignment(Pos.CENTER);
        Label firmaLabel = new Label("Firma");
        row8.getChildren().addAll(firmaLabel);

        HBox row9 = new HBox(10);
        row9.setAlignment(Pos.CENTER);
        Label NIPLabel = new Label("NIP");
        TextField NIPTextField = new TextField();
        row9.getChildren().addAll(NIPLabel, NIPTextField);

        HBox row10 = new HBox(10);
        row10.setAlignment(Pos.CENTER);
        Label nazwaFirmyLabel = new Label("Nazwa firmy");
        TextField nazwaFirmyTextField = new TextField();
        row10.getChildren().addAll(nazwaFirmyLabel, nazwaFirmyTextField);

        EventHandler<ActionEvent> pobierzHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                additionWindow(NIPTextField, nazwaFirmyTextField);
            }
        };

        HBox row11 = new HBox(10);
        row11.setAlignment(Pos.CENTER);
        Button pobierzButton = new Button("Pobierz");
        pobierzButton.addEventHandler(ActionEvent.ACTION, pobierzHandler);
        row11.getChildren().addAll(pobierzButton);

        Button dodajButton = new Button("Dodaj");
        EventHandler<ActionEvent> dodajButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    String NIP = "'" + NIPTextField.getText() + "'";
                    if(NIP.length() == 2)
                    {
                        NIP = "null";
                    }
                    if(imieTextField.getText().length()>0 && nazwiskoTextField.getText().length()>0 && adresTextField.getText().length()>0 && kodPocztowyTextField.getText().length()>0 &&
                            miejscowoscTextField.getText().length()>0 && telefonTextField.getText().length()>0 && mailTextField.getText().length()>0)
                    {
                        if(kodPocztowyTextField.getText().length() == 5)
                        {
                            if(telefonTextField.getText().length() == 9)
                            {
                                //TODO zastap regexem
                                if(mailTextField.getText().length() > 0)
                                {
                                    Statement statement = connection.createStatement();
                                    String query = "INSERT INTO Klienci VALUES (null,'" + imieTextField.getText() + "','" + nazwiskoTextField.getText() + "','" + adresTextField.getText() + "','" + kodPocztowyTextField.getText() + "','" + miejscowoscTextField.getText() + "'," + NIP + ",'" + telefonTextField.getText() + "','" + mailTextField.getText() + "')";
                                    statement.executeQuery(query);

                                    imieTextField.clear();
                                    nazwiskoTextField.clear();
                                    adresTextField.clear();
                                    kodPocztowyTextField.clear();
                                    miejscowoscTextField.clear();
                                    NIPTextField.clear();
                                    nazwaFirmyTextField.clear();
                                    telefonTextField.clear();
                                    mailTextField.clear();
                                }
                            }
                            else
                                new WarningWindow("Bledny numer telefonu");
                        }
                        else
                            new WarningWindow("Bledny kod pocztowy");
                    }
                    else
                        new WarningWindow("Puste pole");
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        };
        dodajButton.addEventHandler(ActionEvent.ACTION, dodajButtonHandler);

        root.getChildren().addAll(row1, row2, row3, row4, row5, row6,row7,row8,row9, row10, row11, dodajButton);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.getChildren().addAll(root);
    }

    public void additionWindow(TextField NIPTextField, TextField nazwaFirmyTextField)
    {
        Stage window = new Stage();
        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);
        root.setMaxHeight(250);
        root.setMinHeight(250);
        root.setMaxWidth(300);
        root.setMinWidth(300);
        window.setResizable(false);

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
                                new WarningWindow("Taki klient juz istnieje");
                                //e.printStackTrace();
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

        Button szukajButton = new Button("Szukaj");
        EventHandler<ActionEvent> szukajButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    Statement statement = connection.createStatement();
                    String query = "SELECT * FROM Firmy WHERE NIP LIKE '%" + nipTextField.getText() + "%';";
                    ResultSet resultSet = statement.executeQuery(query);

                    while(resultSet.next())
                    {
                        nipTextField.setText(resultSet.getString(1));
                        nazwaTextField.setText(resultSet.getString(2));
                        adresTextField.setText(resultSet.getString(3));
                        kodPocztowyTextField.setText(resultSet.getString(4));
                        miejscowoscTextField.setText(resultSet.getString(5));
                        System.out.println(resultSet.getString(2));
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }

            }
        };
        szukajButton.addEventHandler(ActionEvent.ACTION, szukajButtonHandler);

        HBox row6 = new HBox(10);
        row6.setAlignment(Pos.CENTER);
        row6.getChildren().addAll(dodajButton,szukajButton);

        Button pobierzButton = new Button("Pobierz");
        EventHandler<ActionEvent> pobierzButtonHadler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                NIPTextField.setText(nipTextField.getText());
                nazwaFirmyTextField.setText(nazwaTextField.getText());
                window.close();
            }
        };
        pobierzButton.addEventHandler(ActionEvent.ACTION, pobierzButtonHadler);

        root.getChildren().addAll(row1, row2, row3, row4, row5, row6, pobierzButton);

        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}
