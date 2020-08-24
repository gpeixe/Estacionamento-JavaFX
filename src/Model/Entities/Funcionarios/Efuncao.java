package Model.Entities.Funcionarios;

public enum Efuncao {
    ADMIN("Administrador"),
    VIGILANTE("Vigilante"),
    ATENDENTE("Atendente");

    private String funcao;

    Efuncao(String funcao){
        this.funcao = funcao;
    }

    public String getFuncao(){
        return this.funcao;
    }
}
