package View.loaders;

import Controller.MensalistaController;
import Model.Entities.Mensalista.Mensalista;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowMensalista {
    MensalistaController mc = new MensalistaController();
    public void startModal(Mensalista mensalista){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(getClass().getResource("/View/fxml/WindowMensalista.fxml").openStream());
            Scene scene = new Scene(pane, 431, 333);
            mc = loader.getController();

            if(mensalista != null){
                mc.editaMensalista(mensalista);
                mc.setMensalista(mensalista);
            }
            Image icon = new Image("Images/icon.png");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setTitle("Mensalista");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}