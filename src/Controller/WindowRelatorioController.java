package Controller;

import Model.Entities.Ticket.Ticket;
import Model.Entities.Ticket.TicketCliente;
import Model.Entities.Ticket.TicketMensalista;
import Model.UseCases.TicketUseCase;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WindowRelatorioController {

    @FXML
    JFXDatePicker datePick;
    @FXML
    TableView tableRelatorioTempo;

    @FXML
    TableColumn<Ticket, String> cPlaca_rtempo;
    @FXML
    TableColumn<Ticket, String> cTempo_rtempo;

    @FXML
    TableColumn<Ticket, String> cPlaca_rpago;
    @FXML
    TableColumn<Ticket, String> cValor_rpago;

    @FXML
    TableView tablePlacaPago;

    List<TicketCliente> ticketClienteList;
    List<TicketMensalista> ticketMensalistaList;

    @FXML
    Label lblNumCarros, lblTotalRecebido, lblMensalistas, lblPernoite, lblOutros, lblPermanVei;

    @FXML
    private void initialize() {
        LocalDate today = LocalDate.now();
        datePick.setValue(today);
        setTicketListsOnDate(today);
        loader();
    }

    private void loader(){
        this.loadTableTempo();
        this.loadTablePago();
        this.loadLabels();
    }

    private void clear(){
        tablePlacaPago.getItems().clear();
        tableRelatorioTempo.getItems().clear();
        lblNumCarros.setText(lblNumCarros.getText().split(": ")[0] + ":");
        lblTotalRecebido.setText(lblTotalRecebido.getText().split(": ")[0]+ ":");
        lblMensalistas.setText(lblMensalistas.getText().split(": ")[0]+ ":");
        lblPernoite.setText(lblPernoite.getText().split(": ")[0]+ ":");
        lblOutros.setText(lblOutros.getText().split(": ")[0]+ ":");
        lblPermanVei.setText(lblPermanVei.getText().split(": ")[0]+ ":");

    }
    private  void loadTableTempo(){
        cPlaca_rtempo.setCellValueFactory(new PropertyValueFactory<>("placa"));
        cTempo_rtempo.setCellValueFactory(new PropertyValueFactory<>("tempoFormatted"));
        ObservableList<Ticket> values = FXCollections.observableArrayList();
        values.addAll(this.ticketClienteList);
        values.addAll(this.ticketMensalistaList);
        tableRelatorioTempo.setItems(values);
    }

    private  void loadTablePago(){
        cPlaca_rpago.setCellValueFactory(new PropertyValueFactory<>("placa"));
        cValor_rpago.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        ObservableList<Ticket> values = FXCollections.observableArrayList();
        values.addAll(this.ticketClienteList);
        tablePlacaPago.setItems(values);
    }

    private void setTicketListsOnDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateFormatted = formatter.format(date);
        TicketUseCase ticketUseCase = new TicketUseCase();
        this.ticketMensalistaList = ticketUseCase.getAllMensalistaTicketsOnDate(dateFormatted);
        this.ticketClienteList = ticketUseCase.getAllClienteTicketsOnDate(dateFormatted);
    }

    private  int getNumberOfEntries(){

        return this.ticketClienteList.size() + this.ticketMensalistaList.size();
    }

    private double getTotalOfDay(){
        double total = 0.0;

        for (TicketCliente ticket : this.ticketClienteList) {
            total = total + ticket.getValorTotal();
        }

        return total;
    }

    private void loadLabels(){
        lblNumCarros.setText(lblNumCarros.getText() + " " + this.getNumberOfEntries());
        lblTotalRecebido.setText(lblTotalRecebido.getText() + " " + this.getTotalOfDay());
        lblMensalistas.setText(lblMensalistas.getText() + " " + this.getNumberMensalistas());
        lblPernoite.setText(lblPernoite.getText() + " " + this.getNumberPernoite());
        lblOutros.setText(lblOutros.getText() + " " + this.getNumberOutros());
        lblPermanVei.setText(lblPermanVei.getText() + " " + this.getMediaPerma());
    }


    private int getNumberMensalistas(){
        return this.ticketMensalistaList.size();
    }

    private int getNumberPernoite(){
        int count = 0;
        for (TicketCliente ticket: this.ticketClienteList ) {
            if(ticket.isPernoite())
                count++;
        }
        return  count;
    }

    private int getNumberOutros(){
        return this.ticketClienteList.size() - this.getNumberPernoite();
    }

    private String getMediaPerma(){
        long count = 0;
        long media;

        if(this.ticketMensalistaList.size() == 0 && this.ticketClienteList.size() == 0)
            return "";

        for (TicketCliente ticket:
                this.ticketClienteList ) {
                count = count + ticket.getTempo();
            }

        for (TicketMensalista ticket: this.ticketMensalistaList){
            count = count + ticket.getTempo();
        }

        media = count / (ticketClienteList.size() +  ticketMensalistaList.size());

        long mediaHora = (media/(1000 * 60 * 60)) % 24;
        long mediaMinuto = (media/(1000 * 60)) % 60;
        long mediaSegundo = (media/1000) % 60;

        return  mediaHora + ":" + mediaMinuto + ":" + mediaSegundo;
    }

    public void searchOnDate(ActionEvent actionEvent) {
        clear();
        setTicketListsOnDate(datePick.getValue());
        loader();
    }
}