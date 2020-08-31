package Controller;

import Model.Entities.Mensalista.Mensalista;
import Model.Entities.Vagas.Vagas;
import Model.UseCases.MensalistaUseCase;
import Model.UseCases.VagasUseCase;
import View.loaders.WindowMensalista;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;


public class WindowCrudMensalistaController {
    @FXML
    TableView<Mensalista> tableMensalista;
    @FXML
    TableColumn<Mensalista, String> vagaColum;
    @FXML
    TableColumn<Mensalista, String> empresaColum;
    @FXML
    TableColumn<Mensalista, String> telefoneColum;
    @FXML
    TableColumn<Mensalista, String> nomeColum;
    @FXML
    TableColumn<Mensalista, String> cpfColum;

    @FXML
    JFXTextField tfBuscaMensalista;

    private ObservableList<Mensalista> values;
    private Mensalista mensalista;

    public void telaNovoMensalista(ActionEvent actionEvent) throws SQLException {
        WindowMensalista w = new WindowMensalista();
        w.startModal(null);
        loadTableView();
    }

    public void editaMensalista(ActionEvent actionEvent) throws SQLException {
        WindowMensalista w = new WindowMensalista();
        w.startModal(tableMensalista.getSelectionModel().getSelectedItem());
        loadTableView();
    }

    public void removeMensalista(ActionEvent actionEvent) throws SQLException {
        MensalistaUseCase mensalistaUseCase = new MensalistaUseCase();
        mensalistaUseCase.delete(tableMensalista.getSelectionModel().getSelectedItem().getId());
        VagasUseCase vagasUseCase = new VagasUseCase();
        vagasUseCase.setVagaFree(tableMensalista.getSelectionModel().getSelectedItem().getCpf());
        loadTableView();
    }
    @FXML
    private void initialize() throws SQLException {
        cpfColum.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        nomeColum.setCellValueFactory(new PropertyValueFactory<>("nome"));
        telefoneColum.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        empresaColum.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        vagaColum.setCellValueFactory(new PropertyValueFactory<>("vagaOcupada"));
        values = FXCollections.observableArrayList();
        tableMensalista.setItems(values);
        loadTableView();
    }

    private void loadTableView() throws SQLException {
        MensalistaUseCase mensalistaUseCase = new MensalistaUseCase();

        values.setAll(mensalistaUseCase.readAll());

        FilteredList<Mensalista> filteredData = new FilteredList<>(values, b ->  true);

        tfBuscaMensalista.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredData.setPredicate(mensalista -> {

                if(newValue == null || newValue.isEmpty())
                    return  true;

                String lowerCaseFilter = newValue.toLowerCase();

                if(mensalista.getCpf().indexOf(lowerCaseFilter) != -1
                        || mensalista.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1
                        || mensalista.getEmpresa().toLowerCase().indexOf(lowerCaseFilter) != -1
                        || mensalista.getTelefone().indexOf(lowerCaseFilter) != -1
                        || String.valueOf(mensalista.getVagaOcupada()).indexOf(lowerCaseFilter) != -1){
                    return  true;
                }
                else
                    return false;
            });

           tableMensalista.setItems(filteredData);

        }));
    }
}