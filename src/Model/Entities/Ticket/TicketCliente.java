package Model.Entities.Ticket;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketCliente extends Ticket {
    private double valorTotal;
    private double valorNoite;
    private boolean isPernoite;
    private String telefone;
    private String cpf;
    public TicketCliente(){}
    public TicketCliente(String placa, String telefone, Date horarioEntrada, Date horarioSaida, String descricaoCarro, int idVigilante, double valorTotal, double valorNoite, boolean isPernoite) {
        super(placa, horarioEntrada, horarioSaida, descricaoCarro, idVigilante);
        this.isPernoite = isPernoite;
        this.valorTotal = valorTotal;
        this.valorNoite = valorNoite;
        this.telefone = telefone;
    }

    public TicketCliente(String placa, Date horarioEntrada, Date horarioSaida, String descricaoCarro, int idVigilante) {
        super(placa, horarioEntrada, horarioSaida, descricaoCarro, idVigilante);
    }

    @Override
    public String toString() {
        return "Ticket Cliente #" + super.getId() + '\n' +
                "   Placa: '" + super.getPlaca() + '\n' +
                "   Horario de Entrada: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format( super.getHorarioEntrada()) + '\n' +
                "   Horario de Saida: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(super.getHorarioSaida()) + '\n' +
                "   Descrição do Carro: '" + super.getDescricaoCarro() + '\'' + '\n' +
                "   Id do Vigilante: " + super.getIdVigilante() + '\n' +
                "   Valor Total: R$" + valorTotal + '\n' +
                "   É pernoite: " + (isPernoite ? "Sim" : "Não") + '\n' +
                "   Telefone: '" + telefone + '\n' +
                "   CPF: '" + cpf + '\n' ;
    }

    public String toStringEnter() {
        return "Ticket Cliente #" + super.getId() + '\n' +
                "   Placa: '" + super.getPlaca() + '\n' +
                "   Horario de Entrada: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format( super.getHorarioEntrada()) + '\n' +
                "   Descrição do Carro: '" + super.getDescricaoCarro() + '\'' + '\n' +
                "   Id do Vigilante: " + super.getIdVigilante() + '\n' +
                "   É pernoite: " + (isPernoite ? "Sim" : "Não") + '\n' +
                "   CPF: '" + cpf + '\n' ;
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
