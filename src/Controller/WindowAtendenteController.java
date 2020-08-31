package Controller;

import Model.Entities.Funcionarios.Atendente;
import Model.Entities.Funcionarios.Vigilante;
import Model.Entities.Precos.Precos;
import Model.Entities.Vagas.Vagas;
import Model.UseCases.*;
import Utils.MaskFieldUtil;
import View.loaders.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    @FXML
    PieChart graphVagas;

    @FXML
    JFXTextField filterCpf;

    RegistroHoraVigilanteUseCase registroHoraVigilanteUseCase;
    RegistroHoraFuncionarioUseCase registroAtendenteUseCase;

    private ObservableList<Vagas> values;

    public void openTelaTicket(ActionEvent actionEvent) throws SQLException {
        WindowTicket w = new WindowTicket();
        w.startModal();
        reloader();
    }

    public void openTelaEntradaVigilante(ActionEvent actionEvent) {
        WindowLogin w = new WindowLogin();
        w.startModal(true);
        setCurrentVigilante(registroHoraVigilanteUseCase.getCurrentVigilante());
    }

    public void openTelaCRUDMensalista(ActionEvent actionEvent) throws SQLException {
        WindowCrudMensalista w = new WindowCrudMensalista();
        w.startModal();
        reloader();
    }

    public void openTelaPagamento(ActionEvent actionEvent) throws SQLException {
        WindowGeraPagamento windowGeraPagamento = new WindowGeraPagamento();
        windowGeraPagamento.startModal(null);
        reloader();
    }

    public void openTelaEntradaMensalista(ActionEvent actionEvent) {
        WindowEntradaMensalista w = new WindowEntradaMensalista();
        w.startModal();
    }

    public void deslogarFunc(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) btnDeslogar.getScene().getWindow();
        registroAtendenteUseCase.registrarSaida(this.atd);
        stage.close();
        WindowStart windowStart = new WindowStart();
        windowStart.start(new Stage());
    }

    public void setAtendente(Atendente atd) {
        this.atd = atd;
        labelAtendente.setText(labelAtendente.getText() + " " + atd.getNome());
        this.registroAtendenteUseCase.registrarEntrada(this.atd);
    }

    public void setCurrentVigilante(Vigilante newVig) {
        if(newVig != null){
            this.currentVigilante = newVig;
            labelVigilante.setText("Vigilante:" + " " + newVig.getNome());
        }   else{
            this.currentVigilante = null;
            labelVigilante.setText("Vigilante:");
        }
    }

    public void setLabelPrecos() throws SQLException {
        PrecosUseCase precosUseCase = new PrecosUseCase();
        Precos precos = precosUseCase.read();
        if(precos != null){
            labelTrintaMinutos.setText("R$ "+precos.getPreco30min());
            labelUmaHora.setText("R$ "+precos.getPreco1hr());
            labelDemaisHoras.setText("R$ "+precos.getPrecoDemaisHoras());
            labelTaxaPerNoite.setText("R$ "+precos.getPrecoPerNoite());
            labelTAxaMensalista.setText("R$ "+precos.getPrecoMensalista());
        }   else{
            labelTrintaMinutos.setText("Não definido");
            labelUmaHora.setText("Não definido");
            labelDemaisHoras.setText("Não definido");
            labelTaxaPerNoite.setText("Não definido");
            labelTAxaMensalista.setText("Não definido");
        }

    }

    public void setVagasDisponiveis() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();
        lblNumVagasDisponives.setText("Vagas Disponíveis: "+vagasUseCase.numeroVagasDisponiveis());
    }

    public void setVagasTotais() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();
        lblNumVagas.setText(lblNumVagas.getText()+" "+vagasUseCase.numeroVagasTotais());
    }

    public void setGraphVagas() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();
        graphVagas.getData().clear();
        graphVagas.getData().addAll(new PieChart.Data("Vagas Totais", vagasUseCase.numeroVagasTotais()),
                new PieChart.Data("Vagas Disponiveis", vagasUseCase.numeroVagasDisponiveis()));
    }

    @FXML
    private void initialize() throws SQLException {
        vagaColum.setCellValueFactory(new PropertyValueFactory<>("id_vaga"));
        cpfColum.setCellValueFactory(new PropertyValueFactory<>("cpf_ocupante"));
        values = FXCollections.observableArrayList();
        carrosEstacionadosTable.setItems(values);
        this.registroHoraVigilanteUseCase = new RegistroHoraVigilanteUseCase();
        this.registroAtendenteUseCase = new RegistroHoraFuncionarioUseCase();
        setCurrentVigilante(registroHoraVigilanteUseCase.getCurrentVigilante());
        loadTableView();
    }

    private void loadTableView() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();

        values.setAll(vagasUseCase.readAll());

        FilteredList<Vagas> filteredData = new FilteredList<>(values, b ->  true);

        filterCpf.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredData.setPredicate(vaga -> {

                if(newValue == null || newValue.isEmpty())
                    return  true;

                String lowerCaseFilter = newValue.toLowerCase();

                if(vaga.getCpf_ocupante().indexOf(lowerCaseFilter) != -1){
                    return  true;
                }
                else
                    return false;
            });

            SortedList<Vagas> sortedData = new SortedList<>(filteredData);

            carrosEstacionadosTable.setItems(sortedData);

        }));

    }

    public void deslogarVigilante(ActionEvent actionEvent) {
        if(this.currentVigilante != null){
            this.registroHoraVigilanteUseCase.registrarSaida(this.currentVigilante);
            setCurrentVigilante(null);
        }
    }

    public void reloader() throws SQLException {
        loadTableView();
        setVagasDisponiveis();
        setGraphVagas();
    }


    public void searchCpfFormatter(KeyEvent keyEvent) {
        MaskFieldUtil.cpfField(filterCpf);
    }

    public void abrirPagamento(MouseEvent mouseEvent) throws SQLException {
        String cpf = carrosEstacionadosTable.getSelectionModel().getSelectedItem().getCpf_ocupante();
        if(cpf.equals("Disponível"))
            return;
        WindowGeraPagamento windowGeraPagamento = new WindowGeraPagamento();
        windowGeraPagamento.startModal(cpf);
        reloader();
    }
}