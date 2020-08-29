package Model.UseCases;

import Model.Entities.Mensalista.Mensalista;
import Model.Entities.Ticket.Ticket;
import Model.Entities.Ticket.TicketCliente;
import Model.Entities.Ticket.TicketMensalista;
import Utils.SqlConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TicketUseCase {
    Connection connection;

    public TicketUseCase(){
        connection = SqlConnection.getConnection();
        if(connection == null) System.exit(1);
    }

    public void saveClientTicket(TicketCliente ticket) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sqlTicket = "INSERT INTO Ticket_Cliente(horarioEntrada, placa, descricaoCarro, idVigilante, telefone, pernoite, cpf) VALUES(?,?,?,?,?,?,?)";
        try{
            String currentTimeStamp = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(ticket.getHorarioEntrada());
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
            String currentTimeStamp = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(ticket.getHorarioEntrada());
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
}
