package Main.Admin;

import Main.Models.Package;
import Main.Models.PackageArchive;
import Main.Models.ProductLog;
import Main.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PackageArchiveGUI extends AdminGUI
{
    private TableView<PackageArchive> table;

    private ObservableList<PackageArchive> getArchive()
    {
        ObservableList<PackageArchive> archive = FXCollections.observableArrayList();
        try
        {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM PrzesylkiArchiwum;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                PackageArchive packageArchive = new PackageArchive(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),
                        resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12),
                        resultSet.getString(13),resultSet.getString(14),resultSet.getString(15),resultSet.getString(16));
                archive.add(packageArchive);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return archive;
    }

    public PackageArchiveGUI(Stage stage, Connection connection, User user)
    {
        super(stage, connection, user);

        TableColumn<PackageArchive, String> fifteenthColumn = new TableColumn<>("Akcja");
        fifteenthColumn.setCellValueFactory(new PropertyValueFactory<>("akcja"));
        TableColumn<PackageArchive, String> sixteenthColumn = new TableColumn<>("Data wykonania");
        sixteenthColumn.setCellValueFactory(new PropertyValueFactory<>("dataWykonania"));
        TableColumn<PackageArchive, String> firstColumn = new TableColumn<>("ID Zamowienia");
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("idZamowienia"));
        TableColumn<PackageArchive, String> secondColumn = new TableColumn<>("Ilość Paczek");
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("iloscPaczek"));
        TableColumn<PackageArchive, String> thirdColumn = new TableColumn<>("Status przesylki");
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("statusPrzesylki"));
        TableColumn<PackageArchive, String> fourthColumn = new TableColumn<>("Imie");
        fourthColumn.setCellValueFactory(new PropertyValueFactory<>("imie"));
        TableColumn<PackageArchive, String> fifthColumn = new TableColumn<>("Nazwisko");
        fifthColumn.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        TableColumn<PackageArchive, String> sixthColumn = new TableColumn<>("Adres");
        sixthColumn.setCellValueFactory(new PropertyValueFactory<>("adres"));
        TableColumn<PackageArchive, String> seventhColumn = new TableColumn<>("Kod Pocztowy");
        seventhColumn.setCellValueFactory(new PropertyValueFactory<>("kodPocztowy"));
        TableColumn<PackageArchive, String> eightColumn = new TableColumn<>("Miejscowosc");
        eightColumn.setCellValueFactory(new PropertyValueFactory<>("miejscowosc"));
        TableColumn<PackageArchive, String> ninthColumn = new TableColumn<>("Telefon");
        ninthColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        TableColumn<PackageArchive, String> tenthColumn = new TableColumn<>("Mail");
        tenthColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        TableColumn<PackageArchive, String> eleventhColumn = new TableColumn<>("Dodatkowe informacje");
        eleventhColumn.setCellValueFactory(new PropertyValueFactory<>("dodatkoweInformacje"));
        TableColumn<PackageArchive, String> twelfthColumn = new TableColumn<>("Data utworzenia");
        twelfthColumn.setCellValueFactory(new PropertyValueFactory<>("dataUtworzenia"));
        TableColumn<PackageArchive, String> thirteenthColumn = new TableColumn<>("Dostawca");
        thirteenthColumn.setCellValueFactory(new PropertyValueFactory<>("dostawca"));
        TableColumn<PackageArchive, String> fourteenthColumn = new TableColumn<>("Typ Dostawy");
        fourteenthColumn.setCellValueFactory(new PropertyValueFactory<>("typDostawy"));

        table = new TableView<>();
        table.setItems(getArchive());
        table.getColumns().addAll(fifteenthColumn, sixteenthColumn, firstColumn, secondColumn, thirdColumn, fourthColumn, sixthColumn, seventhColumn, eightColumn, ninthColumn, tenthColumn, eleventhColumn, twelfthColumn,
                thirteenthColumn, fourteenthColumn);
        ScrollPane root = new ScrollPane(table);
        rightPane.getChildren().addAll(root);
    }
}
