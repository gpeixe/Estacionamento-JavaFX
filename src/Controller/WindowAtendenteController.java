package Controller;

import Model.Entities.Funcionarios.Atendente;
import View.loaders.*;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;


public class WindowAtendenteController {
    public Atendente atd;
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

    public void registraSaida(ActionEvent actionEvent) {
        WindowRegistraSaida w = new WindowRegistraSaida();
        w.startModal();
    }

    public void gerarPagamento(ActionEvent actionEvent) {
    }

    public void setAtendente(Atendente atd) {
        this.atd = atd;
    }
}