package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class FuncionarioController {
    @FXML
    JFXButton btnCancelar;

    public void salvarFuncionario(ActionEvent actionEvent) {
    }

    public void cancelaOp(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

}
