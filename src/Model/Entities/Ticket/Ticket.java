package Model.Entities.Ticket;

import java.util.Date;

public abstract class Ticket {
    private int id;
    private String placa;
    private Date horarioEntrada;
    private Date horarioSaida;
    private String descricaoCarro;
    private int idVigilante;
    private long tempo;
    private String tempoFormatted = this.getTempoFormatted();

    public long getTempo() {
        return tempo;
    }

    public void setTempo(long tempo) {
        this.tempo = tempo;
    }

    public Ticket(){}

    public Ticket(String placa, Date horarioEntrada, Date horarioSaida, String descricaoCarro, int idVigilante) {
        this.placa = placa;
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
        this.descricaoCarro = descricaoCarro;
        this.idVigilante = idVigilante;
    }

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

    public Date getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(Date horarioSaida) {

        this.horarioSaida = horarioSaida;
    }

    public String getTempoFormatted() {

        long difference_In_Seconds
                = (this.getTempo()
                / 1000)
                % 60;

        long difference_In_Minutes
                = (this.getTempo()
                / (1000 * 60))
                % 60;

        long difference_In_Hours
                = (this.getTempo()
                / (1000 * 60 * 60))
                % 24;
        return difference_In_Hours + ":" + difference_In_Minutes + ":" + difference_In_Seconds;
    }
}
