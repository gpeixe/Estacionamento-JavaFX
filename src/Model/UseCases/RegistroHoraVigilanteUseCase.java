package Model.UseCases;

import Model.Entities.Funcionarios.Funcionario;
import Model.Entities.Funcionarios.Vigilante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class RegistroHoraVigilanteUseCase extends RegistroHoraFuncionarioUseCase {


    public void atualizaVigilante(Vigilante newVig){

        Vigilante currentVigilante = getCurrentVigilante();

        PreparedStatement preparedStatement;
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

            if(currentVigilante != null  && !currentVigilante.getId().equals(newVig.getId())){

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
               FuncionarioCRUDUseCase funcionarioCRUDUseCase = new FuncionarioCRUDUseCase();
               return (Vigilante) funcionarioCRUDUseCase.read(idVigilante);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return  null;
    }



}
