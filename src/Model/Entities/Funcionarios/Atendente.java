package Model.Entities.Funcionarios;

import Model.Entities.Ticket.TicketCliente;
import Model.Entities.Ticket.Ticket;
import Model.Entities.Ticket.TicketMensalista;

public class Atendente extends Funcionario {

    public Atendente(String cpf, String nome, String senha, String telefone, String endereco, Enum<Efuncao> funcao) {
        super(cpf, nome, senha, telefone, endereco, funcao);
    }
    public Atendente(String cpf, String nome, String senha, String telefone, String endereco, Enum<Efuncao> funcao, int id) {
        super(cpf, nome, senha, telefone, endereco, funcao, id);
    }
    public Atendente(){}
   /* //Implementação da geração de ticket do cliente comum será feita aqui
    public TicketCliente generateClienteTicket(Ticket ticket, boolean isNight){

    }

    //Implementação da geração de ticket do mensalista comum será feita aqui
    public TicketMensalista generateMensalistaTicket(Ticket ticket){
        return new TicketMensalista();
    }*/
}
