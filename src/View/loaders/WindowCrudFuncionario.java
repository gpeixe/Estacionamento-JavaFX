package View.loaders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
            Image icon = new Image("Images/icon.png");
            stage.getIcons().add(icon);
            stage.setTitle("Funcionario");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(pane, 1267, 590));
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}