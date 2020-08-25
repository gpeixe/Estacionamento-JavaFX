package Model.UseCases;

import Model.Entities.Mensalista.*;
import Utils.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MensalistaUseCase {
    Connection connection;

    public MensalistaUseCase(){
        connection = SqlConnection.getConnection();
        if(connection == null) System.exit(1);
    }

    public void save(Mensalista mensalista) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO Mensalista(empresa, nome, cpf, telefone) VALUES(?,?,?,?)";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mensalista.getEmpresa());
            preparedStatement.setString(2, mensalista.getNome());
            preparedStatement.setString(3, mensalista.getCpf());
            preparedStatement.setString(4, mensalista.getTelefone());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Mensalista mensalista) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "UPDATE Mensalista SET empresa = ?, nome = ?, cpf = ?, telefone = ? WHERE id = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mensalista.getEmpresa());
            preparedStatement.setString(2, mensalista.getNome());
            preparedStatement.setString(2, mensalista.getCpf());
            preparedStatement.setString(2, mensalista.getTelefone());
            preparedStatement.setInt(4, mensalista.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Mensalista read(Integer key) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Mensalista WHERE id = ?";
        Mensalista mensalista = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, key);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                mensalista = new Mensalista(rs.getString("empresa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"));
                mensalista.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensalista;
    }

    public List<Mensalista> readAll() throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Mensalista";
        List<Mensalista> mensalistas = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Mensalista m = new Mensalista(rs.getString("empresa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"));
                m.setId(rs.getInt("id"));
                mensalistas.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensalistas;
    }

}
