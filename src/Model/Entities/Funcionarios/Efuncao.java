package Model.Entities.Funcionarios;

public enum Efuncao {
    ADMIN("Administrador"),
    VIGILANTE("Vigilante"),
    ATENDENTE("Atendente");

    public String function;

    Efuncao(String funcao){
        this.function = funcao;
    }

    public String getFuncao(){
        return this.function;
    }
}
