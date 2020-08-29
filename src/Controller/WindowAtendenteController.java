package Controller;

import Model.Entities.Funcionarios.Atendente;
import Model.Entities.Funcionarios.Vigilante;
import Model.Entities.Precos.Precos;
import Model.UseCases.AlteraPrecosUseCase;
import Model.UseCases.AtualizaVigilanteUseCase;
import View.loaders.*;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    AtualizaVigilanteUseCase atualizaVigilanteUseCase = new AtualizaVigilanteUseCase();

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
}