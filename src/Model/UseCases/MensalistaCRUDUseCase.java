package Model.UseCases;

import Model.Entities.Mensalista.*;
import Utils.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MensalistaCRUDUseCase {
    Connection connection;

    public MensalistaCRUDUseCase(){
        connection = SqlConnection.getConnection();
        if(connection == null) System.exit(1);
    }

    public void save(Mensalista mensalista){
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        String sql = "INSERT INTO Mensalista(empresa, nome, cpf, telefone, vagaOcupada) VALUES(?,?,?,?,?)";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mensalista.getEmpresa());
            preparedStatement.setString(2, mensalista.getNome());
            preparedStatement.setString(3, mensalista.getCpf());
            preparedStatement.setString(4, mensalista.getTelefone());
            preparedStatement.setInt(5, mensalista.getVagaOcupada());
            preparedStatement.execute();
            VagasUseCase vagasUseCase = new VagasUseCase();
            vagasUseCase.setVaga(mensalista.getCpf(), mensalista.getVagaOcupada());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Mensalista mensalista){
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        String sql = "UPDATE Mensalista SET empresa = ?, nome = ?, cpf = ?, telefone = ?, vagaOcupada = ? WHERE id = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mensalista.getEmpresa());
            preparedStatement.setString(2, mensalista.getNome());
            preparedStatement.setString(3, mensalista.getCpf());
            preparedStatement.setString(4, mensalista.getTelefone());
            preparedStatement.setInt(5, mensalista.getVagaOcupada());
            preparedStatement.setInt(6, mensalista.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Mensalista read(Integer key){
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Mensalista WHERE id = ?";
        Mensalista mensalista = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, key);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                Mensalista m = new Mensalista(rs.getString("cpf"),rs.getString("nome"),rs.getString("telefone"),rs.getString("empresa"),rs.getInt("vagaOcupada"));
                m.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensalista;
    }

    public List<Mensalista> readAll() throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM Mensalista";
        List<Mensalista> mensalistas = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Mensalista m = new Mensalista(rs.getString("cpf"),rs.getString("nome"),rs.getString("telefone"),rs.getString("empresa"),rs.getInt("vagaOcupada"));
                m.setId(rs.getInt("id"));
                mensalistas.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensalistas;
    }

    public Mensalista getMensalistaByCpf(String cpf){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Mensalista WHERE cpf = ?";
        Mensalista mensalista = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                Mensalista m = new Mensalista(rs.getString("cpf"),rs.getString("nome"),rs.getString("telefone"),rs.getString("empresa"),rs.getInt("vagaOcupada"));
                m.setId(rs.getInt("id"));
                mensalista = m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensalista;
    }

    public void delete(Integer key) throws SQLException{
        String sql = "DELETE FROM Mensalista WHERE id=?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,key);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int numeroDeMensalistas() throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT count(*) AS Quantidade FROM mensalista";
        int numMensalistas = 0;
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                numMensalistas = rs.getInt("Quantidade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numMensalistas;
    }

    public boolean verificaCadastrado(String cpf) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM mensalista WHERE cpf = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
