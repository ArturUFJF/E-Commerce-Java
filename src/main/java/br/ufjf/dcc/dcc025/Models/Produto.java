package br.ufjf.dcc.dcc025.Models;

import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.time.LocalDate;

public abstract class Produto {
    private int id;
    private String nome;
    private double preco;

    public Produto(){}

    public Produto(int id, String nome, float preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id == produto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}



class Eletronicos extends Produto {
    private LocalDate garantia;

    public Eletronicos(int id, String nome, float preco, LocalDate garantia) {
        super(id, nome, preco);
        this.garantia = garantia;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Produto(Eletrônico) " + id + " cadastrado: " + nome + ". R$" + preco + " - Garantia até " + garantia.format(formatter));
    }
}



class Roupas extends Produto {
    private String tamanho;
    private String cor;

    public Roupas(int id, String nome, float preco, String tamanho, String cor) {
        super(id, nome, preco);
        this.tamanho = tamanho;
        this.cor = cor;
        System.out.println("Produto(Roupa) " + id + " cadastrado: " + nome + ". R$" + preco + " - Tamanho " + tamanho + " " + cor);
    }
}



class Alimentos extends Produto {
    private LocalDate validade;

    public Alimentos(int id, String nome, float preco, LocalDate validade) {
        super(id, nome, preco);
        this.validade = validade;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Produto(Alimento) " + id + " cadastrado: " + nome + ". R$" + preco + " - Validade até " + validade.format(formatter));
    }
}
