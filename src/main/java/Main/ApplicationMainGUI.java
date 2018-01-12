package Main;

import Main.Admin.AdminGUI;
import Main.Client.ClientGUI;
import Main.Firm.FirmGUI;
import Main.Models.User;
import Main.Order.OrderGUI;
import Main.Package.PackageGUI;
import Main.Package.PackingGUI;
import Main.Products.ProductGUI;
import Main.Storage.StorageGUI;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;


public class ApplicationMainGUI
{
    protected Connection connection = null;

    private Stage stage = null;
    private Scene scene = null;

    private User user = null;

    private BorderPane root = new BorderPane();
    protected SplitPane splitPane = null;
    private MenuBar menuBar = new MenuBar();
    protected StackPane leftPane = new StackPane();
    protected VBox rightPane = new VBox(5);

    private Menu biuroMenu = new Menu("Biuro");
    private Menu pakowanieMenu = new Menu("Pakowanie");
    private Menu magazynMenu = new Menu("Magazyn");
    private Menu adminMenu = new Menu("Admin");
    private SimpleBooleanProperty biuroMenuVisibleProperty = new SimpleBooleanProperty();
    private SimpleBooleanProperty pakowanieMenuVisibleProperty = new SimpleBooleanProperty();
    private SimpleBooleanProperty magazynMenuVisibleProperty = new SimpleBooleanProperty();
    private SimpleBooleanProperty adminMenuVisibleProperty = new SimpleBooleanProperty();

    public Scene getScene()
    {
        return scene;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
        biuroMenuVisibleProperty.setValue(user.isUprBiuro());
        pakowanieMenuVisibleProperty.setValue(user.isUprPakowanie());
        magazynMenuVisibleProperty.setValue(user.isUprMagazyn());
        adminMenuVisibleProperty.setValue(user.isUprAdmin());
    }

    private void biuroMenuSetup()
    {
        biuroMenu.visibleProperty().bindBidirectional(biuroMenuVisibleProperty);

        MenuItem biuroFirmyMenuItem = new MenuItem("Firmy");
        biuroFirmyMenuItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new FirmGUI(stage, connection, user).getScene());
            }
        });

        MenuItem biuroKlienciMenuItem = new MenuItem("Klienci");
        biuroKlienciMenuItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new ClientGUI(stage, connection, user).getScene());
            }
        });

        MenuItem biuroProduktyMenuItem = new MenuItem("Produkty");
        biuroProduktyMenuItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new ProductGUI(stage, connection, user).getScene());
            }
        });

        MenuItem biuroZamowieniaMenuItem = new MenuItem("Zamówienia");
        biuroZamowieniaMenuItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new OrderGUI(stage, connection, user).getScene());
            }
        });

        MenuItem biuroPrzesylkiMenuItem = new MenuItem("Przesylki");
        biuroPrzesylkiMenuItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new PackageGUI(stage, connection, user).getScene());
            }
        });

        biuroMenu.getItems().addAll(biuroFirmyMenuItem, biuroKlienciMenuItem, biuroProduktyMenuItem, biuroZamowieniaMenuItem, biuroPrzesylkiMenuItem);
    }

    public void pakowanieMenuSetup()
    {
        pakowanieMenu.visibleProperty().bindBidirectional(pakowanieMenuVisibleProperty);
        MenuItem pakowanieMenuItem = new MenuItem("Pakowanie");
        pakowanieMenuItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new PackingGUI(stage, connection, user).getScene());
            }
        });
        pakowanieMenu.getItems().addAll(pakowanieMenuItem);
    }

    public void magazynMenuSetup()
    {
        magazynMenu.visibleProperty().bindBidirectional(magazynMenuVisibleProperty);
        MenuItem magazynMenuItem = new MenuItem("Magazyn");
        magazynMenuItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new StorageGUI(stage, connection, user).getScene());
            }
        });

        magazynMenu.getItems().addAll(magazynMenuItem);
    }

    public void adminMenuSetup()
    {
        adminMenu.visibleProperty().bindBidirectional(adminMenuVisibleProperty);
        MenuItem adminMenuItem = new MenuItem("Admin panel");
        adminMenuItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(new AdminGUI(stage, connection, user).getScene());
            }
        });

        adminMenu.getItems().addAll(adminMenuItem);
    }

    public ApplicationMainGUI(Stage stage, Connection connection)
    {
        this.stage = stage;
        this.connection = connection;

        root.setMaxHeight(500);
        root.setMinHeight(500);
        root.setMaxWidth(1000);
        root.setMinWidth(1000);

        biuroMenuSetup();
        pakowanieMenuSetup();
        magazynMenuSetup();
        adminMenuSetup();

        menuBar.getMenus().addAll(biuroMenu, pakowanieMenu, magazynMenu, adminMenu);

        leftPane.setMaxWidth(150);
        leftPane.setMinWidth(150);
        leftPane.setAlignment(Pos.CENTER);

        rightPane.setMinWidth(850);

        splitPane = new SplitPane(leftPane, rightPane);
        splitPane.setOrientation(Orientation.HORIZONTAL);
        root.setTop(menuBar);
        root.setCenter(splitPane);

        scene = new Scene(root);
    }
}
