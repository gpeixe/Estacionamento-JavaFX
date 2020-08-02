package Controller;

import View.loaders.WindowMensalista;
import javafx.event.ActionEvent;


public class WindowCrudMensalistaController {

    public void telaNovoMensalista(ActionEvent actionEvent) {
        WindowMensalista w = new WindowMensalista();
        w.startModal();
    }
}