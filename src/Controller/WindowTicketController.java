package Controller;

import Model.Entities.Funcionarios.Vigilante;
import Model.Entities.Mensalista.Mensalista;
import Model.Entities.Ticket.TicketCliente;
import Model.Entities.Ticket.TicketMensalista;
import Model.UseCases.AtualizaVigilanteUseCase;
import Model.UseCases.MensalistaUseCase;
import Model.UseCases.TicketUseCase;
import Utils.MaskFieldUtil;
import View.loaders.WindowEntradaMensalista;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

public class WindowTicketController {

    @FXML
    JFXButton btnCancelaTicket;

    @FXML
    JFXButton btnGeraTicketMensalista;

    @FXML
    JFXButton btnGerarTicket;

    @FXML
    JFXTextArea taDescCarroCliente;

    @FXML
    JFXTextField tfContatoCliente;

    @FXML
    JFXTextField tfPlacaCliente;

    @FXML
    JFXCheckBox cbTaxaPerNoite;

    @FXML
    JFXTextField tftCPFEntradaMensalista;

    @FXML
    JFXTextField tftPlacaMensalista;

    @FXML
    JFXTextArea taDescCarroMensalista;

    @FXML
    JFXTextField tfCpfCliente;

    private TicketUseCase ticketUseCase = new TicketUseCase();
    private AtualizaVigilanteUseCase atualizaVigilanteUseCase = new AtualizaVigilanteUseCase();
    private MensalistaUseCase mensalistaUseCase = new MensalistaUseCase();

    public void geraTicket(ActionEvent actionEvent) throws SQLException {
        Vigilante vigilante = atualizaVigilanteUseCase.getCurrentVigilante();
        TicketCliente ticketCliente = new TicketCliente(
                tfPlacaCliente.getText(),
                new Date(),
                null,
                taDescCarroCliente.getText(),
                vigilante.getId());
        ticketCliente.setTelefone(tfContatoCliente.getText());
        if (cbTaxaPerNoite.isSelected()) {
            ticketCliente.setPernoite(true);
        } else {
            ticketCliente.setPernoite(false);
        }
        ticketCliente.setCpf(tfCpfCliente.getText());

        ticketUseCase.saveClientTicket(ticketCliente);
        ((Stage)btnGerarTicket.getScene().getWindow()).close();
    }

    public void cancelaTicket(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancelaTicket.getScene().getWindow();
        stage.close();
    }

    public void geraTicketMensalista(ActionEvent actionEvent) throws SQLException {
        Vigilante vigilante = atualizaVigilanteUseCase.getCurrentVigilante();
        Mensalista mensalista = mensalistaUseCase.getMensalistaByCpf(tftCPFEntradaMensalista.getText());
        TicketMensalista ticketMensalista = new TicketMensalista(
                tftPlacaMensalista.getText(),
                new Date(),
                null,
                taDescCarroMensalista.getText(),
                vigilante.getId());
        ticketMensalista.setIdMensalista(mensalista.getId());
        ticketUseCase.saveMensalistaTicket(ticketMensalista);
        ((Stage)btnGeraTicketMensalista.getScene().getWindow()).close();
    }

    public void formatterCpf(KeyEvent keyEvent) {
        MaskFieldUtil.cpfField(tftCPFEntradaMensalista);
    }

    public void formatterCpfClient(KeyEvent keyEvent) {
        MaskFieldUtil.cpfField(tfCpfCliente);
    }
}
