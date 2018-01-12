package Main.Order;

import Main.Models.OrderView;
import Main.Models.Product;
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

public class ShowAllOrdersGUI extends OrderGUI
{
    private TableView<OrderView> table;

    private ObservableList<OrderView> getOrders()
    {
        ObservableList<OrderView> orders = FXCollections.observableArrayList();
        try
        {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM ZamowieniaView;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                OrderView order = new OrderView(resultSet.getInt(1), resultSet.getString(2),resultSet.getInt(3), resultSet.getFloat(4), resultSet.getString(5), resultSet.getInt(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11),
                        resultSet.getString(12), resultSet.getString(13), resultSet.getString(14), resultSet.getString(15));
                if(order.getWysylka().equals("1"))
                {
                    order.setWysylka("Tak");
                }
                else
                {
                    order.setWysylka("Nie");
                }
                orders.add(order);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return orders;
    }

    public ShowAllOrdersGUI(Stage stage, Connection connection, User user)
    {
        super(stage,connection,user);

        TableColumn<OrderView, String> firstColumn = new TableColumn<>("ID");
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<OrderView, String> secondColumn = new TableColumn<>("Przedmiot");
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("przedmiot"));
        TableColumn<OrderView, String> thirdColumn = new TableColumn<>("Cena");
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("cena"));
        TableColumn<OrderView, String> fourthColumn = new TableColumn<>("DataZamowienia");
        fourthColumn.setCellValueFactory(new PropertyValueFactory<>("dataZamowienia"));
        TableColumn<OrderView, String> fifthColumn = new TableColumn<>("Wartosc");
        fifthColumn.setCellValueFactory(new PropertyValueFactory<>("wartosc"));
        TableColumn<OrderView, String> sixthColumn = new TableColumn<>("Typ rachunku");
        sixthColumn.setCellValueFactory(new PropertyValueFactory<>("typRachunku"));
        TableColumn<OrderView, String> seventhColumn = new TableColumn<>("Wysyłka");
        seventhColumn.setCellValueFactory(new PropertyValueFactory<>("wysylka"));
        TableColumn<OrderView, String> eightColumn = new TableColumn<>("Status zamowienia");
        eightColumn.setCellValueFactory(new PropertyValueFactory<>("statusZamowienia"));
        TableColumn<OrderView, String> ninthColumn = new TableColumn<>("NIP");
        ninthColumn.setCellValueFactory(new PropertyValueFactory<>("NIP"));
        TableColumn<OrderView, String> tenthColumn = new TableColumn<>("Nazwa Firmy");
        tenthColumn.setCellValueFactory(new PropertyValueFactory<>("firmaNazwa"));
        TableColumn<OrderView, String> eleventhColumn = new TableColumn<>("Imie");
        eleventhColumn.setCellValueFactory(new PropertyValueFactory<>("imie"));
        TableColumn<OrderView, String> twelfthColumn = new TableColumn<>("Nazwisko");
        twelfthColumn.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        TableColumn<OrderView, String> thirteenthColumn = new TableColumn<>("Adres");
        thirteenthColumn.setCellValueFactory(new PropertyValueFactory<>("adres"));
        TableColumn<OrderView, String> fourteenthColumn = new TableColumn<>("Telefon");
        fourteenthColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        TableColumn<OrderView, String> fifteenthColumn = new TableColumn<>("Ilosc");
        fifteenthColumn.setCellValueFactory(new PropertyValueFactory<>("ilosc"));

        table = new TableView<>();
        table.setItems(getOrders());
        table.getColumns().addAll(firstColumn, secondColumn,fifteenthColumn, thirdColumn, fourthColumn,fifthColumn, sixthColumn, seventhColumn, eightColumn, ninthColumn, tenthColumn, eleventhColumn, twelfthColumn,
                thirteenthColumn, fourteenthColumn);

        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER);
        Button zmienStatusButton = new Button("Zmień status");
        ObservableList<String> statusList = FXCollections.observableArrayList();
        statusList.addAll("w trakcie", "anulowano", "zakonczono");
        ChoiceBox zmienStatusBox = new ChoiceBox(statusList);
        EventHandler<ActionEvent> zmienStatusButtonHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    if (zmienStatusBox.getSelectionModel().getSelectedItem().toString().length() > 0)
                    {
                        Statement statement = connection.createStatement();
                        String query = "UPDATE Zamowienia SET StatusZamowienia = '" + zmienStatusBox.getSelectionModel().getSelectedItem() + "' WHERE ID = " + table.getSelectionModel().getSelectedItem().getId() + ";";
                        statement.executeQuery(query);
                        table.setItems(getOrders());
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        };
        zmienStatusButton.addEventHandler(ActionEvent.ACTION, zmienStatusButtonHandler);
        row.getChildren().addAll(zmienStatusButton,zmienStatusBox);

        ScrollPane root = new ScrollPane(table);

        rightPane.getChildren().addAll(row, root);
    }
}
