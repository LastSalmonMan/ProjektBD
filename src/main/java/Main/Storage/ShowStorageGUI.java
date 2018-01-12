package Main.Storage;

import Main.Models.Product;
import Main.Models.Storage;
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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowStorageGUI extends StorageGUI
{
    TableView<Storage> table;

    public ObservableList<Storage> getStorage()
    {
        ObservableList<Storage> storage = FXCollections.observableArrayList();
        try
        {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM MagazynView;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                Storage store = new Storage(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3));
                storage.addAll(store);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return storage;
    }

    public ShowStorageGUI(Stage stage, Connection connection, User user)
    {
        super(stage,connection,user);
        rightPane.setAlignment(Pos.CENTER);

        TableColumn<Storage, String> firstColumn = new TableColumn<>("ID");
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Storage, String> secondColumn = new TableColumn<>("Nazwa");
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        TableColumn<Storage, String> thirdColumn = new TableColumn<>("Ilosc");
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
        table = new TableView<>();
        table.setItems(getStorage());
        table.getColumns().addAll(firstColumn, secondColumn, thirdColumn);
        ScrollPane root = new ScrollPane(table);


        Button przyjmijButton = new Button("Przyjmij");
        TextField przyjmijTextField = new TextField();
        EventHandler<ActionEvent> przyjmijButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    Statement statement = connection.createStatement();
                    String query = "UPDATE Magazyn SET Ilosc = " + (table.getSelectionModel().getSelectedItem().getIlosc() + Integer.parseInt(przyjmijTextField.getText()) +
                            " WHERE Przedmiot = " + table.getSelectionModel().getSelectedItem().getId() + ";");
                    statement.executeQuery(query);
                    table.setItems(getStorage());
                    przyjmijTextField.clear();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        };
        przyjmijButton.addEventHandler(ActionEvent.ACTION, przyjmijButtonHandler);

        Button wydajButton = new Button("Wydaj");
        TextField wydajTextField = new TextField();
        EventHandler<ActionEvent> wydajButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    Statement statement = connection.createStatement();
                    String query = "UPDATE Magazyn SET Ilosc = " + (table.getSelectionModel().getSelectedItem().getIlosc() - Integer.parseInt(wydajTextField.getText()) +
                            " WHERE Przedmiot = " + table.getSelectionModel().getSelectedItem().getId() + ";");
                    statement.executeQuery(query);
                    table.setItems(getStorage());
                    wydajTextField.clear();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        };
        wydajButton.addEventHandler(ActionEvent.ACTION, wydajButtonHandler);

        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER);
        row.getChildren().addAll(przyjmijButton,przyjmijTextField, wydajButton, wydajTextField);
        rightPane.getChildren().addAll(row , root);
    }
}
