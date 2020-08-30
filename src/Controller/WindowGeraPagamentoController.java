package Controller;

import Model.Entities.Ticket.TicketCliente;
import Model.Entities.Ticket.TicketMensalista;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    @FXML
    private void initialize() {
        btnGeraPagamento.setDisable(true);
    }
    public void gerarPagamento(ActionEvent actionEvent) throws SQLException {
        if(ticketUseCase.isMensalistaTicket(tfCpfPagamento.getText())){
            TicketMensalista ticket = ticketUseCase.getOpenMensalistaTicketByCpf(tfCpfPagamento.getText());
            ticketUseCase.saidaMensalista(tfCpfPagamento.getText());
            ticketUseCase.generateTicketPdf(ticketUseCase.getMensalistaTicketById(ticket.getId()));
            ((Stage)btnGeraPagamento.getScene().getWindow()).close();
        } else if (ticketUseCase.isClienteTicket(tfCpfPagamento.getText())){
            TicketCliente ticket = ticketUseCase.getOpenClienteTicketByCpf(tfCpfPagamento.getText());
            ticketUseCase.pagamentoCliente(tfCpfPagamento.getText());
            vagasUseCase.setVagaFree(tfCpfPagamento.getText());
            ticketUseCase.generateTicketPdf(ticketUseCase.getClienteTicketById(ticket.getId()));
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
        Ticket ticket = null;

        try {

            if (ticketUseCase.isClienteTicket(cpf)) {
                ticket = ticketUseCase.getOpenClienteTicketByCpf(cpf);

            }
            else if(ticketUseCase.isMensalistaTicket(tfCpfPagamento.getText())){
                ticket = ticketUseCase.getOpenMensalistaTicketByCpf(cpf);

            }
            else {
                errorLabel.setText("Ticket não encontrado para o cpf informado.");
            }

            if(ticket != null){
                Date now = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                String horarioSaida = dateFormat.format(now);
                String horarioEntrada = dateFormat.format(ticket.getHorarioEntrada());
                tfHorarioEntrada.setText(horarioEntrada);
                tfHorarioSaida.setText(horarioSaida);
                tfPlaca.setText(ticket.getPlaca());
                btnGeraPagamento.setDisable(false);
            }

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
