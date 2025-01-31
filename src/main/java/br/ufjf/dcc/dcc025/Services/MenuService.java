package br.ufjf.dcc.dcc025.Services;

import java.util.Scanner;

public class MenuService {

    public static void exibirMenuCadastro(){
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();

        do{
        System.out.println("\n====CADASTRO DE PRODUTOS/CUPONS====");
        System.out.println("1. Cadastrar produto");
        System.out.println("2. Cadastrar cupom");
        System.out.println("0. Voltar ao menu principal");
        System.out.print("Escolha uma opção: ");

        switch (opcao){
            case 1:


                break;

            case 2:

                break;

            case 0:
                break;

            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
    } while(opcao != 0);

        scanner.close();
    }


    public static void exibirMenuVenda(){
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();

        do{
            System.out.println("====REALIZAR VENDA====");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Cadastrar cupom");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            switch (opcao){
                case 1:
                    Scanner produto = new Scanner(System.in);
                    break;

                case 2:
                    Scanner cupom = new Scanner(System.in);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while(opcao != 0);
    }

    public static void exibirMenuVendas(){
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();

        System.out.println("==== GERENCIAMENTO DE VENDAS====");

        switch (opcao){
            case 1: break;
        }
    }
}
