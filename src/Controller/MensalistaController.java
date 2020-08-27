package Controller;

import Model.Entities.Mensalista.Mensalista;
import Model.UseCases.LoginUseCase;
import Model.UseCases.MensalistaUseCase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private Mensalista mensalistaMensalista;

    public void salvarMensalista(ActionEvent actionEvent) throws SQLException {
        MensalistaUseCase mensalistaUseCase = new MensalistaUseCase();
        if(mensalistaMensalista != null){
            Mensalista mensalista = new Mensalista(tfCPFMensalista.getText(),tfNomeMensalista.getText(),tfTelefoneMensalista.getText(),tfEmpresaVincMensalista.getText(),Integer.parseInt(tfVagaOcupada.getText()), mensalistaMensalista.getId());
            mensalistaUseCase.update(mensalista);
        }   else {
            Mensalista mensalista = new Mensalista(tfCPFMensalista.getText(),tfNomeMensalista.getText(),tfTelefoneMensalista.getText(),tfEmpresaVincMensalista.getText(),Integer.parseInt(tfVagaOcupada.getText()));
            mensalistaUseCase.save(mensalista);
        }
        ((Stage)tfCPFMensalista.getScene().getWindow()).close();
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

    public void setMensalista(Mensalista mensalista) {
        this.mensalistaMensalista = mensalista;
    }
}
