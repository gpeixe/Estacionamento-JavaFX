package View.loaders;

import Controller.WindowAdminController;
import Controller.WindowAtendenteController;
import Controller.WindowLoginController;
import Model.Entities.Funcionarios.Administrador;
import Model.Entities.Funcionarios.Atendente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class WindowAdmin {
    WindowAdminController wa = new WindowAdminController();
    public void startModal(Administrador adm){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(getClass().getResource("/View/fxml/WindowAdmin.fxml").openStream());
            Scene scene = new Scene(pane, 640, 485);
            wa = loader.getController();
            wa.setAdm(adm);
            wa.setGraphVagas();
            wa.setVagasDisponiveis();
            wa.setVagasTotais();
            wa.setLblNumFuncionarios();
            wa.setLblNumMensalistas();

            Image icon = new Image("Images/icon.png");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setTitle("Funcionário");
            stage.setResizable(false);
            stage.show();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}