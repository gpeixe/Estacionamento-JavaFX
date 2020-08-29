package Controller;

import Model.Entities.Funcionarios.Vigilante;
import Model.Entities.Mensalista.Mensalista;
import Model.Entities.Ticket.TicketCliente;
import Model.Entities.Ticket.TicketMensalista;
import Model.Entities.Vagas.Vagas;
import Model.UseCases.MensalistaUseCase;
import Model.UseCases.RegistroVigilanteUseCase;
import Model.UseCases.TicketUseCase;
import Model.UseCases.VagasUseCase;
import Utils.MaskFieldUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

    @FXML
    JFXTextField tfVagaOcupada;

    @FXML
    TableView<Vagas> vagasDisponiveisTable;

    @FXML
    TableColumn<Vagas, Integer> vagasDisponiveisColum;

    private ObservableList<Vagas> values;
    private TicketUseCase ticketUseCase = new TicketUseCase();
    private RegistroVigilanteUseCase registroVigilanteUseCase = new RegistroVigilanteUseCase();
    private MensalistaUseCase mensalistaUseCase = new MensalistaUseCase();

    public void geraTicket(ActionEvent actionEvent) throws SQLException {
        Vigilante vigilante = registroVigilanteUseCase.getCurrentVigilante();
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

        /*Salvando a vaga*/
        VagasUseCase vagasUseCase = new VagasUseCase();
        vagasUseCase.setVaga(tfCpfCliente.getText(),Integer.parseInt(tfVagaOcupada.getText()));

        /*Fecha a aba*/
        ((Stage)btnGerarTicket.getScene().getWindow()).close();
    }

    public void cancelaTicket(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancelaTicket.getScene().getWindow();
        stage.close();
    }

    public void geraTicketMensalista(ActionEvent actionEvent) throws SQLException {
        Vigilante vigilante = registroVigilanteUseCase.getCurrentVigilante();
        Mensalista mensalista = mensalistaUseCase.getMensalistaByCpf(tftCPFEntradaMensalista.getText());
        TicketMensalista ticketMensalista = new TicketMensalista(
                tftPlacaMensalista.getText(),
                new Date(),
                null,
                taDescCarroMensalista.getText(),
                vigilante.getId(),
                mensalista.getId());
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

    public void formatterTellClient(KeyEvent keyEvent) {
        MaskFieldUtil.foneField(tfContatoCliente);
    }

    @FXML
    private void initialize() throws SQLException {
        vagasDisponiveisColum.setCellValueFactory(new PropertyValueFactory<>("id_vaga"));
        values = FXCollections.observableArrayList();
        vagasDisponiveisTable.setItems(values);
        loadTableView();
    }

    private void loadTableView() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();
        values.setAll(vagasUseCase.readAll());
    }

    public void selecionaVaga(MouseEvent mouseEvent) {
        tfVagaOcupada.setText(String.valueOf(vagasDisponiveisTable.getSelectionModel().getSelectedItem().getId_vaga()));
    }

}
