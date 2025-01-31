package br.ufjf.dcc.dcc025.Models;

import java.util.Objects;

public abstract class Cupom {
    private String codigo;
    private float percentualDesconto;
    private boolean ativo;

    public Cupom(){}

    public Cupom(String codigo, float percentualDesconto, boolean ativo) {
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.ativo = ativo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(float percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cupom cupom = (Cupom) o;
        return Objects.equals(codigo, cupom.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

}



class CupomQuantidadeLimitada extends Cupom {
    private int maximoUtilizacoes;
    private int utilizacoesAtuais;

    public CupomQuantidadeLimitada() {}

    public CupomQuantidadeLimitada(String codigo, float percentualDesconto, boolean ativo, int maximoUtilizacoes, int utilizacoesAtuais) {
        super(codigo, percentualDesconto, ativo);
        this.maximoUtilizacoes = maximoUtilizacoes;
        this.utilizacoesAtuais = utilizacoesAtuais;
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



class CupomValorMinimo extends Cupom {
    private float valorMinimo;

    public CupomValorMinimo() {}

    public CupomValorMinimo(String codigo, float percentualDesconto, boolean ativo, float valorMinimo) {
        super(codigo, percentualDesconto, ativo);
        this.valorMinimo = valorMinimo;
    }

    public float getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(float valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

}