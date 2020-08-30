package Controller;

import Model.Entities.Ticket.Ticket;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class WindowGeraPagamentoController {

    @FXML
    JFXTextField tfCpfPagamento;

    @FXML
    JFXTextField tfHorarioEntrada;
    @FXML
    JFXTextField tfHorarioSaida;
    @FXML
    JFXTextField tfPlaca;
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

    public void buscarTicket(ActionEvent actionEvent) {

        TicketUseCase ticketUseCase = new TicketUseCase();
        String cpf = tfCpfPagamento.getText();
        Ticket ticket;

        try {

            if (ticketUseCase.isClienteTicket(cpf)) {
                //ticket = ticketUseCase.getOpenClienteTicketByCpf(cpf);

            }
            else if(ticketUseCase.isMensalistaTicket(tfCpfPagamento.getText())){
                //ticket = ticketUseCase.getOpenMensalistaTicketByCpf(cpf);

            }
            else {
                errorLabel.setText("Ticket não encontrado para o cpf informado.");
            }

            /*if(ticket != null){
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String horarioSaida = now.format(formatter);
                tfHorarioEntrada.setText(ticket.getHorarioEntrada());
                tfHorarioSaida.setText(horarioSaida);
                tfPlaca.setText(ticket.getPlaca());
            }*/

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cancelar(ActionEvent actionEvent) {
        ((Stage) btnGeraPagamento.getScene().getWindow()).close();
    }

    public void setCpf(String cpf) {
        tfCpfPagamento.setText(cpf);
    }
}
