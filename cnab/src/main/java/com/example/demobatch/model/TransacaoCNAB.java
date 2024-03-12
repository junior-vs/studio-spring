package com.example.demobatch.model;

import java.math.BigDecimal;

public record TransacaoCNAB(
        Integer tipo,
        String data,
        BigDecimal valor,
        String cpf,
        String cartao,
        String hora,
        String donoDaLoja,
        String nomeDaLoja) {

}
