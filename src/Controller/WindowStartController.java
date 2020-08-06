package Controller;

import View.loaders.WindowLogin;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;


public class WindowStartController {

    @FXML
    JFXButton btnEntrar;

    public void login(javafx.event.ActionEvent actionEvent) {
        WindowLogin w = new WindowLogin();
        Stage stage = (Stage) btnEntrar.getScene().getWindow();
        w.startModal();
    }


}
