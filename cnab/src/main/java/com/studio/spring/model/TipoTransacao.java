package com.studio.spring.model;

import java.math.BigDecimal;

public enum TipoTransacao {

    DEBITO(1), BOLETO(2), FINANCIAMENTO(3), CREDITO(4), RECEBIMENTO_EMPRESTIMO(5), VENDAS(6), RECEBIMENTO_TED(7), RECEBIMENTO_DOC(8), ALUGUEL(9);


    private final int tipo;

    TipoTransacao(int tipo) {

        this.tipo = tipo;
    }

    public BigDecimal getSinal() {
        return switch (tipo) {
            case 1, 4, 5, 6, 7, 8 -> BigDecimal.ONE; //créditos
            case 2, 3, 9 -> BigDecimal.valueOf(-1); //débitos
            default -> BigDecimal.ZERO;
        };
    }

    public static TipoTransacao findByTipo(int tipo) {
        for (TipoTransacao tipoTransacao : values()) {
            if (tipoTransacao.tipo == tipo) {
                return tipoTransacao;
            }
        }
        throw  new IllegalArgumentException("Invalid tipo: " + tipo);
    }
}
