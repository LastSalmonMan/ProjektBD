package Main.Admin;

import Main.Models.Product;
import Main.Models.ProductLog;
import Main.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.*;

public class StorageLogsGUI extends AdminGUI
{
    private TableView<ProductLog> table;
    private ObservableList<ProductLog> getLogs()
    {
        ObservableList<ProductLog> logs = FXCollections.observableArrayList();
        try
        {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM MagazynLogiView;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                ProductLog product = new ProductLog(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getString(5));
                logs.add(product);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return logs;
    }

    public StorageLogsGUI(Stage stage, Connection connection, User user)
    {
        super(stage,connection,user);
        setUser(user);

        TableColumn<ProductLog, String> firstColumn = new TableColumn<>("Akcja");
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("akcja"));
        TableColumn<ProductLog, String> secondColumn = new TableColumn<>("Nazwa");
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        TableColumn<ProductLog, String> thirdColumn = new TableColumn<>("Stara Wartosc");
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("staraWartosc"));
        TableColumn<ProductLog, String> fourthColumn = new TableColumn<>("Nowa Wartosc");
        fourthColumn.setCellValueFactory(new PropertyValueFactory<>("nowaWartosc"));
        TableColumn<ProductLog, String> fifthColumn = new TableColumn<>("Data Akcji");
        fifthColumn.setCellValueFactory(new PropertyValueFactory<>("dataAkcji"));
        table = new TableView<>();
        table.setMinWidth(600);
        //table.setMaxWidth(640);

        table.setItems(getLogs());
        table.getColumns().addAll(firstColumn, secondColumn, thirdColumn, fourthColumn, fifthColumn);
        ScrollPane root = new ScrollPane(table);

        rightPane.setAlignment(Pos.CENTER);
        rightPane.getChildren().addAll(root);
    }
}
