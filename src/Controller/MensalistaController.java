package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MensalistaController {
    public void salvarMensalista(ActionEvent actionEvent) {
    }

    public void cancelaOp(ActionEvent actionEvent) {
    }

    public void telaNovoMensalista(ActionEvent actionEvent) throws Exception{
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/TelaCadUpdMensalista.fxml"));
        loginStage.setScene(new Scene(root, 350, 362));
        loginStage.setResizable(false);
        loginStage.setTitle("Mensalista");
        loginStage.initModality(Modality.WINDOW_MODAL);
        loginStage.showAndWait();
    }
}
