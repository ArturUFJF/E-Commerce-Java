package br.ufjf.dcc.dcc025.Services;

import br.ufjf.dcc.dcc025.Exceptions.CupomInvalidoException;
import br.ufjf.dcc.dcc025.Models.Cupom;
import br.ufjf.dcc.dcc025.Models.Produto;
import br.ufjf.dcc.dcc025.Models.Venda;

public class VendaService {

    // Adiciona um produto à venda e atualiza o valor total
    public static void adicionarProduto(Venda venda, Produto produto) {
        if (venda != null && produto != null) {
            venda.getProdutos().add(produto);
            atualizarValorTotal(venda);
        }
    }

    // Atualiza o valor total da venda
    public static void atualizarValorTotal(Venda venda) {
        double total = venda.getProdutos().stream().mapToDouble(Produto::getPreco).sum();
        venda.setValorTotal(total);
    }

    // Aplica um cupom à venda usando CupomService
    public static void aplicarCupom(Venda venda, Cupom cupom) {
        if (venda != null && cupom != null) {
            venda.setCupom(cupom);
            try {
                CupomService.aplicarCupom(venda, cupom);
                venda.setCupomAplicado(true);
            } catch (CupomInvalidoException e) {
                System.out.println("Erro ao aplicar cupom: " + e.getMessage());
                venda.setCupomAplicado(false);
            }
        }
    }

    // Finaliza a venda, atualizando valores e aplicando cupons se necessário
    public static void finalizarVenda(Venda venda, GestorVendas gestor) {
        if (venda != null) {
            atualizarValorTotal(venda);
            if (venda.getCupom() != null) {
                aplicarCupom(venda, venda.getCupom());
            }
            gestor.cadastrarVenda(venda);
            exibirVenda(venda);
        }
    }

    // Exibe os detalhes de uma venda
    public static void exibirVenda(Venda venda) {
        if (venda != null) {
            System.out.println("\n=== Detalhes da Venda ===");
            System.out.println("ID da Venda: " + venda.getId());

            if (venda.getCupom() != null && venda.isCupomAplicado()) {
                System.out.println("Cupom aplicado: " + venda.getCupom().getCodigo() +
                        " | Desconto: " + venda.getCupom().getPercentualDesconto() + "%");
            } else {
                System.out.println("Nenhum cupom aplicado ou cupom inválido.");
            }

            if (!venda.getProdutos().isEmpty()) {
                System.out.println("\nProdutos:");
                for (Produto p : venda.getProdutos()) {
                    System.out.printf("- %s | Preço: R$%.2f\n", p.getNome(), p.getPreco());
                }
            } else {
                System.out.println("Nenhum produto adicionado.");
            }

            System.out.printf("\nValor total da venda: R$%.2f\n", venda.getValorTotal());
        }
    }
}
