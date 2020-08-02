package Controller;

import View.loaders.WindowCrudFuncionario;
import View.loaders.WindowCrudMensalista;
import javafx.event.ActionEvent;


public class WindowAdminController {

    public void openTelaTicket(ActionEvent actionEvent) {
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
}