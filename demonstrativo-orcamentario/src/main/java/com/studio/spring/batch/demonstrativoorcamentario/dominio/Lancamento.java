package com.studio.spring.batch.demonstrativoorcamentario.dominio;

import java.time.LocalDate;

public class Lancamento {
    private int codigoNaturezaDespesa;
    private String descricaoNaturezaDespesa;
    private String descricaoLancamento;
    private LocalDate dataLancamento;
    private double valorLancamento;

    // Construtor
    public Lancamento(int codigoNaturezaDespesa, String descricaoNaturezaDespesa, String descricaoLancamento, LocalDate dataLancamento, double valorLancamento) {
        this.codigoNaturezaDespesa = codigoNaturezaDespesa;
        this.descricaoNaturezaDespesa = descricaoNaturezaDespesa;
        this.descricaoLancamento = descricaoLancamento;
        this.dataLancamento = dataLancamento;
        this.valorLancamento = valorLancamento;
    }

    // Getters e Setters
    public int getCodigoNaturezaDespesa() {
        return codigoNaturezaDespesa;
    }

    public void setCodigoNaturezaDespesa(int codigoNaturezaDespesa) {
        this.codigoNaturezaDespesa = codigoNaturezaDespesa;
    }

    public String getDescricaoNaturezaDespesa() {
        return descricaoNaturezaDespesa;
    }

    public void setDescricaoNaturezaDespesa(String descricaoNaturezaDespesa) {
        this.descricaoNaturezaDespesa = descricaoNaturezaDespesa;
    }

    public String getDescricaoLancamento() {
        return descricaoLancamento;
    }

    public void setDescricaoLancamento(String descricaoLancamento) {
        this.descricaoLancamento = descricaoLancamento;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public double getValorLancamento() {
        return valorLancamento;
    }

    public void setValorLancamento(double valorLancamento) {
        this.valorLancamento = valorLancamento;
    }
}
