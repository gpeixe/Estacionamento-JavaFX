package Controller;

import Utils.MaskFieldUtil;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class WindowGeraPagamentoController {
    @FXML
    JFXTextField tfCpfPagamento;

    public void gerarPagamento(ActionEvent actionEvent) {
    }

    public void formatarCpf(KeyEvent keyEvent) {
        MaskFieldUtil.cpfField(tfCpfPagamento);
    }
}
