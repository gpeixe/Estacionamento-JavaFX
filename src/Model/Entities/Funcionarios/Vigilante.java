package Model.Entities.Funcionarios;

public class Vigilante extends Funcionario {
    public Vigilante(String cpf, String nome, String senha, String telefone, String endereco, Enum<Efuncao> funcao) {
        super(cpf, nome, senha, telefone, endereco, funcao);
    }
    public Vigilante(String cpf, String nome, String senha, String telefone, String endereco, Enum<Efuncao> funcao, int id) {
        super(cpf, nome, senha, telefone, endereco, funcao, id);
    }
    public Vigilante(){}
}
