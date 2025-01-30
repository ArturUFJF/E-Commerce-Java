package br.ufjf.dcc.dcc025;
import java.util.*;

public class Venda {
    private int id;
    private ArrayList<Produto> produtos;
    private Cupom cupom;

    public Venda(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cupom getCupom() {
        return cupom;
    }

    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return id == venda.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
