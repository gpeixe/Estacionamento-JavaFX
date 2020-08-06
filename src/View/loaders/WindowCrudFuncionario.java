package View.loaders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowCrudFuncionario {

    public void startModal(){
        try {
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(getClass().getResource("/View/fxml/WindowCrudFuncionario.fxml").openStream());

            Stage stage = new Stage();

            stage.setTitle("Funcionario");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(pane, 1010, 589));
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}