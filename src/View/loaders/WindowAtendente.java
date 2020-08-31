package View.loaders;

import Controller.FuncionarioController;
import Controller.WindowAtendenteController;
import Model.Entities.Funcionarios.Atendente;
import Model.Entities.Funcionarios.Funcionario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class WindowAtendente {
    WindowAtendenteController wa;
    public void startModal(Atendente atd){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(getClass().getResource("/View/fxml/WindowAtendente.fxml").openStream());
            Scene scene = new Scene(pane, 1056, 570);
            wa = loader.getController();
            wa.setAtendente(atd);
            wa.setVagasDisponiveis();
            wa.setVagasTotais();
            wa.setLabelPrecos();
            wa.setGraphVagas();
            Image icon = new Image("Images/icon.png");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setTitle("Funcionário");
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();


        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}