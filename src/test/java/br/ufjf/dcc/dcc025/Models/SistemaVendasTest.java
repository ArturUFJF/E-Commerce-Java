package br.ufjf.dcc.dcc025.Models;

import br.ufjf.dcc.dcc025.Exceptions.CupomInvalidoException;
import br.ufjf.dcc.dcc025.Services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class SistemaVendasTest {
    private GestorVendas gestor;
    private CupomQuantidadeLimitada cupomLimitado;
    private CupomValorMinimo cupomMinimo;
    private Produto pizza;
    private Produto notebook;
    private Produto camisetaGucci;

    //set-up default pra todos os testes
    @BeforeEach
    void setUp() {
        gestor = new GestorVendas();

        // Criando cupons
        cupomLimitado = new CupomQuantidadeLimitada("DescontoLimitado50", 50, true, 1, gestor);
        cupomMinimo = new CupomValorMinimo("DescontoMinimo50", 50, true, 3000, gestor);

        // Criando produtos
        pizza = new Alimentos(1, "Pizza congelada", 20, LocalDate.of(2025, 10, 31));
        notebook = new Eletronicos(2, "Notebook", 3000, LocalDate.of(2026, 12, 25));
        camisetaGucci = new Roupas(3, "Camiseta Gucci", 10000, "G", "Preta");
    }

    @Test
    void testAplicacaoCupomQuantidadeLimitada() throws CupomInvalidoException {
        Venda venda = new Venda(1);
        venda.setCupom(cupomLimitado);
        VendaService.adicionarProduto(venda, notebook);
        VendaService.finalizarVenda(venda, gestor);

        // O desconto de 50% deve ser aplicado corretamente
        assertEquals(1500, venda.getValorTotal(), 0.01);

        // Testa se o cupom ficou inativo após uso único
        assertFalse(cupomLimitado.isAtivo());
    }

    @Test
    void testAplicacaoCupomValorMinimo() throws CupomInvalidoException {
        Venda venda = new Venda(2);
        venda.setCupom(cupomMinimo);
        VendaService.adicionarProduto(venda, notebook);
        VendaService.finalizarVenda(venda, gestor);

        // O desconto de 50% deve ser aplicado corretamente
        assertEquals(1500, venda.getValorTotal(), 0.01);
    }

    @Test
    void testCupomValorMinimoNaoAplicado() throws CupomInvalidoException {
        Venda venda = new Venda(3);
        venda.setCupom(cupomMinimo);
        VendaService.adicionarProduto(venda, pizza);
        VendaService.finalizarVenda(venda, gestor);

        // O valor deve permanecer inalterado, pois a pizza não atinge o valor mínimo do cupom
        assertEquals(20, venda.getValorTotal(), 0.01);
    }

    @Test
    void testCalculoTotalSemCupom() {
        Venda venda = new Venda(4);
        VendaService.adicionarProduto(venda, pizza);
        VendaService.adicionarProduto(venda, camisetaGucci);
        VendaService.finalizarVenda(venda, gestor);

        // Total esperado: 20 + 10000 = 10020
        assertEquals(10020, venda.getValorTotal(), 0.01);
    }

    @Test
    void testRelatorioVendas() {
        Venda venda = new Venda(5);
        VendaService.adicionarProduto(venda, notebook);
        VendaService.finalizarVenda(venda, gestor);
        gestor.cadastrarVenda(venda); //Tentativa de cadastrar venda com mesmo ID, que já é cadastrada quando realizada

        assertEquals(1, gestor.getVendas().size());
    }

    @Test
    void testListaCuponsAtivos() {
        gestor.listarCuponsAtivos();

        assertTrue(gestor.getCuponsAtivos().contains(cupomMinimo));
        assertTrue(gestor.getCuponsAtivos().contains(cupomLimitado));
    }

    @Test
    void testAplicacaoCupomInvalido() {
        Venda venda = new Venda(6);
        Cupom cupomInvalido = new CupomQuantidadeLimitada("CupomInativo", 20, false, 1, gestor);
        venda.setCupom(cupomInvalido);
        VendaService.adicionarProduto(venda, notebook);

        // Finaliza a venda (não lança exceção)
        VendaService.finalizarVenda(venda, gestor);

        // Como o cupom é inválido, o valor total não deve ser alterado
        assertEquals(3000, venda.getValorTotal(), 0.01);
    }


    @Test
    void testAdicionarProdutosVenda() {
        Venda venda = new Venda(7);
        VendaService.adicionarProduto(venda, pizza);
        VendaService.adicionarProduto(venda, notebook);

        assertEquals(2, venda.getProdutos().size());
        assertTrue(venda.getProdutos().contains(pizza));
        assertTrue(venda.getProdutos().contains(notebook));
        //Verifica se os produtos adicionados realmente estão no array de produtos da venda
    }

    @Test
    void testVendaSemProdutos() {
        Venda venda = new Venda(8);
        VendaService.finalizarVenda(venda, gestor);

        assertEquals(0, venda.getValorTotal(), 0.01);
        assertEquals(0, venda.getProdutos().size());
        //Testa o funcionamento de uma venda sem produtos
    }

    @Test
    void testCupomUsoMultiplo() throws CupomInvalidoException {
        Cupom cupomMultiplo = new CupomQuantidadeLimitada("MultiUse", 10, true, 5, gestor);
        Venda venda1 = new Venda(9);
        Venda venda2 = new Venda(10);

        venda1.setCupom(cupomMultiplo);
        venda2.setCupom(cupomMultiplo);

        VendaService.adicionarProduto(venda1, notebook);
        VendaService.finalizarVenda(venda1, gestor);

        VendaService.adicionarProduto(venda2, notebook);
        VendaService.finalizarVenda(venda2, gestor);

        // O cupom ainda deve estar ativo, pois tem limite de 5 usos
        assertTrue(cupomMultiplo.isAtivo());
    }

}
