package br.ufjf.dcc.dcc025.Models;

import br.ufjf.dcc.dcc025.Exceptions.CupomInvalidoException;
import br.ufjf.dcc.dcc025.Services.GestorVendas;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int id;
    private ArrayList<Produto> produtos;
    private double valorTotal;
    private Cupom cupom;
    private boolean cupomAplicado;
    private GestorVendas gestor;

    public Venda(int id, GestorVendas gestor) {
        this.id = id;
        this.produtos = new ArrayList<>();
        this.valorTotal = 0;
        this.gestor = gestor;
        this.cupomAplicado = false;
    }

    public int getId() {
        return id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
    }

    public void realizarVenda() {
        valorTotal = 0;
        for (Produto p : produtos) {
            valorTotal += p.getPreco();
        }

        if (cupom != null && cupom.isAtivo()) {
            try {
                if (cupom instanceof CupomQuantidadeLimitada) {
                    ((CupomQuantidadeLimitada) cupom).aplicarCupom(this);
                } else if (cupom instanceof CupomValorMinimo) {
                    ((CupomValorMinimo) cupom).aplicarCupom(this);
                }
                cupomAplicado = true;
            } catch (CupomInvalidoException e) {
                System.out.println("Aviso: " + e.getMessage());
                cupomAplicado = false;
            }
        }

        exibirVenda();
        gestor.cadastrarVenda(this);
    }

    public void exibirVenda() {
        System.out.println("\n=== Detalhes da Venda ===");
        System.out.println("ID da Venda: " + id);

        if (cupom != null && cupomAplicado) {
            System.out.println("Cupom aplicado: " + cupom.getCodigo() + " | Desconto: " + cupom.getPercentualDesconto() + "%");
        } else {
            System.out.println("Nenhum cupom aplicado ou cupom inválido.");
        }

        if (!produtos.isEmpty()) {
            System.out.println("\nProdutos:");
            for (Produto p : produtos) {
                System.out.printf("- %s | Preço: R$%.2f\n", p.getNome(), p.getPreco());
            }
        } else {
            System.out.println("Nenhum produto adicionado.");
        }

        System.out.printf("\nValor total da venda: R$%.2f\n", valorTotal);
    }
}
