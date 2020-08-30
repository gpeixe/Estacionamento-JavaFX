package Model.UseCases;

import Model.Entities.Vagas.Vagas;
import Utils.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VagasUseCase {
    Connection connection;

    public VagasUseCase(){
        connection = SqlConnection.getConnection();
        if(connection == null) System.exit(1);
    }

    public int numeroVagasDisponiveis() throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT count(*) AS Quantidade FROM vagas WHERE cpf_ocupante IS NULL";
        int numVagas = 0;
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                numVagas = rs.getInt("Quantidade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numVagas;
    }

    public int numeroVagasTotais() throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT count(*) AS Quantidade FROM vagas";
        int numVagas = 0;
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                numVagas = rs.getInt("Quantidade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numVagas;
    }

    public List<Vagas> readAll() throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM vagas";
        List<Vagas> vagas = new ArrayList<>();
        String ocupante = "";
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                if(rs.getString("cpf_ocupante") == null){
                    ocupante = "Disponível";
                }   else{
                    ocupante = rs.getString("cpf_ocupante");
                }
                vagas.add(new Vagas(rs.getInt("id_vaga"), ocupante));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vagas;
    }

    public List<Vagas> readAllLivres() throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM vagas WHERE cpf_ocupante IS NULL";
        List<Vagas> vagas = new ArrayList<>();
        String ocupante = "";
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                if(rs.getString("cpf_ocupante") == null){
                    ocupante = "Disponível";
                }   else{
                    ocupante = rs.getString("cpf_ocupante");
                }
                vagas.add(new Vagas(rs.getInt("id_vaga"), ocupante));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vagas;
    }

    public void setVaga(String cpf, int id_vaga) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "UPDATE vagas SET cpf_ocupante = ? WHERE id_vaga = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            preparedStatement.setInt(2, id_vaga);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setVagaFree(String cpf) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "UPDATE vagas SET cpf_ocupante = null WHERE cpf_ocupante = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
