package Main.Package;

import Main.Models.OrderView;
import Main.Models.Package;
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

public class ShowAllPackagesGUI extends PackageGUI
{
    private TableView<Package> table;

    private ObservableList<Package> getPackages()
    {
        ObservableList<Package> packages = FXCollections.observableArrayList();
        try
    {
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM Przesylki;";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next())
        {
            Package pack = new Package(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                    resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
                    resultSet.getString(11),resultSet.getString(12),resultSet.getString(13),resultSet.getString(14));
            packages.addAll(pack);
        }
    }
    catch (SQLException e)
    {
        e.printStackTrace();
    }
        return packages;
    }

    public ShowAllPackagesGUI(Stage stage, Connection connection, User user)
    {
        super(stage,connection,user);

        TableColumn<Package, String> firstColumn = new TableColumn<>("ID Zamowienia");
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("idZamowienia"));
        TableColumn<Package, String> secondColumn = new TableColumn<>("Ilość Paczek");
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("iloscPaczek"));
        TableColumn<Package, String> thirdColumn = new TableColumn<>("Status przesylki");
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("statusPrzesylki"));
        TableColumn<Package, String> fourthColumn = new TableColumn<>("Imie");
        fourthColumn.setCellValueFactory(new PropertyValueFactory<>("imie"));
        TableColumn<Package, String> fifthColumn = new TableColumn<>("Nazwisko");
        fifthColumn.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        TableColumn<Package, String> sixthColumn = new TableColumn<>("Adres");
        sixthColumn.setCellValueFactory(new PropertyValueFactory<>("adres"));
        TableColumn<Package, String> seventhColumn = new TableColumn<>("Kod Pocztowy");
        seventhColumn.setCellValueFactory(new PropertyValueFactory<>("kodPocztowy"));
        TableColumn<Package, String> eightColumn = new TableColumn<>("Miejscowosc");
        eightColumn.setCellValueFactory(new PropertyValueFactory<>("miejscowosc"));
        TableColumn<Package, String> ninthColumn = new TableColumn<>("Telefon");
        ninthColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        TableColumn<Package, String> tenthColumn = new TableColumn<>("Mail");
        tenthColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        TableColumn<Package, String> eleventhColumn = new TableColumn<>("Dodatkowe informacje");
        eleventhColumn.setCellValueFactory(new PropertyValueFactory<>("dodatkoweInformacje"));
        TableColumn<Package, String> twelfthColumn = new TableColumn<>("Data utworzenia");
        twelfthColumn.setCellValueFactory(new PropertyValueFactory<>("dataUtworzenia"));
        TableColumn<Package, String> thirteenthColumn = new TableColumn<>("Dostawca");
        thirteenthColumn.setCellValueFactory(new PropertyValueFactory<>("dostawca"));
        TableColumn<Package, String> fourteenthColumn = new TableColumn<>("Typ Dostawy");
        fourteenthColumn.setCellValueFactory(new PropertyValueFactory<>("typDostawy"));

        table = new TableView<>();
        table.setItems(getPackages());
        table.getColumns().addAll(firstColumn, secondColumn, thirdColumn, fourthColumn, sixthColumn, seventhColumn, eightColumn, ninthColumn, tenthColumn, eleventhColumn, twelfthColumn,
                thirteenthColumn, fourteenthColumn);
        ScrollPane root = new ScrollPane(table);

        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER);
        Button zmienStatusButton = new Button("Zmień status");
        ObservableList<String> statusList = FXCollections.observableArrayList();
        statusList.addAll("do spakowania", "pakowana", "spakowana", "anulowano");
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
                        String query = "UPDATE Przesylki SET StatusPrzesylki = '" + zmienStatusBox.getSelectionModel().getSelectedItem() + "' WHERE IDZamowienia = " + table.getSelectionModel().getSelectedItem().getIdZamowienia() + ";";
                        statement.executeQuery(query);
                        table.setItems(getPackages());
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

        rightPane.getChildren().addAll(row, root);
    }
}
