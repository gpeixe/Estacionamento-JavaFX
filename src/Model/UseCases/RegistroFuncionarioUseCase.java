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


public abstract  class RegistroFuncionarioUseCase {
    Connection connection;

    public RegistroFuncionarioUseCase(){
        Calendar calendar = Calendar.getInstance();
        connection = SqlConnection.getConnection();
        if(connection == null) System.exit(1);
    }

    public void registrarEntrada(Funcionario funcionario){
        String currentTimeStamp = getCurrentTimeStampFormatted();
        PreparedStatement preparedStatement = null;
        String sql;

        try {

            //INSERE A HORA DE INICIO DO FUNCIONARIO LOGADO
            sql = "INSERT INTO working_hours(funcionario_id, horario_entrada) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, funcionario.getId());
            preparedStatement.setString(2, currentTimeStamp);
            preparedStatement.execute();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrarSaida(Funcionario funcionario){
        String currentTimeStamp = getCurrentTimeStampFormatted();
        PreparedStatement preparedStatement = null;
        String sql;

        try {

            //INSERE O HORARIO DE SÁIDA  DO FUNCIONARIO DESLOGANTE
            sql = "UPDATE working_hours SET horario_saida = ? WHERE id = (select max(id) from working_hours where funcionario_id = ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, currentTimeStamp);
            preparedStatement.setInt(2, funcionario.getId());
            preparedStatement.execute();



            //INSERE AS HORAS TRABALHADAS  DO FUNCIONARIO DESLOGANTE
            sql = "UPDATE working_hours SET horas_trabalhadas = ? WHERE id = (select max(id) from working_hours where funcionario_id = ?) ";
            String horasTrabalhadas =  getWorkedHours(funcionario.getId());
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, horasTrabalhadas);
            preparedStatement.setInt(2, funcionario.getId());
            preparedStatement.execute();


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String getWorkedHours(int id){
        try {
            String sql = "SELECT horario_entrada, horario_saida FROM working_hours  WHERE id = (select max(id) from working_hours where funcionario_id = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                LocalDateTime horarioEntrada = convertStringToLocalTime(resultSet.getString(1));
                LocalDateTime horarioSaida = convertStringToLocalTime(resultSet.getString(2));
                Duration diferencaDuration = Duration.between(horarioSaida, horarioEntrada);
                int horas = Math.abs(diferencaDuration.toHoursPart());
                int minutos = Math.abs(diferencaDuration.toMinutesPart());
                int segundos = Math.abs(diferencaDuration.toSecondsPart());

                return  horas + ":" + minutos + ":" + segundos;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected  LocalDateTime convertStringToLocalTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    protected String getCurrentTimeStampFormatted(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return now.format(formatter);

    }

}
