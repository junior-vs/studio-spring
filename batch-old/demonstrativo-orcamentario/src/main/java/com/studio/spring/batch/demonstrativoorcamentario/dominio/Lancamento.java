package com.studio.spring.batch.demonstrativoorcamentario.dominio;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Lancamento {
    private String descricao;
    private LocalDate data;
    private Double valor;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setData(Date date) {
        this.data = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    public String toString() {
        return String.format("Lancamento [descricao=%s, data=%s, valor=%s]", descricao, data, valor);
    }

}
