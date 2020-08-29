package View.loaders;

import Controller.FuncionarioController;
import Controller.MensalistaController;
import Model.Entities.Funcionarios.Funcionario;
import Model.Entities.Mensalista.Mensalista;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowFuncionario {
    FuncionarioController fc = new FuncionarioController();
    public void startModal(Funcionario funcionario){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(getClass().getResource("/View/fxml/WindowFuncionario.fxml").openStream());
            Scene scene = new Scene(pane, 350, 456);
            fc = loader.getController();

            if(funcionario != null){
                fc.editFuncionario(funcionario);
                fc.setFuncionario(funcionario);
            }
            Image icon = new Image("Images/icon.png");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setTitle("Funcionário");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
