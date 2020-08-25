package Model.Entities.Funcionarios;

import Model.Entities.Relatorio.Relatorio;
import Model.Entities.Ticket.TicketCliente;
import Model.Entities.Ticket.Ticket;
import Model.Entities.Ticket.TicketMensalista;

import java.util.List;

public class Administrador extends  Funcionario{
    public Administrador(String cpf, String nome, String senha, String telefone, String endereco, Enum<Efuncao> funcao) {
        super(cpf, nome, senha, telefone, endereco, funcao);
    }
    public  Administrador(){}
   /* //Implementação da geração de ticket do cliente comum será feita aqui
    public TicketCliente generateClienteTicket(Ticket ticket, boolean isNight){
        return new TicketCliente();
    }

    //Implementação da geração de ticket do mensalista comum será feita aqui
    public TicketMensalista generateMensalistaTicket(Ticket ticket){
        return new TicketMensalista();
    }

    //Implementação da mundança de preço será feita aqui
    public void updatePrices( double newHourPrice, double newNightPrice){

    }

    //Implementação da geração de relatorio vai aqui
    public Relatorio generateRelatorio(List<String> filters){
        return new Relatorio();
    } */

}
