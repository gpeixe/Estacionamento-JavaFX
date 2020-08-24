package Model.Entities.Mensalista;

public class Mensalista {
    private String cpf;
    private String nome;
    private String telefone;
    private String empresa;
    private int id;

    public Mensalista(String cpf, String nome, String telefone, String empresa) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.empresa = empresa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
