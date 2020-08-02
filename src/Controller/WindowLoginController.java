package Controller;

import View.loaders.WindowAdmin;
import View.loaders.WindowAtendente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class WindowLoginController {
    @FXML
    JFXButton btnLogar;
    @FXML
    JFXPasswordField senhaUser;
    @FXML
    JFXTextField cpfUser;

    public void login(javafx.event.ActionEvent actionEvent) {
        switch (cpfUser.getText()) {
            case "admin": {
                WindowAdmin w = new WindowAdmin();
                w.startModal();
                break;
            }
            case "vig":
                /*Voltar pra onde estava, no caso fechar o modal*/
                break;
            case "atendente": {
                WindowAtendente w = new WindowAtendente();
                w.startModal();
                break;
            }
        }
    }
}
