package Controller;

import Model.Entities.Funcionarios.Vigilante;
import Model.Entities.Mensalista.Mensalista;
import Model.Entities.Ticket.TicketMensalista;
import Model.UseCases.MensalistaCRUDUseCase;
import Model.UseCases.RegistroHoraVigilanteUseCase;
import Model.UseCases.TicketUseCase;
import Utils.MaskFieldUtil;
import Utils.ValidaCPF;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Date;

public class WindowEntradaMensalistaController {
    @FXML
    JFXTextField tftCPFEntradaMensalista;

    @FXML
    JFXTextField tftPlacaMensalista;

    @FXML
    JFXTextArea taDescCarroMensalista;

    @FXML
    JFXButton btnGeraTicketMensalista;

    @FXML
    Label lblAviso;

    private RegistroHoraVigilanteUseCase registroHoraVigilanteUseCase = new RegistroHoraVigilanteUseCase();
    private MensalistaCRUDUseCase mensalistaCRUDUseCase = new MensalistaCRUDUseCase();
    private TicketUseCase ticketUseCase = new TicketUseCase();

    public void geraTicketMensalista(ActionEvent actionEvent) throws SQLException {
        String cpf = tftCPFEntradaMensalista.getText();
        if(ValidaCPF.isCPF(cpf)){
            Vigilante vigilante = registroHoraVigilanteUseCase.getCurrentVigilante();
            if(vigilante!=null){
                if (!tftPlacaMensalista.getText().equals("") && !taDescCarroMensalista.getText().equals("")) {
                    Mensalista mensalista = mensalistaCRUDUseCase.getMensalistaByCpf(cpf);
                    TicketMensalista ticketMensalista = new TicketMensalista(
                            tftPlacaMensalista.getText(),
                            new Date(),
                            null,
                            taDescCarroMensalista.getText(),
                            registroHoraVigilanteUseCase.getCurrentVigilante().getId(),
                            mensalista.getId());
                    ticketMensalista.setId(ticketUseCase.idProximoTicketMensalista());
                    ticketUseCase.saveMensalistaTicket(ticketMensalista);
                    /*Gera o Ticket*/
                    ticketUseCase.generateEnterTicketPdf(ticketMensalista);
                    /*Fecha a Aba*/
                    ((Stage) btnGeraTicketMensalista.getScene().getWindow()).close();
                } else {
                    lblAviso.setText("Por favor, preencha todos os campos!");
                }
            } else{
                lblAviso.setText("Nenhum vigilante em trabalho");
            }
        } else{
            lblAviso.setText("Por favor, digite um CPF válido!");
        }
    }

    public void mensalistaCpfFormatter(KeyEvent keyEvent) {
        MaskFieldUtil.cpfField(tftCPFEntradaMensalista);
    }
}
