package br.ufjf.dcc.dcc025.Models;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int id;
    private List<Produto> produtos;
    private double valorTotal;
    private Cupom cupom;
    private boolean cupomAplicado;

    public Venda(int id) {
        this.id = id;
        this.produtos = new ArrayList<>();
        this.valorTotal = 0;
        this.cupomAplicado = false;
    }

    public int getId() {
        return id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cupom getCupom() {
        return cupom;
    }

    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
    }

    public boolean isCupomAplicado() {
        return cupomAplicado;
    }

    public void setCupomAplicado(boolean cupomAplicado) {
        this.cupomAplicado = cupomAplicado;
    }
}
