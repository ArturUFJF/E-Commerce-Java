package br.ufjf.dcc.dcc025.Models;

import br.ufjf.dcc.dcc025.Exceptions.CupomInvalidoException;
import br.ufjf.dcc.dcc025.Services.GestorVendas;

import java.util.Objects;

public abstract class Cupom {
    private String codigo;
    private float percentualDesconto;
    private boolean ativo;

    public Cupom(){}

    public Cupom(String codigo, float percentualDesconto, boolean ativo, GestorVendas gestor) {
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.ativo = ativo;
        gestor.cadastrarCupom(this);
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

    public void aplicarCupom(Venda venda) throws CupomInvalidoException{
        if(utilizacoesAtuais > 0) {
            venda.setValorTotal(venda.getValorTotal() * (this.getPercentualDesconto()/100));
            utilizacoesAtuais--;

            if(utilizacoesAtuais == 0)
                this.setAtivo(false);
        }

        else {
            this.setAtivo(false);
            throw new CupomInvalidoException("Não há mais utilizações disponíveis no cupom!");
        }
    }

}



class CupomValorMinimo extends Cupom {
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

    public void aplicarCupom (Venda venda) throws CupomInvalidoException{
        if(venda.getValorTotal() >= this.getValorMinimo())
            venda.setValorTotal(venda.getValorTotal() * (this.getPercentualDesconto()/100));
        else
            throw new CupomInvalidoException("Venda possui valor menor que o mínimo necessário!");
    }
}