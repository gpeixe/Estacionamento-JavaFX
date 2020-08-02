package Controller;

import View.loaders.WindowCrudMensalista;
import View.loaders.WindowLogin;
import View.loaders.WindowTicket;
import javafx.event.ActionEvent;


public class WindowAtendenteController {

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
    }
}