package Model.Entities.Ticket;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketMensalista extends Ticket {
    private int idMensalista;

    public TicketMensalista(String placa, Date horarioEntrada, Date horarioSaida, String descricaoCarro, int idVigilante, int idMensalista) {
        super(placa, horarioEntrada, horarioSaida, descricaoCarro, idVigilante);
        this.idMensalista = idMensalista;
    }

    public TicketMensalista(String placa, Date horarioEntrada, Date horarioSaida, String descricaoCarro, int idVigilante) {
        super(placa, horarioEntrada, horarioSaida, descricaoCarro, idVigilante);
    }

    @Override
    public String toString() {
        return "Ticket Mensalista #" + super.getId() + '\n' +
                "   Placa: " + super.getPlaca() + '\n' +
                "   Horario de Entrada: " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format( super.getHorarioEntrada()) + '\n' +
                "   Horario de Saida: " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(super.getHorarioSaida()) + '\n' +
                "   Descrição do Carro: " + super.getDescricaoCarro() + '\'' + '\n' +
                "   Id do Vigilante: " + super.getIdVigilante() + '\n' +
                "   Id do Mensalista: " + idMensalista ;
    }

    public int getIdMensalista() {
        return idMensalista;
    }

    public void setIdMensalista(int idMensalista) {
        this.idMensalista = idMensalista;
    }
}
