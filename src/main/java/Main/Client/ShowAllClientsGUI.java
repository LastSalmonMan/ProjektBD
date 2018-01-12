package Main.Client;

import Main.Models.ClientJoinFirm;
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

public class ShowAllClientsGUI extends ClientGUI
{
    private TableView<ClientJoinFirm> table;

    private ObservableList<ClientJoinFirm> getClients()
    {
        ObservableList<ClientJoinFirm> clients = FXCollections.observableArrayList();
        try
        {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Klienci LEFT JOIN Firmy ON Klienci.Firma = Firmy.NIP;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                ClientJoinFirm client = new ClientJoinFirm(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),
                        resultSet.getString(10), resultSet.getString(11), resultSet.getString(12), resultSet.getString(13), resultSet.getString(14));
                clients.add(client);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return clients;
    }

    public ShowAllClientsGUI(Stage stage, Connection connection, User user)
    {
        super(stage, connection, user);


        TableColumn<ClientJoinFirm, String> firstColumn = new TableColumn<>("Imie");
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("imie"));
        TableColumn<ClientJoinFirm, String> secondColumn = new TableColumn<>("Nazwisko");
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        TableColumn<ClientJoinFirm, String> thirdColumn = new TableColumn<>("Adres");
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("adres"));
        TableColumn<ClientJoinFirm, String> fourthColumn = new TableColumn<>("Kod pocztowy");
        fourthColumn.setCellValueFactory(new PropertyValueFactory<>("kodPocztowy"));
        TableColumn<ClientJoinFirm, String> fifthColumn = new TableColumn<>("Miejscowosc");
        fifthColumn.setCellValueFactory(new PropertyValueFactory<>("miejscowosc"));
        TableColumn<ClientJoinFirm, String> sixthColumn = new TableColumn<>("Telefon");
        sixthColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        TableColumn<ClientJoinFirm, String> seventhColumn = new TableColumn<>("Mail");
        seventhColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        TableColumn<ClientJoinFirm, String> eighthColumn = new TableColumn<>("NIP");
        eighthColumn.setCellValueFactory(new PropertyValueFactory<>("NIP"));
        TableColumn<ClientJoinFirm, String> ninthColumn = new TableColumn<>("Nazwa firmy");
        ninthColumn.setCellValueFactory(new PropertyValueFactory<>("firmaNazwa"));

        table = new TableView<>();
        table.setMinWidth(760);
        //table.setMaxWidth(640);
        table.setItems(getClients());
        table.getColumns().addAll(firstColumn, secondColumn, thirdColumn, fourthColumn, fifthColumn, sixthColumn, seventhColumn, eighthColumn, ninthColumn);
        ScrollPane root = new ScrollPane(table);

        rightPane.getChildren().addAll(root);
    }
}
