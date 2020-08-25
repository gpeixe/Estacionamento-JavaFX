package Controller;

import Model.Entities.Funcionarios.Administrador;
import View.loaders.*;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;


public class WindowAdminController {
    public Administrador adm;



    @FXML
    JFXButton btnDeslogar;

    public void openTelaTicket(ActionEvent actionEvent) {
        WindowTicket w = new WindowTicket();
        w.startModal();
    }

    public void deslogaAdmin(ActionEvent actionEvent) {
        Stage stage = (Stage) btnDeslogar.getScene().getWindow();
        stage.close();
    }

    public void openTelaRelatorios(ActionEvent actionEvent) {
        WindowRelatorio w = new WindowRelatorio();
        w.startModal();
    }

    public void openTelaPrecos(ActionEvent actionEvent) {
        WindowAlteraPrecos w = new WindowAlteraPrecos();
        w.startModal();
    }

    public void openTelaCRUDMensalista(ActionEvent actionEvent) throws Exception{
        WindowCrudMensalista w = new WindowCrudMensalista();
        w.startModal();
    }

    public void openTelaCRUDFuncionarios(ActionEvent actionEvent) throws Exception{
        WindowCrudFuncionario w = new WindowCrudFuncionario();
        w.startModal();
    }

    public void salvaPrecosEstacionamento(ActionEvent actionEvent) {
    }

    public void setAdm(Administrador adm) {
        this.adm = adm;
    }
}