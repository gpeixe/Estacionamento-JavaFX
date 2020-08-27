package Model.Entities.Funcionarios;

public abstract class Funcionario {
    private String cpf;
    private String nome;
    private String senha;
    private String telefone;
    private String endereco;
    private Enum<Efuncao> funcao;
    private int id;

    public Funcionario(String cpf, String nome, String senha, String telefone, String endereco, Enum<Efuncao> funcao) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
        this.endereco = endereco;
        this.funcao = funcao;
    }

    public Funcionario(String cpf, String nome, String senha, String telefone, String endereco, Enum<Efuncao> funcao, int id) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
        this.endereco = endereco;
        this.funcao = funcao;
        this.id = id;
    }

    public  Funcionario(){}

    public Enum<Efuncao> getFuncao() {
        return funcao;
    }

    public void setFuncao(Enum<Efuncao> funcao) {
        this.funcao = funcao;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
