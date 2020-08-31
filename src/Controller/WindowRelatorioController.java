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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @FXML
    LineChart graphPermHr;
    @FXML
      CategoryAxis graphX;
    @FXML NumberAxis graphY;

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
        this.loadLinearGraph();
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
        graphPermHr.getData().clear();

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
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.addAll(this.ticketClienteList);
        ticketList.addAll(this.ticketMensalistaList);
        lblNumCarros.setText(lblNumCarros.getText() + " " + this.getNumberOfEntries());
        lblTotalRecebido.setText(lblTotalRecebido.getText() + " " + this.getTotalOfDay());
        lblMensalistas.setText(lblMensalistas.getText() + " " + this.getNumberMensalistas());
        lblPernoite.setText(lblPernoite.getText() + " " + this.getNumberPernoite());
        lblOutros.setText(lblOutros.getText() + " " + this.getNumberOutros());
        lblPermanVei.setText(lblPermanVei.getText() + " " + this.getMediaPerma(ticketList));
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

    private String getMediaPerma(List<Ticket> ticketList){
        long count = 0;
        long media;

        if(ticketList.size() == 0)
            return "";

        for (Ticket ticket:
                ticketList ) {
                count = count + ticket.getTempo();
            }


        media = count / ticketList.size() ;

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

    private void loadLinearGraph(){

        XYChart.Series serie = new XYChart.Series();
        serie.setName("Permanência/hora");

        if(getNumberOfEntries() == 0)
            return;

        for (int i = 0; i < 24 ; i++) {
            serie.getData().add(new XYChart.Data(this.getMediaPermaPorHora(i), i));
        }

        graphPermHr.getData().addAll(serie);

    }

    private String getMediaPermaPorHora(int hora){
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.addAll(this.ticketClienteList);
        ticketList.addAll(this.ticketMensalistaList);

        List<Ticket> rangeTicket = new ArrayList<>();

        int horaSup = hora + 1;
        String superior =  datePick.getValue().toString() + " " +(horaSup) + ":00:00";
        String inferior = datePick.getValue().toString() + " " + hora+ ":00:00";

        DateTimeFormatter formatterOne = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss");
        DateTimeFormatter formatterTwo = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime superiorDate;
        LocalDateTime inferiorDate;
        try {
            superiorDate = LocalDateTime.parse(superior, formatterTwo);
            inferiorDate = LocalDateTime.parse(inferior, formatterTwo);
        } catch (Exception e){
            superiorDate = LocalDateTime.parse(superior, formatterOne);
            inferiorDate = LocalDateTime.parse(inferior, formatterOne);
        }

        long count = 0;

        for (Ticket ticket:
             ticketList) {

            if(ticket.getHorarioSaida() == null)
                continue;

            LocalDateTime  ticketHoraEntrada = ticket.getHorarioEntrada().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime  ticketHoraSaida = ticket.getHorarioSaida().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();


            if(ticketHoraEntrada.isBefore(superiorDate) && ticketHoraSaida.isAfter(inferiorDate)){
                rangeTicket.add(ticket);
               long ticketTempo = ticket.getTempo();
               long tempoHoras;

                tempoHoras = ticketTempo / (1000 * 60 * 60);

                if(tempoHoras > 1)
                    count = count + 3600000;
                else{
                    count = count + ticketTempo;
                }

            }

            }
        if(rangeTicket.size() == 0)
            return "00:00:";


        long media = count/rangeTicket.size();

        long difference_In_Minutes
                = (media
                / (1000 * 60))
                % 60;

        long difference_In_Hours
                = (media
                / (1000 * 60 * 60))
                % 24;




        return difference_In_Hours + ":" + difference_In_Minutes;
    }
}