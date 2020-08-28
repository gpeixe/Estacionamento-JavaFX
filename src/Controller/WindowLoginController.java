package Controller;

import Model.Entities.Funcionarios.*;
import Model.UseCases.AtualizaVigilanteUseCase;
import Model.UseCases.LoginUseCase;
import Utils.MaskFieldUtil;
import View.loaders.WindowAdmin;
import View.loaders.WindowAtendente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
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

    @FXML
    Label errorLabel;

    private boolean isVigilanteLogin;

    public void login(javafx.event.ActionEvent actionEvent) {

        String cpf = tfCPFUser.getText();
        String password = tfSenhaUser.getText();

        try {

            Funcionario funcionario  = loginUseCase.login(cpf, password);

            if(funcionario != null ){

                Efuncao funcao = (Efuncao) funcionario.getFuncao();
                if(!isVigilanteLogin){

                    if(funcao.getFuncao().equals("Administrador")){
                        WindowAdmin w = new WindowAdmin();
                        Administrador adm = (Administrador) funcionario;
                        w.startModal(adm);
                    }

                    else if (funcao.getFuncao().equals("Atendente")){
                        WindowAtendente w = new WindowAtendente();
                        Atendente atd = (Atendente) funcionario;
                        w.startModal(atd);
                    }

                    else {
                        errorLabel.setText("Apenas atendentes e adms.");
                    }
                }
                else {
                    if(funcao.getFuncao().equals("Vigilante")){
                        Vigilante newVig = (Vigilante) funcionario;
                        AtualizaVigilanteUseCase atualizaVigilante = new AtualizaVigilanteUseCase();
                        atualizaVigilante.atualizaVigilante(newVig);
                        //((Stage) tfCPFUser.getScene().getWindow()).close();

                    }
                    else  {
                        errorLabel.setText("Apenas vigilantes.");
                    }
                }
            }

            else if(tfCPFUser.getText().length() == 0 || tfSenhaUser.getText().length() == 0){
                errorLabel.setText("Informe todos os campos.");
            }

            else {
               errorLabel.setText("Usuário não encontrado.");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    public void formataCPF(KeyEvent keyEvent) {
        MaskFieldUtil.cpfField(tfCPFUser);
    }

    public void setIsVigilanteLogin(boolean isVigilanteLogin) {
        this.isVigilanteLogin = isVigilanteLogin;
    }
}




