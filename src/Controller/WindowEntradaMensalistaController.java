package Controller;

import Model.Entities.Funcionarios.Vigilante;
import Model.Entities.Mensalista.Mensalista;
import Model.Entities.Ticket.TicketMensalista;
import Model.UseCases.MensalistaUseCase;
import Model.UseCases.RegistroVigilanteUseCase;
import Model.UseCases.TicketUseCase;
import Utils.MaskFieldUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    private RegistroVigilanteUseCase registroVigilanteUseCase = new RegistroVigilanteUseCase();
    private MensalistaUseCase mensalistaUseCase = new MensalistaUseCase();
    private TicketUseCase ticketUseCase = new TicketUseCase();

    public void geraTicketMensalista(ActionEvent actionEvent) throws SQLException {
        Mensalista mensalista = mensalistaUseCase.getMensalistaByCpf(tftCPFEntradaMensalista.getText());
        TicketMensalista ticketMensalista = new TicketMensalista(
                tftPlacaMensalista.getText(),
                new Date(),
                null,
                taDescCarroMensalista.getText(),
                registroVigilanteUseCase.getCurrentVigilante().getId(),
                mensalista.getId());
        ticketUseCase.saveMensalistaTicket(ticketMensalista);
        ((Stage)btnGeraTicketMensalista.getScene().getWindow()).close();
    }

    public void mensalistaCpfFormatter(KeyEvent keyEvent) {
        MaskFieldUtil.cpfField(tftCPFEntradaMensalista);
    }
}
