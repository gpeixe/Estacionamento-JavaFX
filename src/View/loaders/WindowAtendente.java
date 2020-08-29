package View.loaders;

import Controller.FuncionarioController;
import Controller.WindowAtendenteController;
import Model.Entities.Funcionarios.Atendente;
import Model.Entities.Funcionarios.Funcionario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class WindowAtendente {
    WindowAtendenteController wa = new WindowAtendenteController();
    public void startModal(Atendente atd){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(getClass().getResource("/View/fxml/WindowAtendente.fxml").openStream());
            Scene scene = new Scene(pane, 1056, 570);
            wa = loader.getController();
            wa.setCurrentVigilante(null);
            wa.setAtendente(atd);
            wa.setVagasDisponiveis();
            wa.setVagasTotais();
            wa.setLabelPrecos();

            stage.setScene(scene);
            stage.setTitle("Funcionário");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}