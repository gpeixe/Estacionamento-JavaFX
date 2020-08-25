package View.loaders;

import Controller.WindowAtendenteController;
import Model.Entities.Funcionarios.Atendente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowAtendente {

    public void startModal(Atendente atd){
        try {
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(getClass().getResource("/View/fxml/WindowAtendente.fxml").openStream());
            WindowAtendenteController controller = loader.getController();
            controller.setAtendente(atd);
            Stage stage = new Stage();

            stage.setTitle("Atendente");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(pane, 1055, 618));
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}