package br.ufjf.dcc.dcc025;

public class Produto {
    private int id;
    private String nome;
    private float preco;

    public Produto(int id, String nome, float preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
}

class Eletronicos extends Produto {
    private String garantia;

    // Construtor da classe Eletronicos
    public Eletronicos(int id, String nome, float preco, String garantia) {
        super(id, nome, preco); // Chama o construtor da classe Produto
        this.garantia = garantia;
    }
}

class Roupas extends Produto {
    private String tamanho;
    private String cor;

    // Construtor da classe Roupas
    public Roupas(int id, String nome, float preco, String tamanho, String cor) {
        super(id, nome, preco); // Chama o construtor da classe Produto
        this.tamanho = tamanho;
        this.cor = cor;
    }
}

class Alimentos extends Produto {
    private String validade;

    // Construtor da classe Roupas
    public Alimentos(int id, String nome, float preco, String validade) {
        super(id, nome, preco); // Chama o construtor da classe Produto
        this.validade = validade;
    }
}
