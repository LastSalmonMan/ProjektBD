package Main.Firm;

import Main.Models.Firm;
import Main.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ShowAllFirmGUI extends FirmGUI
{
    private TableView<Firm> table;

    public ObservableList<Firm> getFirms()
    {
        ObservableList<Firm> firms = FXCollections.observableArrayList();
        try
        {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM WszystkieFirmy;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                Firm firm = new Firm(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5));
                firms.add(firm);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return firms;
    }

    public ShowAllFirmGUI(Stage stage, Connection connection, User user)
    {
        super(stage,connection,user);

        TableColumn<Firm, String> firstColumn = new TableColumn<>("NIP");
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("NIP"));
        firstColumn.setMinWidth(80);
        firstColumn.setMaxWidth(80);
        TableColumn<Firm, String> secondColumn = new TableColumn<>("Nazwa");
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        TableColumn<Firm, String> thirdColumn = new TableColumn<>("Adres");
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("adres"));
        TableColumn<Firm, String> fourthColumn = new TableColumn<>("Kod Pocztowy");
        fourthColumn.setCellValueFactory(new PropertyValueFactory<>("kodPocztowy"));
        fourthColumn.setMinWidth(100);
        fourthColumn.setMaxWidth(100);
        TableColumn<Firm, String> fifthColumn = new TableColumn<>("Miejscowosc");
        fifthColumn.setMinWidth(100);
        fifthColumn.setMaxWidth(100);
        fifthColumn.setCellValueFactory(new PropertyValueFactory<>("miejscowosc"));

        table = new TableView<>();
        table.setMinWidth(640);
        table.setItems(getFirms());
        table.getColumns().addAll(firstColumn, secondColumn, thirdColumn, fourthColumn, fifthColumn);

        ScrollPane root = new ScrollPane(table);

        rightPane.getChildren().addAll(root);
    }
}
