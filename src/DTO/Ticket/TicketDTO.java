package DTO.Ticket;

import java.util.Date;

public abstract class TicketDTO {
    private int id;
    private String placa;
    private String telefone;
    private Date horarioEntrada;
    private String descricaoCarro;
    private int idVigilante;

    public TicketDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(Date horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public String getDescricaoCarro() {
        return descricaoCarro;
    }

    public void setDescricaoCarro(String descricaoCarro) {
        this.descricaoCarro = descricaoCarro;
    }

    public int getIdVigilante() {
        return idVigilante;
    }

    public void setIdVigilante(int idVigilante) {
        this.idVigilante = idVigilante;
    }
}
