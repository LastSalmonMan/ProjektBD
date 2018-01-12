package Main.Products;

import Main.Models.Firm;
import Main.Models.Product;
import Main.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
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

public class ShowAllProductsGUI extends ProductGUI
{
    private TableView<Product> table;

    public ObservableList<Product> getProducts()
    {
        ObservableList<Product> products = FXCollections.observableArrayList();
        try
        {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Przedmioty;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                Product product = new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getFloat(3));
                products.add(product);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return products;
    }


    public ShowAllProductsGUI(Stage stage, Connection connection, User user)
    {
        super(stage, connection, user);

        TableColumn<Product, String> firstColumn = new TableColumn<>("ID");
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Product, String> secondColumn = new TableColumn<>("Nazwa");
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        TableColumn<Product, String> thirdColumn = new TableColumn<>("Cena");
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("cena"));

        table = new TableView<>();
        //table.setMinWidth(600);
        //table.setMaxWidth(640);
        table.setItems(getProducts());
        table.getColumns().addAll(firstColumn, secondColumn, thirdColumn);

        ScrollPane root = new ScrollPane(table);
        //root.setMinWidth(600);
        rightPane.getChildren().addAll(root);
    }
}
