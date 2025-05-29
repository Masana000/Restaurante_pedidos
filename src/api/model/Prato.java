package api.model;

public class Prato {
    private String id;
    private String nome;
    private String descricao; // Campo adicionado
    private double preco;

    // Construtor atualizado para incluir descricao
    public Prato(String id, String nome, String descricao, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Prato{" +
               "id='" + id + '\'' +
               ", nome='" + nome + '\'' +
               ", descricao='" + descricao + '\'' +
               ", preco=" + preco +
               '}';
    }
}