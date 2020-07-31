package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UsuarioController {
    @FXML
    JFXButton btnLogar;
    @FXML
    JFXPasswordField senhaUser;
    @FXML
    JFXTextField cpfUser;

    public void login(ActionEvent actionEvent) throws Exception{
        if(cpfUser.getText().equals("admin")){
            Stage principalStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../View/TelaAdmin.fxml"));
            principalStage.setScene(new Scene(root, 910, 496));
            principalStage.setResizable(false);
            principalStage.setTitle("ADMIN");
            principalStage.initModality(Modality.WINDOW_MODAL);
            principalStage.showAndWait();
        }   else if(cpfUser.getText().equals("vig")) {
            /*Voltar pra onde estava, no caso fechar o modal*/
        }   else if(cpfUser.getText().equals("atendente")) {
            Stage principalStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../View/TelaPrincipal.fxml"));
            principalStage.setScene(new Scene(root, 618, 496));
            principalStage.setResizable(false);
            principalStage.setTitle("Principal");
            principalStage.initModality(Modality.WINDOW_MODAL);
            principalStage.showAndWait();
        }
    }
}
