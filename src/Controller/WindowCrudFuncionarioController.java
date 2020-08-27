package Controller;

import Model.Entities.Funcionarios.Funcionario;
import Model.Entities.Mensalista.Mensalista;
import Model.UseCases.FuncionarioUseCase;
import Model.UseCases.MensalistaUseCase;
import View.loaders.WindowFuncionario;
import View.loaders.WindowMensalista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;


public class WindowCrudFuncionarioController {
    @FXML
    TableView<Funcionario> tableFuncionario;
    @FXML
    TableColumn<Funcionario, String> funcaoColum;
    @FXML
    TableColumn<Funcionario, String> telefoneColum;
    @FXML
    TableColumn<Funcionario, String> enderecoColum;
    @FXML
    TableColumn<Funcionario, String> nomeColum;
    @FXML
    TableColumn<Funcionario, String> cpfColum;

    private ObservableList<Funcionario> values;
    private Funcionario funcionario;

    public void telaNovoFuncionario(ActionEvent actionEvent) throws SQLException {
        WindowFuncionario w = new WindowFuncionario();
        w.startModal(null);
        loadTableView();
    }

    public void editaFuncionario(ActionEvent actionEvent) throws SQLException {
        WindowFuncionario w = new WindowFuncionario();
        w.startModal(tableFuncionario.getSelectionModel().getSelectedItem());
        loadTableView();
    }

    public void removeFuncionario(ActionEvent actionEvent) throws SQLException {
        FuncionarioUseCase funcionarioUseCase = new FuncionarioUseCase();
        funcionarioUseCase.delete(tableFuncionario.getSelectionModel().getSelectedItem().getId());
        loadTableView();
    }

    @FXML
    private void initialize() throws SQLException {
        cpfColum.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        nomeColum.setCellValueFactory(new PropertyValueFactory<>("nome"));
        funcaoColum.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        telefoneColum.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        enderecoColum.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        values = FXCollections.observableArrayList();
        tableFuncionario.setItems(values);
        loadTableView();
    }

    private void loadTableView() throws SQLException {
        FuncionarioUseCase funcionarioUseCase = new FuncionarioUseCase();
        values.setAll(funcionarioUseCase.readAll());
    }
}
