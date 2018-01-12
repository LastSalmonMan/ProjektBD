package Main;

import javafx.application.Application;
import javafx.stage.Stage;


import java.sql.*;


public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/OnlineStore-DB", "root", "kalisz12");

            primaryStage.setMaxHeight(500);
            primaryStage.setMinHeight(500);
            primaryStage.setMaxWidth(1000);
            primaryStage.setMinWidth(1000);

            ApplicationMainGUI applicationMainGUI = new ApplicationMainGUI(primaryStage, connection);
            LoginScreenGUI loginScreenGUI = new LoginScreenGUI(primaryStage, connection, applicationMainGUI);
            primaryStage.setTitle("OnlineStore-DB");
            primaryStage.setScene(loginScreenGUI.getScene());
            primaryStage.show();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
