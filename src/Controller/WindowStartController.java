package Controller;

import View.loaders.WindowLogin;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;


public class WindowStartController {

    @FXML
    JFXButton loginButton;

    public void login(javafx.event.ActionEvent actionEvent) {
        WindowLogin w = new WindowLogin();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        w.startModal();
    }


}
