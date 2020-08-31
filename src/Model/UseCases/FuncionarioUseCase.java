package Model.UseCases;

import Model.Entities.Funcionarios.*;
import Model.Entities.Mensalista.Mensalista;
import Utils.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioUseCase {
    Connection connection;

    public FuncionarioUseCase(){
        connection = SqlConnection.getConnection();
        if(connection == null) System.exit(1);
    }

    public void save(Funcionario funcionario) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO funcionario(cpf, nome, funcao, telefone, endereco, senha) VALUES(?,?,?,?,?,?)";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, funcionario.getCpf());
            preparedStatement.setString(2, funcionario.getNome());
            if(funcionario instanceof Atendente){
                preparedStatement.setString(3, "Atendente");
            }   else if(funcionario instanceof Vigilante){
                preparedStatement.setString(3, "Vigilante");
            }   else if(funcionario instanceof Administrador){
                preparedStatement.setString(3, "Administrador");
            }
            preparedStatement.setString(4, funcionario.getTelefone());
            preparedStatement.setString(5, funcionario.getEndereco());
            preparedStatement.setString(6, funcionario.getSenha());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Funcionario funcionario) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "UPDATE funcionario SET cpf = ?, nome = ?, funcao = ?, telefone = ?, endereco = ?, senha = ? WHERE id = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, funcionario.getCpf());
            preparedStatement.setString(2, funcionario.getNome());
            if(funcionario instanceof Atendente){
                preparedStatement.setString(3, "Atendente");
            }   else if(funcionario instanceof Vigilante){
                preparedStatement.setString(3, "Vigilante");
            }   else if(funcionario instanceof Administrador){
                preparedStatement.setString(3, "Administrador");
            }
            preparedStatement.setString(4, funcionario.getTelefone());
            preparedStatement.setString(5, funcionario.getEndereco());
            preparedStatement.setString(6, funcionario.getSenha());
            preparedStatement.setInt(7, funcionario.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Funcionario read(Integer key) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM funcionario WHERE id = ?";
        Funcionario funcionario = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, key);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                if(rs.getString("funcao").equals("Atendente")){
                    return new Atendente(rs.getString("cpf"),rs.getString("nome"),rs.getString("senha"),rs.getString("telefone"),rs.getString("endereco"),Efuncao.ATENDENTE,rs.getInt("id"));
                }   else if(rs.getString("funcao").equals("Vigilante")){
                    return new Vigilante(rs.getString("cpf"),rs.getString("nome"),rs.getString("senha"),rs.getString("telefone"),rs.getString("endereco"),Efuncao.VIGILANTE,rs.getInt("id"));
                }   else if(rs.getString("funcao").equals("Administrador")){
                    return new Administrador(rs.getString("cpf"),rs.getString("nome"),rs.getString("senha"),rs.getString("telefone"),rs.getString("endereco"),Efuncao.ADMIN,rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
    }

    public boolean verificaCadastrado(String cpf) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM funcionario WHERE cpf = ?";
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

    public List<Funcionario> readAll() throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                if(rs.getString("funcao").equals("Atendente")){
                    funcionarios.add(new Atendente(rs.getString("cpf"),rs.getString("nome"),rs.getString("senha"),rs.getString("telefone"),rs.getString("endereco"),Efuncao.ATENDENTE,rs.getInt("id")));
                }   else if(rs.getString("funcao").equals("Vigilante")){
                    funcionarios.add(new Vigilante(rs.getString("cpf"),rs.getString("nome"),rs.getString("senha"),rs.getString("telefone"),rs.getString("endereco"),Efuncao.VIGILANTE,rs.getInt("id")));
                }   else if(rs.getString("funcao").equals("Administrador")){
                    funcionarios.add(new Administrador(rs.getString("cpf"),rs.getString("nome"),rs.getString("senha"),rs.getString("telefone"),rs.getString("endereco"),Efuncao.ADMIN,rs.getInt("id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    public void delete(Integer key) throws SQLException{
        String sql = "DELETE FROM funcionario WHERE id=?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,key);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int numeroDeFuncionarios() throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT count(*) AS Quantidade FROM funcionario";
        int numFunc = 0;
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                numFunc = rs.getInt("Quantidade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numFunc;
    }
}
