package Controller;

import Model.Entities.Funcionarios.Administrador;
import Model.Entities.Funcionarios.Atendente;
import Model.Entities.Funcionarios.Efuncao;
import Model.Entities.Funcionarios.Funcionario;
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
            Funcionario funcionario  = loginUseCase.login(cpf, password);
            Efuncao funcao = (Efuncao) funcionario.getFuncao();

            if(funcao.getFuncao().equals("Administrador")){
                WindowAdmin w = new WindowAdmin();
                Administrador adm = (Administrador) funcionario;
                w.startModal(adm);
            }
            else if (funcao.getFuncao().equals("Atendente")){
                WindowAtendente w = new WindowAtendente();
                w.startModal((Atendente) funcionario);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }



}




