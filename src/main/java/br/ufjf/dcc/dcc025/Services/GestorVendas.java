package br.ufjf.dcc.dcc025.Services;

import br.ufjf.dcc.dcc025.Models.Cupom;
import br.ufjf.dcc.dcc025.Models.Venda;

import java.util.ArrayList;
import java.util.List;

interface Relatorio {
    void gerarRelatorioVendas();
    void listarCuponsAtivos();
}

public class GestorVendas implements Relatorio {
    private List<Venda> vendas;
    private List<Cupom> cuponsAtivos;

    public GestorVendas() {
        this.vendas = new ArrayList<>();
        this.cuponsAtivos = new ArrayList<>();
    }

    public void cadastrarVenda(Venda venda) {
        vendas.add(venda);
        System.out.println("Venda cadastrada com sucesso: ID " + venda.getId());
    }

    public void cadastrarCupom(Cupom cupom) {
        cuponsAtivos.add(cupom);
        System.out.println("Cupom cadastrado: " + cupom.getCodigo());
    }

    @Override
    public void gerarRelatorioVendas() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        System.out.println("\n=== Relatório de Vendas ===");
        for (Venda venda : vendas) {
            venda.realizarVenda();
        }
    }

    @Override
    public void listarCuponsAtivos() {
        if (cuponsAtivos.isEmpty()) {
            System.out.println("Nenhum cupom ativo.");
            return;
        }

        System.out.println("\n=== Cupons Ativos ===");
        for (Cupom cupom : cuponsAtivos) {
            System.out.println("- " + cupom.getCodigo() + " | Desconto: " + cupom.getPercentualDesconto() + "%");
        }
    }

}
