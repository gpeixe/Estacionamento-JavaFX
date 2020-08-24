package Model.Entities.Relatorio;

import Model.Entities.Ticket.Ticket;

import java.util.List;

public class Relatorio {
    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Relatorio() {}

    public void generateRelatorio(List<String> filters){

    }
}
