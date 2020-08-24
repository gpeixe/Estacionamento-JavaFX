package Model.Entities.Ticket;

import java.util.Date;

public class TicketCliente extends Ticket {
    private double valorHora;
    private double valorNoite;
    private boolean isPernoite;
    private String telefone;

    public TicketCliente(String placa, String telefone, Date horarioEntrada, Date horarioSaida, String descricaoCarro, int idVigilante, double valorHora, double valorNoite, boolean isPernoite) {
        super(placa, horarioEntrada, horarioSaida, descricaoCarro, idVigilante);
        this.isPernoite = isPernoite;
        this.valorHora = valorHora;
        this.valorNoite = valorNoite;
        this.telefone = telefone;

    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public double getValorNoite() {
        return valorNoite;
    }

    public void setValorNoite(double valorNoite) {
        this.valorNoite = valorNoite;
    }

    public boolean isPernoite() {
        return isPernoite;
    }

    public void setPernoite(boolean pernoite) {
        isPernoite = pernoite;
    }
}
