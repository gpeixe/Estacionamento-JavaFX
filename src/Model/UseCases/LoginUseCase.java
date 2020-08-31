package Model.UseCases;

import Model.Entities.Funcionarios.*;
import Utils.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUseCase {
    Connection connection;

    public LoginUseCase(){
        connection = SqlConnection.getConnection();
        if(connection == null) System.exit(1);
    }

    public Funcionario login(String cpf, String password) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "select * from FUNCIONARIO where cpf = ? and senha = ?";

        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){

                Funcionario funcionario = verificaLoginFuncionario(resultSet);
                return funcionario;
            }

            return  null;

        }catch (Exception e){

            return  null;

        }

    }

    public Funcionario verificaLoginFuncionario(ResultSet rs) throws SQLException {

        int id = rs.getInt(1);
        String cpf = rs.getString(2);
        String nome = rs.getString(3);
        String funcao = rs.getString(4);
        String telefone = rs.getString(5);
        String endereco = rs.getString(6);
        String password = rs.getString(7);

        Funcionario funcionario;

        switch (funcao){
            case "Administrador":
                funcionario = new Administrador();
                funcionario.setFuncao(Efuncao.ADMIN);
                break;
            case "Atendente":
                funcionario= new Atendente();
                funcionario.setFuncao(Efuncao.ATENDENTE);
                break;
            default:
                funcionario = new Vigilante();
                funcionario.setFuncao(Efuncao.VIGILANTE);
                break;
        }

        funcionario.setId(id);
        funcionario.setCpf(cpf);
        funcionario.setNome(nome);
        funcionario.setTelefone(telefone);
        funcionario.setEndereco(endereco);
        funcionario.setSenha(password);

        return  funcionario;
    }

    public boolean isDBbConnected(){
        try {
            return !connection.isClosed();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
