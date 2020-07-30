package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class IniciarSistemaController {
    public void openTelaLogin(ActionEvent actionEvent) throws Exception{
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/TelaLogin.fxml"));
        loginStage.setScene(new Scene(root, 350, 318));
        loginStage.setResizable(false);
        loginStage.setTitle("Login");
        loginStage.initModality(Modality.WINDOW_MODAL);
        loginStage.showAndWait();
    }
}
