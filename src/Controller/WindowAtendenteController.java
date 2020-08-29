package Controller;

import Model.Entities.Funcionarios.Atendente;
import Model.Entities.Funcionarios.Vigilante;
import Model.Entities.Mensalista.Mensalista;
import Model.Entities.Precos.Precos;
import Model.Entities.Vagas.Vagas;
import Model.UseCases.AlteraPrecosUseCase;
import Model.UseCases.AtualizaVigilanteUseCase;
import Model.UseCases.FuncionarioUseCase;
import Model.UseCases.VagasUseCase;
import View.loaders.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;


public class WindowAtendenteController {
    private Atendente atd;
    private Vigilante currentVigilante;

    @FXML
    JFXButton btnDeslogar;
    @FXML
    Label labelAtendente;
    @FXML
    Label labelVigilante;
    @FXML
    Label labelTrintaMinutos;
    @FXML
    Label labelUmaHora;
    @FXML
    Label labelDemaisHoras;
    @FXML
    Label labelTaxaPerNoite;
    @FXML
    Label labelTAxaMensalista;
    @FXML
    Label lblNumVagasDisponives;
    @FXML
    Label lblNumVagas;
    @FXML
    TableView<Vagas> carrosEstacionadosTable;
    @FXML
    TableColumn<Vagas, String> vagaColum;
    @FXML
    TableColumn<Vagas, String> cpfColum;

    AtualizaVigilanteUseCase atualizaVigilanteUseCase = new AtualizaVigilanteUseCase();

    private ObservableList<Vagas> values;

    public void openTelaTicket(ActionEvent actionEvent) {
        WindowTicket w = new WindowTicket();
        w.startModal();
    }

    public void openTelaEntradaVigilante(ActionEvent actionEvent) {
        WindowLogin w = new WindowLogin();
        w.startModal(true);
        setCurrentVigilante(atualizaVigilanteUseCase.getCurrentVigilante());
    }

    public void openTelaCRUDMensalista(ActionEvent actionEvent) {
        WindowCrudMensalista w = new WindowCrudMensalista();
        w.startModal();
    }

    public void deslogarFunc(ActionEvent actionEvent) {
        Stage stage = (Stage) btnDeslogar.getScene().getWindow();
        stage.close();
    }

    public void openTelaEntradaMensalista(ActionEvent actionEvent) {
        WindowEntradaMensalista w = new WindowEntradaMensalista();
        w.startModal();
    }

    public void registraSaida(ActionEvent actionEvent) {
        WindowRegistraSaida w = new WindowRegistraSaida();
        w.startModal();
    }

    public void gerarPagamento(ActionEvent actionEvent) {
    }

    public void setAtendente(Atendente atd) {
        this.atd = atd;
        labelAtendente.setText(labelAtendente.getText() + " " + atd.getNome());
    }

    public void setCurrentVigilante(Vigilante newVig) {
        if(newVig != null){
            this.currentVigilante = newVig;
            labelVigilante.setText("Vigilante:" + " " + newVig.getNome());
        }   else{
            Vigilante vigilante = atualizaVigilanteUseCase.getCurrentVigilante();
            labelVigilante.setText(labelVigilante.getText() + " " + vigilante.getNome());
        }
    }

    public void setLabelPrecos() throws SQLException {
        AlteraPrecosUseCase alteraPrecosUseCase = new AlteraPrecosUseCase();
        Precos precos = alteraPrecosUseCase.read();
        if(precos != null){
            labelTrintaMinutos.setText(labelTrintaMinutos.getText()+" R$ "+precos.getPreco30min());
            labelUmaHora.setText(labelUmaHora.getText()+" R$ "+precos.getPreco1hr());
            labelDemaisHoras.setText(labelDemaisHoras.getText()+" R$ "+precos.getPrecoDemaisHoras());
            labelTaxaPerNoite.setText(labelTaxaPerNoite.getText()+" R$ "+precos.getPrecoPerNoite());
            labelTAxaMensalista.setText(labelTAxaMensalista.getText()+" R$ "+precos.getPrecoMensalista());
        }   else{
            labelTrintaMinutos.setText(labelTrintaMinutos.getText()+" Não definido");
            labelUmaHora.setText(labelUmaHora.getText()+" Não definido");
            labelDemaisHoras.setText(labelDemaisHoras.getText()+" Não definido");
            labelTaxaPerNoite.setText(labelTaxaPerNoite.getText()+" Não definido");
            labelTAxaMensalista.setText(labelTAxaMensalista.getText()+" Não definido");
        }

    }

    public void setVagasDisponiveis() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();
        lblNumVagasDisponives.setText(lblNumVagasDisponives.getText()+" "+vagasUseCase.numeroVagasDisponiveis());
    }

    public void setVagasTotais() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();
        lblNumVagas.setText(lblNumVagas.getText()+" "+vagasUseCase.numeroVagasTotais());
    }

    @FXML
    private void initialize() throws SQLException {
        vagaColum.setCellValueFactory(new PropertyValueFactory<>("id_vaga"));
        cpfColum.setCellValueFactory(new PropertyValueFactory<>("cpf_ocupante"));
        values = FXCollections.observableArrayList();
        carrosEstacionadosTable.setItems(values);
        loadTableView();
    }

    private void loadTableView() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();
        values.setAll(vagasUseCase.readAll());
    }

    public void deslogarVigilante(ActionEvent actionEvent) {
    }
}