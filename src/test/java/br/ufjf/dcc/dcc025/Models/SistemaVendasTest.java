package br.ufjf.dcc.dcc025.Models;

import br.ufjf.dcc.dcc025.Exceptions.CupomInvalidoException;
import br.ufjf.dcc.dcc025.Services.GestorVendas;
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
        Venda venda = new Venda(1, gestor);
        venda.setCupom(cupomLimitado);
        venda.adicionarProduto(notebook);
        venda.realizarVenda();

        // O desconto de 50% deve ser aplicado corretamente
        assertEquals(1500, venda.getValorTotal(), 0.01);

        // Testa se o cupom ficou inativo após uso único
        assertFalse(cupomLimitado.isAtivo());
    }

    @Test
    void testAplicacaoCupomValorMinimo() throws CupomInvalidoException {
        Venda venda = new Venda(2, gestor);
        venda.setCupom(cupomMinimo);
        venda.adicionarProduto(notebook);
        venda.realizarVenda();

        // O desconto de 50% deve ser aplicado corretamente
        assertEquals(1500, venda.getValorTotal(), 0.01);
    }

    @Test
    void testCupomValorMinimoNaoAplicado() throws CupomInvalidoException {
        Venda venda = new Venda(3, gestor);
        venda.setCupom(cupomMinimo);
        venda.adicionarProduto(pizza);
        venda.realizarVenda();

        // O valor deve permanecer inalterado, pois a pizza não atinge o valor mínimo do cupom
        assertEquals(20, venda.getValorTotal(), 0.01);
    }

    @Test
    void testCalculoTotalSemCupom() {
        Venda venda = new Venda(4, gestor);
        venda.adicionarProduto(pizza);
        venda.adicionarProduto(camisetaGucci);
        venda.realizarVenda();

        // Total esperado: 20 + 10000 = 10020
        assertEquals(10020, venda.getValorTotal(), 0.01);
    }

    @Test
    void testRelatorioVendas() {
        Venda venda = new Venda(5, gestor);
        venda.adicionarProduto(notebook);
        venda.realizarVenda();
        gestor.cadastrarVenda(venda); //Tentativa de cadastrar venda com mesmo ID, que já é cadastrada quando realizada

        assertEquals(1, gestor.getVendas().size());
    }

    @Test
    void testListaCuponsAtivos() {
        gestor.listarCuponsAtivos();

        assertTrue(gestor.getCuponsAtivos().contains(cupomMinimo));
        assertTrue(gestor.getCuponsAtivos().contains(cupomLimitado));
    }
}
