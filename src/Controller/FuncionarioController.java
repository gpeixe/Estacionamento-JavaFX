package Controller;

import Model.Entities.Funcionarios.*;
import Model.Entities.Mensalista.Mensalista;
import Model.UseCases.FuncionarioUseCase;
import Utils.MaskFieldUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class FuncionarioController {
    @FXML
    JFXButton btnCancelar;
    @FXML
    JFXTextField tfEnderecoFuncionario;
    @FXML
    JFXTextField tfTelefoneFuncionario;
    @FXML
    JFXTextField tfNomeFuncionario;
    @FXML
    JFXTextField tfCPFFuncionario;
    @FXML
    JFXComboBox<String> cbFuncFuncionario;
    @FXML
    JFXTextField tfSenhaFuncionario;

    private Funcionario funcionarioFuncionario;

    public void salvarFuncionario(ActionEvent actionEvent) throws SQLException {
        FuncionarioUseCase funcionarioUseCase = new FuncionarioUseCase();
        if(funcionarioFuncionario!=null){
            switch (cbFuncFuncionario.getSelectionModel().getSelectedItem()) {
                case "Atendente":
                    funcionarioUseCase.update(new Atendente(tfCPFFuncionario.getText(), tfNomeFuncionario.getText(), tfSenhaFuncionario.getText(), tfTelefoneFuncionario.getText(), tfEnderecoFuncionario.getText(), Efuncao.ATENDENTE, funcionarioFuncionario.getId()));
                    break;
                case "Vigilante":
                    funcionarioUseCase.update(new Vigilante(tfCPFFuncionario.getText(), tfNomeFuncionario.getText(), tfSenhaFuncionario.getText(), tfTelefoneFuncionario.getText(), tfEnderecoFuncionario.getText(), Efuncao.VIGILANTE, funcionarioFuncionario.getId()));
                    break;
                case "Administrador":
                    funcionarioUseCase.update(new Administrador(tfCPFFuncionario.getText(), tfNomeFuncionario.getText(), tfSenhaFuncionario.getText(), tfTelefoneFuncionario.getText(), tfEnderecoFuncionario.getText(), Efuncao.ADMIN, funcionarioFuncionario.getId()));
                    break;
            }
        }   else{
            switch (cbFuncFuncionario.getSelectionModel().getSelectedItem()) {
                case "Atendente":
                    funcionarioUseCase.save(new Atendente(tfCPFFuncionario.getText(), tfNomeFuncionario.getText(), tfSenhaFuncionario.getText(), tfTelefoneFuncionario.getText(), tfEnderecoFuncionario.getText(), Efuncao.ATENDENTE));
                    break;
                case "Vigilante":
                    funcionarioUseCase.save(new Vigilante(tfCPFFuncionario.getText(), tfNomeFuncionario.getText(), tfSenhaFuncionario.getText(), tfTelefoneFuncionario.getText(), tfEnderecoFuncionario.getText(), Efuncao.VIGILANTE));
                    break;
                case "Administrador":
                    funcionarioUseCase.save(new Administrador(tfCPFFuncionario.getText(), tfNomeFuncionario.getText(), tfSenhaFuncionario.getText(), tfTelefoneFuncionario.getText(), tfEnderecoFuncionario.getText(), Efuncao.ADMIN));
                    break;
            }
        }
        ((Stage)tfEnderecoFuncionario.getScene().getWindow()).close();
    }

    public void cancelaOp(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void editFuncionario(Funcionario funcionario) {
        tfCPFFuncionario.setText(funcionario.getCpf());
        tfNomeFuncionario.setText(funcionario.getNome());
        tfTelefoneFuncionario.setText(funcionario.getTelefone());
        tfEnderecoFuncionario.setText(funcionario.getEndereco());
        if(funcionario instanceof Atendente){
            cbFuncFuncionario.setValue("Atendente");
        }   else if(funcionario instanceof Vigilante){
            cbFuncFuncionario.setValue("Vigilante");
        }   else if(funcionario instanceof Administrador){
            cbFuncFuncionario.setValue("Administrador");
        }
        tfSenhaFuncionario.setText(funcionario.getSenha());
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionarioFuncionario = funcionario;
    }

    @FXML public void initialize(){
        ObservableList<String> tipos = FXCollections.observableArrayList();
        tipos.addAll("Atendente","Vigilante","Administrador");
        cbFuncFuncionario.setItems(tipos);
    }

    public void formataCpf(KeyEvent keyEvent) {
        MaskFieldUtil.cpfField(tfCPFFuncionario);
    }

    public void formataTelefone(KeyEvent keyEvent) {
        MaskFieldUtil.foneField(tfTelefoneFuncionario);
    }
}
