package View.loaders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowRegistraSaida {
    public void startModal(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(getClass().getResource("View/fxml/WindowRegistraSaida.fxml").openStream());
            Scene scene = new Scene(pane, 451, 121);

            Image icon = new Image("Images/icon.png");
            stage.getIcons().add(icon);

            stage.setScene(scene);
            stage.setTitle("Pagamento");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
