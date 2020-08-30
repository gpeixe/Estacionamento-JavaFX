package View.loaders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class WindowStart extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/fxml/WindowStart.fxml"));
        Image icon = new Image("Images/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Estacionamento");
        stage.setResizable(false);
        stage.setScene(new Scene(root, 618, 496));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
