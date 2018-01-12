package Main;

import Main.Utils.PasswordHashingManager;
import Main.Models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;


public class LoginScreenGUI
{
    private Connection connection = null;
    private User user = null;
    private PasswordHashingManager passwordHashingManager = PasswordHashingManager.getInstance();


    private Stage stage = null;
    private Scene scene = null;
    private ApplicationMainGUI applicationMainGUI = null;
    private VBox vBox = new VBox(5);
    private TextField nameField = new TextField();
    private PasswordField passwordField = new PasswordField();
    private Button loginButton = new Button("Login");
    private HBox nameRow = new HBox(new Label("Nazwa: "), nameField);
    private HBox passwordRow = new HBox(new Label("Hasło: "), passwordField);

    EventHandler<ActionEvent> loginHandler = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            try
            {
                //Statement statement = connection.createStatement();
                //String query = "SELECT * FROM Uzytkownicy WHERE Nazwa LIKE '" + nameField.getText() +"'";
                //ResultSet resultSet = statement.executeQuery(query);
                String query = "SELECT * FROM Uzytkownicy WHERE Nazwa LIKE ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,nameField.getText());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                {
                    user = new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(4), resultSet.getString(5), resultSet.getBoolean(6),
                            resultSet.getBoolean(7), resultSet.getBoolean(8), resultSet.getBoolean(9));
                }
                System.out.println(user.getNazwa());
                String password = passwordHashingManager.hashPassword(passwordField.getText(),user.getHasloSalt());
                if(password.equals(user.getHasloHash()))
                {
                    applicationMainGUI.setUser(user);
                    stage.setScene(applicationMainGUI.getScene());
                }
                else
                {

                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    };

    public Scene getScene()
    {
        return scene;
    }

    public User getUser()
    {
        return user;
    }

    public LoginScreenGUI(Stage stage, Connection connection, ApplicationMainGUI applicationMainGUI)
    {
        this.stage = stage;
        this.connection = connection;
        this.applicationMainGUI = applicationMainGUI;

        nameField.setText("root");
        passwordField.setText("mateusz");

        nameRow.setAlignment(Pos.CENTER);
        passwordRow.setAlignment(Pos.CENTER);
        loginButton.addEventHandler(ActionEvent.ACTION, loginHandler);
        vBox.getChildren().addAll(nameRow, passwordRow, loginButton);
        vBox.setAlignment(Pos.CENTER);
        scene = new Scene(vBox);
    }
}
