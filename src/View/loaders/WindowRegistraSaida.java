package View.loaders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowRegistraSaida {
    public void startModal(){
        try {
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(getClass().getResource("/View/fxml/WindowRegistraSaida.fxml").openStream());

            Stage stage = new Stage();

            stage.setTitle("Pagamento");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(pane, 451, 121));
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}