package br.ufjf.dcc.dcc025.Services;

import br.ufjf.dcc.dcc025.Exceptions.CupomInvalidoException;
import br.ufjf.dcc.dcc025.Models.Cupom;
import br.ufjf.dcc.dcc025.Models.CupomQuantidadeLimitada;
import br.ufjf.dcc.dcc025.Models.CupomValorMinimo;
import br.ufjf.dcc.dcc025.Models.Venda;

public class CupomService {

    public static void aplicarCupom(Venda venda, Cupom cupom) throws CupomInvalidoException {
        if (cupom == null) {
            throw new CupomInvalidoException("Cupom inválido! O cupom não pode ser nulo.");
        }

        if (!cupom.isAtivo()) {
            throw new CupomInvalidoException("Este cupom não está mais ativo!");
        }

        if (cupom instanceof CupomQuantidadeLimitada) {
            aplicarCupomQuantidadeLimitada(venda, (CupomQuantidadeLimitada) cupom);
        } else if (cupom instanceof CupomValorMinimo) {
            aplicarCupomValorMinimo(venda, (CupomValorMinimo) cupom);
        } else {
            throw new CupomInvalidoException("Tipo de cupom desconhecido.");
        }
    }

    private static void aplicarCupomQuantidadeLimitada(Venda venda, CupomQuantidadeLimitada cupom) throws CupomInvalidoException {
        if (cupom.getUtilizacoesAtuais() <= 0) {
            cupom.setAtivo(false);
            throw new CupomInvalidoException("Não há mais utilizações disponíveis no cupom!");
        }

        double novoValorTotal = venda.getValorTotal() * (cupom.getPercentualDesconto() / 100);
        venda.setValorTotal(novoValorTotal);

        cupom.setUtilizacoesAtuais(cupom.getUtilizacoesAtuais() - 1);

        if (cupom.getUtilizacoesAtuais() == 0) {
            cupom.setAtivo(false);
        }
    }

    private static void aplicarCupomValorMinimo(Venda venda, CupomValorMinimo cupom) throws CupomInvalidoException {
        if (venda.getValorTotal() < cupom.getValorMinimo()) {
            throw new CupomInvalidoException("Venda possui valor menor que o mínimo necessário!");
        }

        double novoValorTotal = venda.getValorTotal() * (cupom.getPercentualDesconto() / 100);
        venda.setValorTotal(novoValorTotal);
    }
}
