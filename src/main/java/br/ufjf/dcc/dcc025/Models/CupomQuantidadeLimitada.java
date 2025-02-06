package br.ufjf.dcc.dcc025.Models;

import br.ufjf.dcc.dcc025.Services.GestorVendas;

public class CupomQuantidadeLimitada extends Cupom {
    private int maximoUtilizacoes;
    private int utilizacoesAtuais;

    public CupomQuantidadeLimitada(GestorVendas gestor) {
        gestor.cadastrarCupom(this);
    }

    public CupomQuantidadeLimitada(String codigo, float percentualDesconto, boolean ativo, int maximoUtilizacoes, GestorVendas gestor) {
        super(codigo, percentualDesconto, ativo, gestor);
        this.maximoUtilizacoes = maximoUtilizacoes;
        this.utilizacoesAtuais = maximoUtilizacoes;
    }

    public int getMaximoUtilizacoes() {
        return maximoUtilizacoes;
    }

    public void setMaximoUtilizacoes(int maximoUtilizacoes) {
        this.maximoUtilizacoes = maximoUtilizacoes;
    }

    public int getUtilizacoesAtuais() {
        return utilizacoesAtuais;
    }

    public void setUtilizacoesAtuais(int utilizacoesAtuais) {
        this.utilizacoesAtuais = utilizacoesAtuais;
    }

}
