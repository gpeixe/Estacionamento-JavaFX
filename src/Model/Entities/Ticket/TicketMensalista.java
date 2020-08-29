package Model.Entities.Ticket;

import java.util.Date;

public class TicketMensalista extends Ticket {
    private int idMensalista;
    private String cpf;

    public TicketMensalista(String placa, Date horarioEntrada, Date horarioSaida, String descricaoCarro, int idVigilante, int idMensalista, String cpf) {
        super(placa, horarioEntrada, horarioSaida, descricaoCarro, idVigilante);
        this.idMensalista = idMensalista;
        this.cpf = cpf;
    }

    public TicketMensalista(String placa, Date horarioEntrada, Date horarioSaida, String descricaoCarro, int idVigilante) {
        super(placa, horarioEntrada, horarioSaida, descricaoCarro, idVigilante);
    }

    public int getIdMensalista() {
        return idMensalista;
    }

    public void setIdMensalista(int idMensalista) {
        this.idMensalista = idMensalista;
    }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }
}
