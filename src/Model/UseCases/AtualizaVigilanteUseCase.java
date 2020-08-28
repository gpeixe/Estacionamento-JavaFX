package Model.UseCases;

import Model.Entities.Funcionarios.Vigilante;
import Utils.SqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class AtualizaVigilanteUseCase {
    Connection connection;

    public AtualizaVigilanteUseCase(){
        Calendar calendar = Calendar.getInstance();
        connection = SqlConnection.getConnection();
        if(connection == null) System.exit(1);
    }

    public void atualizaVigilante(Vigilante newVig){
        Vigilante currentVigilante = getCurrentVigilante();
        String currentTimeStamp = getCurrentTimeStampFormatted();

        PreparedStatement preparedStatement = null;
        String sql;
        try{
            sql = "INSERT INTO working_hours(funcionario_id, horario_entrada) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newVig.getId());
            preparedStatement.setString(2, currentTimeStamp);
            preparedStatement.execute();

            if(currentVigilante != null){
                System.out.println("Entrou update saida: ");
                sql = "UPDATE working_hours SET horario_saida = ? WHERE id = (select max(id) from working_hours where funcionario_id = ?)";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, currentTimeStamp);
                preparedStatement.setInt(2, currentVigilante.getId());
                preparedStatement.execute();
                System.out.println("Era pra sair");
            }

            sql = "UPDATE last_vigilante SET id_vigilante = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newVig.getId());
            preparedStatement.execute();


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private Vigilante getCurrentVigilante(){
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

    private String getCurrentTimeStampFormatted(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println(now.format(formatter));
        return now.format(formatter);

    }


}
