package br.ufjf.dcc.dcc025.Models;

import br.ufjf.dcc.dcc025.Services.MenuService;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("==== MENU ====");
            System.out.println("1. Cadastrar produtos/cupons");
            System.out.println("2. Realizar venda");
            System.out.println("3. Gerenciar vendas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    MenuService.exibirMenuCadastro();
                    break;

                case 2:
                    MenuService.exibirMenuVenda();
                    break;

                case 3:
                    MenuService.exibirMenuVendas();
                    break;

                case 0:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}