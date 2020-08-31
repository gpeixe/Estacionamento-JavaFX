package Controller;

import Model.Entities.Funcionarios.Administrador;
import Model.UseCases.FuncionarioCRUDUseCase;
import Model.UseCases.MensalistaCRUDUseCase;
import Model.UseCases.VagasUseCase;
import View.loaders.*;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.sql.SQLException;


public class WindowAdminController {
    public Administrador adm;

    @FXML
    JFXButton btnDeslogar;
    @FXML
    Label lblNumFuncionarios;
    @FXML
    Label lblNumMensalistas;
    @FXML
    Label lblNumVagasDisponiveis;
    @FXML
    Label lblNumVagas;
    @FXML
    PieChart graphVagas;

    public void openTelaTicket(ActionEvent actionEvent) throws SQLException {
        WindowTicket w = new WindowTicket();
        w.startModal();
        reloader();
    }

    public void deslogaAdmin(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) btnDeslogar.getScene().getWindow();
        stage.close();
        WindowStart windowStart = new WindowStart();
        windowStart.start(new Stage());
    }

    public void openTelaRelatorios(ActionEvent actionEvent) {
        WindowRelatorio w = new WindowRelatorio();
        w.startModal();
    }

    public void openTelaPrecos(ActionEvent actionEvent) {
        WindowAlteraPrecos w = new WindowAlteraPrecos();
        w.startModal();
    }

    public void openTelaCRUDMensalista(ActionEvent actionEvent) throws Exception{
        WindowCrudMensalista w = new WindowCrudMensalista();
        w.startModal();
        reloader();
    }

    public void openTelaCRUDFuncionarios(ActionEvent actionEvent) throws Exception{
        WindowCrudFuncionario w = new WindowCrudFuncionario();
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

    public void setVagasDisponiveis() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();
        lblNumVagasDisponiveis.setText("Vagas Disponíveis: "+vagasUseCase.numeroVagasDisponiveis());
    }

    public void setVagasTotais() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();
        lblNumVagas.setText("Número de vagas: "+vagasUseCase.numeroVagasTotais());
    }

    public void setLblNumFuncionarios() throws SQLException {
        FuncionarioCRUDUseCase funcionarioCRUDUseCase = new FuncionarioCRUDUseCase();
        lblNumFuncionarios.setText("Número de funcionários: "+ funcionarioCRUDUseCase.numeroDeFuncionarios());
    }

    public void setLblNumMensalistas() throws SQLException {
        MensalistaCRUDUseCase mensalistaCRUDUseCase = new MensalistaCRUDUseCase();
        lblNumMensalistas.setText("Número de mensalistas: "+ mensalistaCRUDUseCase.numeroDeMensalistas());
    }

    public void setGraphVagas() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();
        graphVagas.getData().clear();
        graphVagas.getData().addAll(new PieChart.Data("Vagas Totais", vagasUseCase.numeroVagasTotais()),
                new PieChart.Data("Vagas Disponiveis", vagasUseCase.numeroVagasDisponiveis()));
    }

    public void reloader() throws SQLException {
        setVagasTotais();
        setGraphVagas();
        setVagasDisponiveis();
        setLblNumFuncionarios();
        setLblNumMensalistas();
    }

    public void setAdm(Administrador adm) {
        this.adm = adm;
    }

}