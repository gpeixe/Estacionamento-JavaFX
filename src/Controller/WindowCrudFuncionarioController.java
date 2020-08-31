package Controller;

import Model.Entities.Funcionarios.Funcionario;
import Model.UseCases.FuncionarioCRUDUseCase;
import View.loaders.WindowFuncionario;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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

    @FXML
    JFXTextField tfBuscaFuncionario;

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
        FuncionarioCRUDUseCase funcionarioCRUDUseCase = new FuncionarioCRUDUseCase();
        funcionarioCRUDUseCase.delete(tableFuncionario.getSelectionModel().getSelectedItem().getId());
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
        FuncionarioCRUDUseCase funcionarioCRUDUseCase = new FuncionarioCRUDUseCase();
        values.setAll(funcionarioCRUDUseCase.readAll());

        FilteredList<Funcionario> filteredData = new FilteredList<>(values, b ->  true);

        tfBuscaFuncionario.textProperty().addListener(((observable, oldValue, newValue) -> {
                    filteredData.setPredicate(funcionario -> {

                        if (newValue == null || newValue.isEmpty())
                            return true;

                        String lowerCaseFilter = newValue.toLowerCase();

                        if (funcionario.getCpf().indexOf(lowerCaseFilter) != -1
                                || funcionario.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1
                                || funcionario.getTelefone().indexOf(lowerCaseFilter) != -1
                                || funcionario.getFuncao().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else
                            return false;
                    });
                }));

            tableFuncionario.setItems(filteredData);
    }
}
