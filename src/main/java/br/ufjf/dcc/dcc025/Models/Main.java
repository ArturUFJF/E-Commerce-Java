package br.ufjf.dcc.dcc025.Models;

import br.ufjf.dcc.dcc025.Services.GestorVendas;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // CRIANDO GESTOR DE VENDAS
        GestorVendas gestor = new GestorVendas();

        // CADASTRO DE PRODUTOS
        System.out.println("CADASTRANDO PRODUTOS:");
        Alimentos pizza = new Alimentos(1, "Pizza congelada", 20, LocalDate.of(2025, 10, 31));
        Eletronicos notebook = new Eletronicos(2, "Notebook", 3000, LocalDate.of(2026, 12, 25));
        Roupas camisetaGucci = new Roupas(3, "Camiseta Gucci", 10000, "G", "Preta");


        // CADASTRO DE CUPONS
        System.out.println("\n\nCADASTRANDO CUPONS:");
        CupomQuantidadeLimitada cupomLimitado = new CupomQuantidadeLimitada("DescontoLimitado50", 50, true, 1, gestor);
        CupomValorMinimo cupomMinimo = new CupomValorMinimo("DescontoMinimo50", 50, true, 3000, gestor);


        // REALIZANDO VENDAS
        System.out.println("\n\nTESTE DE REALIZAÇÃO DE VENDAS:");

        // Primeira venda
        System.out.println("=VENDA 1=");
        Venda vendaTeste = new Venda(1, gestor);
        vendaTeste.setCupom(cupomLimitado);
        vendaTeste.adicionarProduto(pizza);
        vendaTeste.adicionarProduto(notebook);
        vendaTeste.adicionarProduto(camisetaGucci);
        vendaTeste.realizarVenda();
        System.out.println("------------------------");

        // Segunda venda - Usa o mesmo cupom limitado
        System.out.println("\n=VENDA 2=");
        Venda vendaTeste2 = new Venda(2, gestor);
        vendaTeste2.setCupom(cupomLimitado);
        vendaTeste2.adicionarProduto(camisetaGucci);
        vendaTeste2.realizarVenda();
        System.out.println("------------------------");

        // Terceira venda - Usa o cupom de valor mínimo com notebook
        System.out.println("\n=VENDA 3=");
        Venda vendaTeste3 = new Venda(3, gestor);
        vendaTeste3.setCupom(cupomMinimo);
        vendaTeste3.adicionarProduto(notebook);
        vendaTeste3.realizarVenda();
        System.out.println("------------------------");

        // Quarta venda - Usa o cupom de valor mínimo com pizza
        System.out.println("\n=VENDA 4=");
        Venda vendaTeste4 = new Venda(4, gestor);
        vendaTeste4.setCupom(cupomMinimo);
        vendaTeste4.adicionarProduto(pizza);
        vendaTeste4.realizarVenda();
        System.out.println("------------------------");


        //IMPRIMINDO RELATÓRIOS
        System.out.println("\nRELATÓRIOS:");
        gestor.gerarRelatorioVendas();
        gestor.listarCuponsAtivos();
    }
}
