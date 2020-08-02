package View.loaders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowFuncionario {

    public void startModal(){
        try {
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(getClass().getResource("/View/fxml/WindowFuncionario.fxml").openStream());

            Stage stage = new Stage();

            stage.setTitle("Funcionario");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(pane, 350, 435));
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}