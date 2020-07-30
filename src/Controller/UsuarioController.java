package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UsuarioController {
    public void login(ActionEvent actionEvent) throws Exception{
        Stage principalStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/TelaPrincipal.fxml"));
        principalStage.setScene(new Scene(root, 618, 496));
        principalStage.setResizable(false);
        principalStage.setTitle("Login");
        principalStage.initModality(Modality.WINDOW_MODAL);
        principalStage.showAndWait();
    }
}
