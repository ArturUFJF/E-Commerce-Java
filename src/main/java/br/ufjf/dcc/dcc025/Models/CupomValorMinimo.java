package br.ufjf.dcc.dcc025.Models;

import br.ufjf.dcc.dcc025.Services.GestorVendas;

public class CupomValorMinimo extends Cupom {
    private float valorMinimo;

    public CupomValorMinimo() {}

    public CupomValorMinimo(String codigo, float percentualDesconto, boolean ativo, float valorMinimo, GestorVendas gestor) {
        super(codigo, percentualDesconto, ativo, gestor);
        this.valorMinimo = valorMinimo;
    }

    public float getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(float valorMinimo) {
        this.valorMinimo = valorMinimo;
    }
}
