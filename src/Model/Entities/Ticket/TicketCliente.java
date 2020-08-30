package Model.Entities.Ticket;

import java.util.Date;

public class TicketCliente extends Ticket {
    private double valorTotal;
    private double valorNoite;
    private boolean isPernoite;
    private String telefone;
    private String cpf;

    public TicketCliente(String placa, String telefone, Date horarioEntrada, Date horarioSaida, String descricaoCarro, int idVigilante, double valorTotal, double valorNoite, boolean isPernoite) {
        super(placa, horarioEntrada, horarioSaida, descricaoCarro, idVigilante);
        this.isPernoite = isPernoite;
        this.valorTotal = valorTotal;
        this.valorNoite = valorNoite;
        this.isPernoite = isPernoite;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public TicketCliente(String placa, Date horarioEntrada, Date horarioSaida, String descricaoCarro, int idVigilante) {
        super(placa, horarioEntrada, horarioSaida, descricaoCarro, idVigilante);
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
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

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }
}
