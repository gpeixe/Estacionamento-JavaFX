package Controller;

import Model.UseCases.TicketUseCase;
import Model.UseCases.VagasUseCase;
import Utils.MaskFieldUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class WindowGeraPagamentoController {
    @FXML
    JFXTextField tfCpfPagamento;

    @FXML
    JFXButton btnGeraPagamento;

    @FXML
    Label errorLabel;

    private TicketUseCase ticketUseCase = new TicketUseCase();
    private VagasUseCase vagasUseCase = new VagasUseCase();

    public void gerarPagamento(ActionEvent actionEvent) throws SQLException {
        if(ticketUseCase.isMensalistaTicket(tfCpfPagamento.getText())){
            ticketUseCase.saidaMensalista(tfCpfPagamento.getText());
            ((Stage)btnGeraPagamento.getScene().getWindow()).close();
        } else if (ticketUseCase.isClienteTicket(tfCpfPagamento.getText())){
            ticketUseCase.pagamentoCliente(tfCpfPagamento.getText());
            vagasUseCase.setVagaFree(tfCpfPagamento.getText());
            ((Stage)btnGeraPagamento.getScene().getWindow()).close();
        } else {
            errorLabel.setText("Nenhuma vaga encontrada para esse CPF.");
        }
    }

    public void formatarCpf(KeyEvent keyEvent) {
        MaskFieldUtil.cpfField(tfCpfPagamento);
    }
}
