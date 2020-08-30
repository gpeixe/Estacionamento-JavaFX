package Controller;

import Model.Entities.Funcionarios.Funcionario;
import Model.Entities.Precos.Precos;
import Model.UseCases.AlteraPrecosUseCase;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;

public class PrecoController {
    @FXML
    JFXTextField tfTaxa30min;
    @FXML
    JFXTextField tfTaxa1hora;
    @FXML
    JFXTextField tfTaxaDemaisHrs;
    @FXML
    JFXTextField tfTaxaPernoite;
    @FXML
    JFXTextField tfTaxaMensalista;
    @FXML
    Label lblAviso;

    private Precos preco;
    AlteraPrecosUseCase alteraPrecosUseCase = new AlteraPrecosUseCase();

    public void salvaPrecosEstacionamento() throws SQLException{
        String taxa30min = tfTaxa30min.getText();
        String taxa1hr = tfTaxa1hora.getText();
        String taxaDemais = tfTaxaDemaisHrs.getText();
        String taxaPerNoite = tfTaxaPernoite.getText();
        String taxaMensalista = tfTaxaMensalista.getText();

        if (!taxa30min.equals("") && !taxa1hr.equals("") && !taxaDemais.equals("") && !taxaPerNoite.equals("") && !taxaMensalista.equals("")){
            try {
                if(preco!=null){
                    alteraPrecosUseCase.update(new Precos(Double.parseDouble(taxa30min) , Double.parseDouble(taxa1hr), Double.parseDouble(taxaDemais), Double.parseDouble(taxaPerNoite), Double.parseDouble(taxaMensalista)));
                }   else{
                    alteraPrecosUseCase.save(new Precos(Double.parseDouble(taxa30min) , Double.parseDouble(taxa1hr), Double.parseDouble(taxaDemais), Double.parseDouble(taxaPerNoite), Double.parseDouble(taxaMensalista)));
                }
                ((Stage)tfTaxaDemaisHrs.getScene().getWindow()).close();
            }   catch (Exception e){
                lblAviso.setText("Preenchimento Inválido!");
            }
        }   else{
            lblAviso.setText("Preencha todos os campos!");
        }
    }

    public Precos readPrecos() throws SQLException {
        return alteraPrecosUseCase.read();
    }

    public void editaPrecos(Precos precos) throws SQLException {
        tfTaxa30min.setText(precos.getPreco30min().toString());
        tfTaxa1hora.setText(precos.getPreco1hr().toString());
        tfTaxaDemaisHrs.setText(precos.getPrecoDemaisHoras().toString());
        tfTaxaPernoite.setText(precos.getPrecoPerNoite().toString());
        tfTaxaMensalista.setText(precos.getPrecoMensalista().toString());
    }

    public void setPreco(Precos preco) {
        this.preco = preco;
    }
}
