package Model.UseCases;

import Model.Entities.Funcionarios.Funcionario;
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

    public boolean isLogin(String cpf, String password) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "select * from FUNCIONARIO where cpf = ? and senha = ?";

        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
                return  true;

            return  false;

        }catch (Exception e){

            return  false;

        } finally {
            preparedStatement.close();
            resultSet.close();
        }

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
