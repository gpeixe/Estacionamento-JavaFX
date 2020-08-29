package Model.Entities.Ticket;

import java.util.Date;

public class TicketMensalista extends Ticket {
    private int idMensalista;

    public TicketMensalista(String placa, Date horarioEntrada, Date horarioSaida, String descricaoCarro, int idVigilante, int idMensalista) {
        super(placa, horarioEntrada, horarioSaida, descricaoCarro, idVigilante);
        this.idMensalista = idMensalista;
    }

    public int getIdMensalista() {
        return idMensalista;
    }

    public void setIdMensalista(int idMensalista) {
        this.idMensalista = idMensalista;
    }
}
