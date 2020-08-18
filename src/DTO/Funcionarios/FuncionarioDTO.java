package DTO.Funcionarios;

public abstract class FuncionarioDTO {
    private String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String funcao;
    private int id;

    public FuncionarioDTO() {}

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

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //Implementação do login será feita aqui
    public void userLogin(){

    }

    //Implementação do logout será feita aqui
    public void userLogout(){

    }
}
