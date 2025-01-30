package br.ufjf.dcc.dcc025;

import java.util.Objects;

public class Cupom {
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
}

class CupomValorMinimo extends Cupom {
    private float valorMinimo;
}