package Controller;

import View.loaders.WindowCrudMensalista;
import View.loaders.WindowEntradaMensalista;
import View.loaders.WindowLogin;
import View.loaders.WindowTicket;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;


public class WindowAtendenteController {

    @FXML
    JFXButton btnDeslogar;
    public void openTelaTicket(ActionEvent actionEvent) {
        WindowTicket w = new WindowTicket();
        w.startModal();
    }

    public void openTelaLogin(ActionEvent actionEvent) {
        WindowLogin w = new WindowLogin();
        w.startModal();
    }

    public void openTelaCRUDMensalista(ActionEvent actionEvent) {
        WindowCrudMensalista w = new WindowCrudMensalista();
        w.startModal();
    }

    public void deslogarFunc(ActionEvent actionEvent) {
        Stage stage = (Stage) btnDeslogar.getScene().getWindow();
        stage.close();
    }

    public void openTelaEntradaMensalista(ActionEvent actionEvent) {
        WindowEntradaMensalista w = new WindowEntradaMensalista();
        w.startModal();
    }
}