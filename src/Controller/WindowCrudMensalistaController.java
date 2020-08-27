package Controller;

import Model.Entities.Mensalista.Mensalista;
import Model.UseCases.MensalistaUseCase;
import View.loaders.WindowMensalista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    }
}