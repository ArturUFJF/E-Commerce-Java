package br.ufjf.dcc.dcc025.Services;

import br.ufjf.dcc.dcc025.Models.Cupom;
import br.ufjf.dcc.dcc025.Models.Venda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

interface Relatorio {
    void gerarRelatorioVendas();
    void listarCuponsAtivos();
}

public class GestorVendas implements Relatorio {
    private List<Venda> vendas;
    private List<Cupom> cuponsAtivos;

    public List<Venda> getVendas() {
        return vendas;
    }

    public List<Cupom> getCuponsAtivos() {
        return cuponsAtivos;
    }

    public GestorVendas() {
        this.vendas = new ArrayList<>();
        this.cuponsAtivos = new ArrayList<>();
    }

    public void cadastrarVenda(Venda venda) {
        if (venda != null && !vendas.contains(venda)) { // Evita vendas duplicadas
            vendas.add(venda);
            System.out.println("Venda cadastrada com sucesso: ID " + venda.getId());
        } else {
            System.out.println("Erro: Venda já cadastrada ou inválida.");
        }
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

        System.out.println("=== Relatório de Vendas ===");
        for (Venda venda : vendas) {
            venda.exibirVenda();
            System.out.println("------------------------");
        }
    }

    @Override
    public void listarCuponsAtivos() {
        Iterator<Cupom> iterator = cuponsAtivos.iterator();
        while (iterator.hasNext()) {
            Cupom cupom = iterator.next();
            if (!cupom.isAtivo()) {
                iterator.remove();
            }
        }

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
