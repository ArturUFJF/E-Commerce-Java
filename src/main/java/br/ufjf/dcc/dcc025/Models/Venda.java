package br.ufjf.dcc.dcc025.Models;

import br.ufjf.dcc.dcc025.Services.GestorVendas;
import br.ufjf.dcc.dcc025.Exceptions.CupomInvalidoException;
import java.util.*;

public class Venda {
    private int id;
    private ArrayList<Produto> produtos = new ArrayList<>();
    private Cupom cupom;
    private double valorTotal;

    public Venda(GestorVendas gestor) {
        gestor.cadastrarVenda(this);
    }

    public Venda(int id, GestorVendas gestor) {
        this.id = id;
        gestor.cadastrarVenda(this);
    }

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

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
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
        return Objects.hash(id);
    }

    public void realizarVenda() {
        // Calculando valor total sem desconto
        valorTotal = 0;
        for (Produto p : produtos) {
            valorTotal += p.getPreco();
        }

        // Aplicação de cupom se houver
        if (cupom != null && cupom.isAtivo()) {
            try {
                if (cupom instanceof CupomQuantidadeLimitada) {
                    ((CupomQuantidadeLimitada) cupom).aplicarCupom(this);
                } else if (cupom instanceof CupomValorMinimo) {
                    ((CupomValorMinimo) cupom).aplicarCupom(this);
                }
            } catch (CupomInvalidoException e) {
                System.out.println("Aviso: " + e.getMessage());
            }
        }

        // Exibir os detalhes da venda após o cálculo correto do valor total
        exibirVenda();
    }

    public void exibirVenda() {
        System.out.println("\n=== Detalhes da Venda ===");
        System.out.println("ID da Venda: " + id);

        if (cupom != null && cupom.isAtivo()) {
            System.out.println("Cupom aplicado: " + cupom.getCodigo() + " | Desconto: " + cupom.getPercentualDesconto() + "%");
        } else {
            System.out.println("Nenhum cupom aplicado ou cupom inválido.");
        }

        if (!produtos.isEmpty()) {
            System.out.println("\nProdutos:");
            for (Produto p : produtos) {
                System.out.printf("- %s | Preço: R$%.2f\n", p.getNome(), p.getPreco());
            }
            System.out.printf("\nValor total da venda: R$%.2f\n", valorTotal);
        } else {
            System.out.println("Nenhum produto adicionado.");
        }
    }
}
