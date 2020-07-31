package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FuncionarioController {
    public void salvarFuncionario(ActionEvent actionEvent) {
    }

    public void cancelaOp(ActionEvent actionEvent) {
    }

    public void telaNovoFuncionario(ActionEvent actionEvent) throws Exception{
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/TelaCadUpdFuncionario.fxml"));
        loginStage.setScene(new Scene(root, 350, 318));
        loginStage.setResizable(false);
        loginStage.setTitle("Funcionários");
        loginStage.initModality(Modality.WINDOW_MODAL);
        loginStage.showAndWait();
    }
}
