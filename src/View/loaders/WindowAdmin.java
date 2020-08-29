package View.loaders;

import Controller.WindowAdminController;
import Controller.WindowLoginController;
import Model.Entities.Funcionarios.Administrador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowAdmin {

    public void startModal(Administrador adm){
        try {
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(getClass().getResource("/View/fxml/WindowAdmin.fxml").openStream());
            WindowAdminController controller = loader.getController();
            controller.setAdm(adm);
            Stage stage = new Stage();

            Image icon = new Image("Images/icon.png");
            stage.getIcons().add(icon);
            stage.setTitle("Admin");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(pane, 910, 496));
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}