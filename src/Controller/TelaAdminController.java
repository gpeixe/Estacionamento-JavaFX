package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaAdminController {
    public void openTelaTicket(ActionEvent actionEvent) {
    }

    public void deslogaAdmin(ActionEvent actionEvent) {
    }

    public void openTelaRelatorios(ActionEvent actionEvent) {
    }

    public void openTelaPrecos(ActionEvent actionEvent) {
    }

    public void openTelaCRUDMensalista(ActionEvent actionEvent) throws Exception{
        Stage mensalistaStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/TelaCRUDMensalistas.fxml"));
        mensalistaStage.setScene(new Scene(root, 1010, 589));
        mensalistaStage.setResizable(false);
        mensalistaStage.setTitle("Mensalista");
        mensalistaStage.initModality(Modality.WINDOW_MODAL);
        mensalistaStage.showAndWait();
    }

    public void openTelaCRUDFuncionarios(ActionEvent actionEvent) throws Exception{
        Stage funcionarioStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/TelaCRUDFuncionarios.fxml"));
        funcionarioStage.setScene(new Scene(root, 1205, 589));
        funcionarioStage.setResizable(false);
        funcionarioStage.setTitle("Funcionario");
        funcionarioStage.initModality(Modality.WINDOW_MODAL);
        funcionarioStage.showAndWait();
    }
}
