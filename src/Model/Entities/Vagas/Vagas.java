package Model.Entities.Vagas;

public class Vagas {
    private int id_vaga;
    private String cpf_ocupante;

    public Vagas(int id_vaga, String cpf_ocupante) {
        this.id_vaga = id_vaga;
        this.cpf_ocupante = cpf_ocupante;
    }

    public int getId_vaga() {
        return id_vaga;
    }

    public void setId_vaga(int id_vaga) {
        this.id_vaga = id_vaga;
    }

    public String getCpf_ocupante() {
        return cpf_ocupante;
    }

    public void setCpf_ocupante(String cpf_ocupante) {
        this.cpf_ocupante = cpf_ocupante;
    }
}
