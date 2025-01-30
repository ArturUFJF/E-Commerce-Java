package br.ufjf.dcc.dcc025;

public class Cupom {
    private String codigo;
    private float percentualDesconto;
    private boolean ativo;
}

class CupomQuantidadeLimitada extends Cupom {
    private int maximoUtilizacoes;
    private int utilizacoesAtuais;
}

class CupomValorMinimo extends Cupom {
    private float valorMinimo;
}