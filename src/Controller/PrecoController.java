package Controller;

import Model.Entities.Funcionarios.Funcionario;
import Model.Entities.Precos.Precos;
import Model.UseCases.AlteraPrecosUseCase;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXTextField;
import javafx.stage.Stage;

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

    private Precos preco;
    AlteraPrecosUseCase alteraPrecosUseCase = new AlteraPrecosUseCase();

    public void salvaPrecosEstacionamento() throws SQLException{
        if(preco!=null){
            alteraPrecosUseCase.update(new Precos(Double.parseDouble(tfTaxa30min.getText()), Double.parseDouble(tfTaxa1hora.getText()), Double.parseDouble(tfTaxaDemaisHrs.getText()), Double.parseDouble(tfTaxaPernoite.getText()), Double.parseDouble(tfTaxaMensalista.getText())));
        }   else{
            alteraPrecosUseCase.save(new Precos(Double.parseDouble(tfTaxa30min.getText()), Double.parseDouble(tfTaxa1hora.getText()), Double.parseDouble(tfTaxaDemaisHrs.getText()), Double.parseDouble(tfTaxaPernoite.getText()), Double.parseDouble(tfTaxaMensalista.getText())));
        }
        ((Stage)tfTaxaDemaisHrs.getScene().getWindow()).close();
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
