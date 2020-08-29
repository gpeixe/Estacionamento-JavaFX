package Model.UseCases;

import Model.Entities.Funcionarios.Funcionario;
import Model.Entities.Funcionarios.Vigilante;
import Utils.SqlConnection;
import javafx.css.converter.DurationConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;


public class RegistroVigilanteUseCase extends RegistroFuncionarioUseCase{


    public void atualizaVigilante(Vigilante newVig){

        Vigilante currentVigilante = getCurrentVigilante();

        PreparedStatement preparedStatement = null;
        String sql;
        try{

            if (currentVigilante == null ){
                // INSERE NOVO VIGILANTE COMO VIGITALNTE ATUAL
                sql = "INSERT INTO last_vigilante  VALUES (?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, newVig.getId());
                preparedStatement.execute();

                super.registrarEntrada(newVig);

            }

            if(currentVigilante != null  && currentVigilante.getId() != newVig.getId()){

                super.registrarEntrada(newVig);

                super.registrarSaida(currentVigilante);

                //ATUALIZA TABELA VIGILANTE ATUAL COM ID DO NOVO VIGILANTE
                sql = "UPDATE last_vigilante SET id_vigilante = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, newVig.getId());
                preparedStatement.execute();

            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void registrarSaida(Funcionario funcionario) {
        super.registrarSaida(funcionario);
        try {
            String sql = "DELETE FROM last_vigilante";
            PreparedStatement  preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Vigilante getCurrentVigilante(){
        try {
            Vigilante currentVigilante = null;

            String sql = "SELECT id_vigilante FROM last_vigilante";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
               int idVigilante = resultSet.getInt(1);
               FuncionarioUseCase funcionarioUseCase = new FuncionarioUseCase();
               return (Vigilante) funcionarioUseCase.read(idVigilante);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return  null;
    }



}
