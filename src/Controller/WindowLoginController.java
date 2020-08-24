package Controller;

import Model.UseCases.LoginUseCase;
import View.loaders.WindowAdmin;
import View.loaders.WindowAtendente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class WindowLoginController {
    public LoginUseCase loginUseCase = new LoginUseCase();
    @FXML
    JFXButton btnLogar;
    @FXML
    JFXPasswordField tfSenhaUser;
    @FXML
    JFXTextField tfCPFUser;




    public void login(javafx.event.ActionEvent actionEvent) {

        String cpf = tfCPFUser.getText();
        String password = tfSenhaUser.getText();

        try {
            if(loginUseCase.isLogin(cpf, password))
                tfCPFUser.setText("Logado");
            else
                tfCPFUser.setText("Nao Logado");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



       /* switch (tfCPFUser.getText()) {
            case "admin": {
                WindowAdmin w = new WindowAdmin();
                w.startModal();
                break;
            }
            case "vig":
                Voltar pra onde estava, no caso fechar o modal
                break;
            case "atendente": {
                WindowAtendente w = new WindowAtendente();
                w.startModal();
                break;

            }
        } */


    }




}
