package Main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class WarningWindow
{
    public WarningWindow(String message)
    {
        Stage stage = new Stage();
        stage.setTitle("Warning");
        VBox root = new VBox(5);
        Label label = new Label(message);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(label);

        stage.setMaxHeight(100);
        stage.setMinHeight(100);
        stage.setMaxWidth(150);
        stage.setMinWidth(150);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
