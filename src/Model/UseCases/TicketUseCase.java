package Model.UseCases;

import Model.Entities.Precos.Precos;
import Model.Entities.Ticket.TicketCliente;
import Model.Entities.Ticket.TicketMensalista;
import Utils.SqlConnection;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketUseCase {
    Connection connection;
    private RegistroVigilanteUseCase registroVigilanteUseCase = new RegistroVigilanteUseCase();
    private AlteraPrecosUseCase alteraPrecosUseCase = new AlteraPrecosUseCase();
    public TicketUseCase(){
        connection = SqlConnection.getConnection();
        if(connection == null) System.exit(1);
    }

    public void saveClientTicket(TicketCliente ticket) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sqlTicket = "INSERT INTO Ticket_Cliente(horarioEntrada, placa, descricaoCarro, idVigilante, telefone, pernoite, cpf) VALUES(?,?,?,?,?,?,?)";
        try{
            String currentTimeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ticket.getHorarioEntrada());
            preparedStatement = connection.prepareStatement(sqlTicket);
            preparedStatement.setString(1, currentTimeStamp);
            preparedStatement.setString(2, ticket.getPlaca());
            preparedStatement.setString(3, ticket.getDescricaoCarro());
            preparedStatement.setInt(4, ticket.getIdVigilante());
            preparedStatement.setString(5, ticket.getTelefone());
            preparedStatement.setBoolean(6, ticket.isPernoite());
            preparedStatement.setString(7, ticket.getCpf());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveMensalistaTicket(TicketMensalista ticket) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sqlTicket = "INSERT INTO Ticket_Mensalista(horarioEntrada, idVigilante, id_mensalista, placa, descricaoCarro) VALUES(?,?,?,?,?)";
        try{
            String currentTimeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ticket.getHorarioEntrada());
            preparedStatement = connection.prepareStatement(sqlTicket);
            preparedStatement.setString(1, currentTimeStamp);
            preparedStatement.setInt(2, ticket.getIdVigilante());
            preparedStatement.setInt(3, ticket.getIdMensalista());
            preparedStatement.setString(4, ticket.getPlaca());
            preparedStatement.setString(5, ticket.getDescricaoCarro());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isMensalistaTicket(String cpf) throws SQLException{
        PreparedStatement preparedStatement = null;
        int id = 0;
        ResultSet resultSet = null;
        String sql = "SELECT t.id FROM ticket_mensalista t " +
                "join mensalista m on t.id_mensalista = m.id  " +
                "WHERE m.cpf = ? and horarioSaida is null";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (id ==0 ? false : true);
    }

    public boolean isClienteTicket(String cpf) throws SQLException{
        PreparedStatement preparedStatement = null;
        int id = 0;
        ResultSet resultSet = null;
        String sql = "SELECT id FROM ticket_cliente WHERE cpf = ? and horarioSaida is null";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (id ==0 ? false : true);
    }

    public void pagamentoCliente(String cpf) throws SQLException {
        Precos precos = alteraPrecosUseCase.read();
        TicketCliente ticketCliente = getOpenClienteTicketByCpf(cpf);
        ticketCliente.setHorarioSaida(new Date());
        Long different = Math.abs(ticketCliente.getHorarioEntrada().getTime() - ticketCliente.getHorarioSaida().getTime());
        Long secondsInMilli = (long)1000;
        Long minutesInMilli = secondsInMilli * 60;
        Long hoursInMilli = minutesInMilli * 60;
        Long elapsedHours = (different / hoursInMilli)/360000;

        if(elapsedHours <= 0.5){
            ticketCliente.setValorTotal(precos.getPreco30min());
        } else if (elapsedHours <= 1.083){
            ticketCliente.setValorTotal(precos.getPreco1hr());
        } else {
            double calculoPreco = 0 + precos.getPreco1hr();
            elapsedHours = elapsedHours - 1;
            calculoPreco = calculoPreco + (elapsedHours.doubleValue() * precos.getPrecoDemaisHoras());
            ticketCliente.setValorTotal(calculoPreco);
        }


        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "UPDATE ticket_cliente SET horarioSaida = ?, valorTotal = ? WHERE cpf = ? and horarioSaida is null";
        try{
            String currentTimeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ticketCliente.getHorarioSaida());
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, currentTimeStamp);
            preparedStatement.setDouble(2, ticketCliente.getValorTotal());
            preparedStatement.setString(3, cpf);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saidaMensalista(String cpf) throws SQLException {
        TicketMensalista ticketMensalista = getOpenMensalistaTicketByCpf(cpf);
        ticketMensalista.setHorarioSaida(new Date());

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "UPDATE ticket_mensalista SET horarioSaida = ? WHERE id_mensalista = ? and horarioSaida is null";
        try{
            String currentTimeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ticketMensalista.getHorarioSaida());
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, currentTimeStamp);
            preparedStatement.setInt(2, ticketMensalista.getIdMensalista());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TicketMensalista getOpenMensalistaTicketByCpf(String cpf){
        PreparedStatement preparedStatement = null;
        TicketMensalista ticketMensalista = new TicketMensalista(null, null, null, null, registroVigilanteUseCase.getCurrentVigilante().getId());
        ResultSet resultSet = null;
        String sql = "SELECT * FROM ticket_mensalista t " +
                "join mensalista m on t.id_mensalista = m.id  " +
                "WHERE m.cpf = ? and horarioSaida is null";;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                ticketMensalista.setId(rs.getInt("id"));
                ticketMensalista.setIdMensalista(rs.getInt("id_mensalista"));
                ticketMensalista.setPlaca(rs.getString("placa"));
                ticketMensalista.setDescricaoCarro(rs.getString("descricaoCarro"));
                ticketMensalista.setHorarioEntrada(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("horarioEntrada")));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return ticketMensalista;
    }

    public TicketMensalista getMensalistaTicketById(int id){
        PreparedStatement preparedStatement = null;
        TicketMensalista ticketMensalista = new TicketMensalista(null, null, null, null, registroVigilanteUseCase.getCurrentVigilante().getId());
        ResultSet resultSet = null;
        String sql = "SELECT * FROM ticket_mensalista WHERE id = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                ticketMensalista.setId(rs.getInt("id"));
                ticketMensalista.setIdMensalista(rs.getInt("id_mensalista"));
                ticketMensalista.setPlaca(rs.getString("placa"));
                ticketMensalista.setDescricaoCarro(rs.getString("descricaoCarro"));
                ticketMensalista.setHorarioEntrada(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("horarioEntrada")));
                ticketMensalista.setHorarioSaida(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("horarioSaida")));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return ticketMensalista;
    }

    public TicketCliente getOpenClienteTicketByCpf(String cpf){
        PreparedStatement preparedStatement = null;
        TicketCliente ticketCliente = new TicketCliente(null, null, null, null, registroVigilanteUseCase.getCurrentVigilante().getId());
        ResultSet resultSet = null;
        String sql = "SELECT * FROM ticket_cliente " +
                "WHERE cpf = ? and horarioSaida is null";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                ticketCliente.setId(rs.getInt("id"));
                ticketCliente.setCpf(rs.getString("cpf"));
                ticketCliente.setPernoite(rs.getBoolean("pernoite"));
                ticketCliente.setPlaca(rs.getString("placa"));
                ticketCliente.setTelefone(rs.getString("telefone"));
                ticketCliente.setDescricaoCarro(rs.getString("descricaoCarro"));
                ticketCliente.setHorarioEntrada(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("horarioEntrada")));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return ticketCliente;
    }

    public TicketCliente getClienteTicketById(int id){
        PreparedStatement preparedStatement = null;
        TicketCliente ticketCliente = new TicketCliente(null, null, null, null, registroVigilanteUseCase.getCurrentVigilante().getId());
        ResultSet resultSet = null;
        String sql = "SELECT * FROM ticket_cliente " +
                "WHERE id = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                ticketCliente.setId(rs.getInt("id"));
                ticketCliente.setCpf(rs.getString("cpf"));
                ticketCliente.setPernoite(rs.getBoolean("pernoite"));
                ticketCliente.setPlaca(rs.getString("placa"));
                ticketCliente.setTelefone(rs.getString("telefone"));
                ticketCliente.setValorTotal(rs.getDouble("valorTotal"));
                ticketCliente.setDescricaoCarro(rs.getString("descricaoCarro"));
                ticketCliente.setHorarioEntrada(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("horarioEntrada")));
                ticketCliente.setHorarioSaida(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("horarioSaida")));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return ticketCliente;
    }

    public void generateTicketPdf(TicketCliente ticket){
        Document doc = new Document();
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF","*.pdf"));
        //File file = f.showSaveDialog(new Stage());
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("Tickets/TicketPagamentoCliente"+ticket.getId()+".pdf"));
            doc.open();
            doc.add(new Paragraph(ticket.toString()));
            doc.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void generateTicketPdf(TicketMensalista ticket){
        Document doc = new Document();
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF","*.pdf"));
        //File file = f.showSaveDialog(new Stage());
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("Tickets/TicketPagamentoMensalista"+ticket.getId()+".pdf"));
            doc.open();
            doc.add(new Paragraph(ticket.toString()));
            doc.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void generateEnterTicketPdf(TicketCliente ticket){
        Document doc = new Document();
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF","*.pdf"));
        //File file = f.showSaveDialog(new Stage());
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("Tickets/TicketEntradaCliente"+ticket.getId()+".pdf"));
            doc.open();
            doc.add(new Paragraph(ticket.toStringEnter()));
            doc.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void generateEnterTicketPdf(TicketMensalista ticket){
        Document doc = new Document();
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF","*.pdf"));
        //File file = f.showSaveDialog(new Stage());
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("Tickets/TicketEntradaMensalista"+ticket.getId()+".pdf"));
            doc.open();
            doc.add(new Paragraph(ticket.toStringEnter()));
            doc.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
