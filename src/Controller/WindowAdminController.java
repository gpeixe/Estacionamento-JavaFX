package Controller;

import View.loaders.WindowCrudFuncionario;
import View.loaders.WindowCrudMensalista;
import View.loaders.WindowTicket;
import javafx.event.ActionEvent;


public class WindowAdminController {

    public void openTelaTicket(ActionEvent actionEvent) {
        WindowTicket w = new WindowTicket();
        w.startModal();
    }

    public void deslogaAdmin(ActionEvent actionEvent) {
    }

    public void openTelaRelatorios(ActionEvent actionEvent) {
    }

    public void openTelaPrecos(ActionEvent actionEvent) {
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
}