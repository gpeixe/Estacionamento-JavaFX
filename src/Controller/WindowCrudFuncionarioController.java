package Controller;

import View.loaders.WindowFuncionario;
import javafx.event.ActionEvent;


public class WindowCrudFuncionarioController {

    public void telaNovoFuncionario(ActionEvent actionEvent) {
        WindowFuncionario w = new WindowFuncionario();
        w.startModal();
    }

    public void editaFuncionario(ActionEvent actionEvent) {
    }

    public void removeFuncionario(ActionEvent actionEvent) {
    }
}
