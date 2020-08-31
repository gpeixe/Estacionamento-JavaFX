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

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TicketUseCase {
    Connection connection;
    private RegistroVigilanteUseCase registroVigilanteUseCase = new RegistroVigilanteUseCase();
    private AlteraPrecosUseCase alteraPrecosUseCase = new AlteraPrecosUseCase();
    public TicketUseCase(){
        connection = SqlConnection.getConnection();
        if(connection == null) System.exit(1);
    }

    public void saveClientTicket(TicketCliente ticket)  {
        PreparedStatement preparedStatement;
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

    public void saveMensalistaTicket(TicketMensalista ticket)  {
        PreparedStatement preparedStatement;
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

    public boolean isMensalistaTicket(String cpf){
        PreparedStatement preparedStatement;
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
        return (id != 0 );
    }

    public boolean isClienteTicket(String cpf) {
        PreparedStatement preparedStatement;
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
        return (id != 0 );
    }

    public void pagamentoCliente(String cpf) throws SQLException {
        Precos precos = alteraPrecosUseCase.read();
        TicketCliente ticketCliente = getOpenClienteTicketByCpf(cpf);
        ticketCliente.setHorarioSaida(new Date());
        Long different = Math.abs(ticketCliente.getHorarioSaida().getTime() - ticketCliente.getHorarioEntrada().getTime() );
        ticketCliente.setTempo(different);
        double hoursInMilli = 3600000;
        double elapsedHours = ((double)different / hoursInMilli);

        if(ticketCliente.isPernoite()){
            ticketCliente.setValorTotal(precos.getPrecoPerNoite());
        }
        else if(elapsedHours <= 0.5){
            ticketCliente.setValorTotal(precos.getPreco30min());
        } else if (elapsedHours <= 1.083){
            ticketCliente.setValorTotal(precos.getPreco1hr());
        } else {
            elapsedHours = Math.ceil(elapsedHours);
            double calculoPreco = 0 + precos.getPreco1hr();
            elapsedHours = elapsedHours - 1;
            calculoPreco = calculoPreco + (elapsedHours * precos.getPrecoDemaisHoras());
            ticketCliente.setValorTotal(calculoPreco);
        }


        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        String sql = "UPDATE ticket_cliente SET horarioSaida = ?, tempo = ?, valorTotal = ? WHERE cpf = ? and horarioSaida is null";
        try{
            String currentTimeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ticketCliente.getHorarioSaida());
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, currentTimeStamp);
            preparedStatement.setLong(2, ticketCliente.getTempo());
            preparedStatement.setDouble(3, ticketCliente.getValorTotal());
            preparedStatement.setString(4, cpf);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saidaMensalista(String cpf)  {
        TicketMensalista ticketMensalista = getOpenMensalistaTicketByCpf(cpf);
        ticketMensalista.setHorarioSaida(new Date());
        Long different = Math.abs(ticketMensalista.getHorarioEntrada().getTime() - ticketMensalista.getHorarioSaida().getTime());
        ticketMensalista.setTempo(different);
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        String sql = "UPDATE ticket_mensalista SET horarioSaida = ?, tempo = ? WHERE id_mensalista = ? and horarioSaida is null";
        try{
            String currentTimeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ticketMensalista.getHorarioSaida());
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, currentTimeStamp);
            preparedStatement.setLong(2, ticketMensalista.getTempo());
            preparedStatement.setInt(3, ticketMensalista.getIdMensalista());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TicketMensalista getOpenMensalistaTicketByCpf(String cpf){
        PreparedStatement preparedStatement;
        TicketMensalista ticketMensalista = new TicketMensalista(null, null, null, null, registroVigilanteUseCase.getCurrentVigilante().getId());
        ResultSet resultSet = null;
        String sql = "SELECT * FROM ticket_mensalista t " +
                "join mensalista m on t.id_mensalista = m.id  " +
                "WHERE m.cpf = ? and horarioSaida is null";
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
        PreparedStatement preparedStatement;
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
        PreparedStatement preparedStatement;
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
        PreparedStatement preparedStatement;
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
            String path = "Tickets/TicketPagamentoCliente"+ticket.getId()+".pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.open();
            doc.add(new Paragraph(ticket.toString()));
            doc.close();
            java.awt.Desktop.getDesktop().open(new File( path ));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateTicketPdf(TicketMensalista ticket){
        Document doc = new Document();
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF","*.pdf"));
        //File file = f.showSaveDialog(new Stage());
        try {
            String path = "Tickets/TicketPagamentoMensalista"+ticket.getId()+".pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.open();
            doc.add(new Paragraph(ticket.toString()));
            doc.close();
            java.awt.Desktop.getDesktop().open(new File( path ));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateEnterTicketPdf(TicketCliente ticket){
        Document doc = new Document();
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF","*.pdf"));
        //File file = f.showSaveDialog(new Stage());
        try {
            String path = "Tickets/TicketEntradaCliente"+ticket.getId()+".pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.open();
            doc.add(new Paragraph(ticket.toStringEnter()));
            doc.close();
            java.awt.Desktop.getDesktop().open(new File( path ));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateEnterTicketPdf(TicketMensalista ticket){
        Document doc = new Document();
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF","*.pdf"));
        //File file = f.showSaveDialog(new Stage());
        try {
            String path = "Tickets/TicketEntradaMensalista"+ticket.getId()+".pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.open();
            doc.add(new Paragraph(ticket.toStringEnter()));
            doc.close();
            java.awt.Desktop.getDesktop().open(new File( path ));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<TicketMensalista> getAllMensalistaTicketsOnDate(String date) {
        List<TicketMensalista> tickets = new ArrayList<>();
        try {
            TicketMensalista ticketMensalista = new TicketMensalista();
            String sql = "SELECT * FROM ticket_mensalista WHERE horarioSaida IS NOT NULL and horarioEntrada LIKE ?";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, date + "%");
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    ticketMensalista.setId(rs.getInt("id"));
                    ticketMensalista.setIdMensalista(rs.getInt("id_mensalista"));
                    ticketMensalista.setPlaca(rs.getString("placa"));
                    ticketMensalista.setDescricaoCarro(rs.getString("descricaoCarro"));
                    ticketMensalista.setHorarioEntrada(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("horarioEntrada")));
                    ticketMensalista.setHorarioSaida(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("horarioSaida")));
                    ticketMensalista.setIdVigilante(rs.getInt("idVigilante"));
                    ticketMensalista.setTempo(rs.getLong("tempo"));
                    tickets.add(ticketMensalista);
                }

                return tickets;
            } catch (SQLException | ParseException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public List<TicketCliente> getAllClienteTicketsOnDate(String date) {
        List<TicketCliente> tickets = new ArrayList<>();
        try {

            String sql = "SELECT * FROM ticket_cliente WHERE horarioSaida IS NOT NULL and horarioEntrada LIKE ?";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, date + "%");
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    TicketCliente ticketCliente = new TicketCliente();
                    ticketCliente.setId(rs.getInt("id"));
                    ticketCliente.setCpf(rs.getString("cpf"));
                    ticketCliente.setPernoite(rs.getBoolean("pernoite"));
                    ticketCliente.setPlaca(rs.getString("placa"));
                    ticketCliente.setTelefone(rs.getString("telefone"));
                    ticketCliente.setValorTotal(rs.getDouble("valorTotal"));
                    ticketCliente.setDescricaoCarro(rs.getString("descricaoCarro"));
                    ticketCliente.setHorarioEntrada(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("horarioEntrada")));
                    ticketCliente.setHorarioSaida(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("horarioSaida")));
                    ticketCliente.setTempo(rs.getLong("tempo"));
                    tickets.add(ticketCliente);
                }

                return tickets;
            } catch (SQLException | ParseException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }

    /*Retorna o número do próximo ticket*/
    public int idProximoTicketCliente(){
        PreparedStatement preparedStatement;
        String sql = "SELECT max(id) as number FROM ticket_cliente";
        int id = 0;
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                id = rs.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id+1;
    }

    public int idProximoTicketMensalista(){
        PreparedStatement preparedStatement;
        String sql = "SELECT max(id) as number FROM ticket_mensalista";
        int id = 0;
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                id = rs.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id+1;
    }

}
