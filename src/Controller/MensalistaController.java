package Controller;

import Model.Entities.Mensalista.Mensalista;
import Model.Entities.Vagas.Vagas;
import Model.UseCases.MensalistaCRUDUseCase;
import Model.UseCases.VagasUseCase;
import Utils.MaskFieldUtil;
import Utils.ValidaCPF;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class MensalistaController {
    @FXML
    JFXButton btnCancelar;
    @FXML
    JFXTextField tfCPFMensalista;
    @FXML
    JFXTextField tfNomeMensalista;
    @FXML
    JFXTextField tfTelefoneMensalista;
    @FXML
    JFXTextField tfEmpresaVincMensalista;
    @FXML
    JFXTextField tfVagaOcupada;
    @FXML
    TableView<Vagas> vagasDisponiveisTable;
    @FXML
    TableColumn<Vagas, Integer> VagasDisponiveisColum;
    @FXML
    Label lblAviso;

    private ObservableList<Vagas> values;
    private Mensalista mensalistaMensalista;

    public void salvarMensalista(ActionEvent actionEvent) throws SQLException {
        String cpf = tfCPFMensalista.getText();
        String nome = tfNomeMensalista.getText();
        String vaga = tfVagaOcupada.getText();
        String empresa = tfEmpresaVincMensalista.getText();
        String telefone = tfTelefoneMensalista.getText();
        MensalistaCRUDUseCase mensalistaCRUDUseCase = new MensalistaCRUDUseCase();

        if(ValidaCPF.isCPF(cpf) && mensalistaCRUDUseCase.verificaCadastrado(cpf)){ //Valida CPF
            if(!nome.equals("") && !vaga.equals("") && !telefone.equals("") && telefone.length()>=13){
                if(mensalistaMensalista != null){
                    Mensalista mensalista = new Mensalista(cpf,nome,telefone,empresa,Integer.parseInt(vaga), mensalistaMensalista.getId());
                    mensalistaCRUDUseCase.update(mensalista);
                }   else {
                    Mensalista mensalista = new Mensalista(cpf,nome,telefone,empresa,Integer.parseInt(vaga));
                    mensalistaCRUDUseCase.save(mensalista);
                }
                ((Stage)tfCPFMensalista.getScene().getWindow()).close();
            }   else{
                lblAviso.setText("Por favor, preencha todos os campos!");
            }
        }   else{
            lblAviso.setText("CPF Inválido ou Já Cadastrado!");
        }

    }

    public void editaMensalista(Mensalista mensalista){
        tfCPFMensalista.setText(mensalista.getCpf());
        tfNomeMensalista.setText(mensalista.getNome());
        tfTelefoneMensalista.setText(mensalista.getTelefone());
        tfEmpresaVincMensalista.setText(mensalista.getEmpresa());
        tfVagaOcupada.setText(String.valueOf(mensalista.getVagaOcupada()));
    }

    public void cancelaOp(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize() throws SQLException {
        VagasDisponiveisColum.setCellValueFactory(new PropertyValueFactory<>("id_vaga"));
        values = FXCollections.observableArrayList();
        vagasDisponiveisTable.setItems(values);
        loadTableView();
    }

    private void loadTableView() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();
        values.setAll(vagasUseCase.readAllLivres());
    }

    public void setMensalista(Mensalista mensalista) {
        this.mensalistaMensalista = mensalista;
    }

    public void formatarCPF(KeyEvent keyEvent) {
        MaskFieldUtil.cpfField(tfCPFMensalista);
    }

    public void formataTelefone(KeyEvent keyEvent) {
        MaskFieldUtil.foneField(tfTelefoneMensalista);
    }

    public void preencheVagaOcupada(MouseEvent mouseEvent) {
        tfVagaOcupada.setText(String.valueOf(vagasDisponiveisTable.getSelectionModel().getSelectedItem().getId_vaga()));
    }
}
