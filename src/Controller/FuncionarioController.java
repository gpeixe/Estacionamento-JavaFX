package Controller;

import Model.Entities.Funcionarios.*;
import Model.Entities.Mensalista.Mensalista;
import Model.UseCases.FuncionarioUseCase;
import Utils.MaskFieldUtil;
import Utils.ValidaCPF;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    @FXML
    Label lblAviso;

    private Funcionario funcionarioFuncionario;

    public void salvarFuncionario(ActionEvent actionEvent) throws SQLException {
        String cpf = tfCPFFuncionario.getText();
        String nome = tfNomeFuncionario.getText();
        String senha = tfSenhaFuncionario.getText();
        String endereco = tfEnderecoFuncionario.getText();
        String telefone = tfTelefoneFuncionario.getText();
        FuncionarioUseCase funcionarioUseCase = new FuncionarioUseCase();

        if(ValidaCPF.isCPF(cpf) && funcionarioUseCase.verificaCadastrado(cpf)){ //Valida se o CPF é válido
            if(!nome.equals("") && !senha.equals("") && !endereco.equals("") && !telefone.equals("") && telefone.length()>=13){ //Valida se todos os campos foram preenchidos
                if(funcionarioFuncionario!=null){
                    switch (cbFuncFuncionario.getSelectionModel().getSelectedItem()) {
                        case "Atendente":
                            funcionarioUseCase.update(new Atendente(cpf, nome, senha,telefone, endereco, Efuncao.ATENDENTE, funcionarioFuncionario.getId()));
                            break;
                        case "Vigilante":
                            funcionarioUseCase.update(new Vigilante(cpf, nome, senha,telefone, endereco, Efuncao.VIGILANTE, funcionarioFuncionario.getId()));
                            break;
                        case "Administrador":
                            funcionarioUseCase.update(new Administrador(cpf, nome, senha,telefone, endereco, Efuncao.ADMIN, funcionarioFuncionario.getId()));
                            break;
                    }
                }   else{
                    switch (cbFuncFuncionario.getSelectionModel().getSelectedItem()) {
                        case "Atendente":
                            funcionarioUseCase.save(new Atendente(cpf, nome, senha,telefone, endereco, Efuncao.ATENDENTE));
                            break;
                        case "Vigilante":
                            funcionarioUseCase.save(new Vigilante(cpf, nome, senha,telefone, endereco, Efuncao.VIGILANTE));
                            break;
                        case "Administrador":
                            funcionarioUseCase.save(new Administrador(cpf, nome, senha,telefone, endereco, Efuncao.ADMIN));
                            break;
                    }
                }
                ((Stage)tfEnderecoFuncionario.getScene().getWindow()).close();
            }   else{
                lblAviso.setText("Por favor, preencha todos os campos!");
            }
        }   else{
            lblAviso.setText("CPF Inválido ou Já Cadastrado!");
        }
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
        cbFuncFuncionario.setValue("Atendente");
    }

    public void formataCpf(KeyEvent keyEvent) {
        MaskFieldUtil.cpfField(tfCPFFuncionario);
    }

    public void formataTelefone(KeyEvent keyEvent) {
        MaskFieldUtil.foneField(tfTelefoneFuncionario);
    }
}
