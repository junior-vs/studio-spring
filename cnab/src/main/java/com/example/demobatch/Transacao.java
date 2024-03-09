package com.example.demobatch;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public record Transacao(
                Long id,
                Integer tipo,
                LocalDate data,
                BigDecimal valor,
                String cpf,
                String cartao,
                LocalTime hora,
                String donoDaLoja,
                String nomeDaLoja) {

        public Transacao withValor(BigDecimal valor) {
                return new Transacao(this.id,
                                this.tipo, this.data, valor, this.cpf,
                                this.cartao, this.hora,
                                this.donoDaLoja, this.nomeDaLoja);
        }

        public Transacao withData(String data) throws DateTimeParseException {
                LocalDate dataFormatada = LocalDate
                                .parse(data, DateTimeFormatter.ofPattern("yyyyMMdd"));

                return new Transacao(this.id,
                                this.tipo, dataFormatada, this.valor, this.cpf,
                                this.cartao, this.hora,
                                this.donoDaLoja, this.nomeDaLoja);
        }

        public Transacao withHora(String hora) throws DateTimeParseException {

                LocalTime horaFormatada = LocalTime
                                .parse(hora, DateTimeFormatter.ofPattern("HHmmss"));
                return new Transacao(this.id,
                                this.tipo, this.data, this.valor, this.cpf,
                                this.cartao, horaFormatada,
                                this.donoDaLoja, this.nomeDaLoja);
        }

}
