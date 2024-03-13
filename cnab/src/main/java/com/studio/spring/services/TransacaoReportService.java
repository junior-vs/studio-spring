package com.studio.spring.services;

import  com.studio.spring.model.TipoTransacao;
import  com.studio.spring.model.Transacao;
import  com.studio.spring.model.TransacaoReport;
import  com.studio.spring.repositories.TransacaoReportRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TransacaoReportService {

    private final TransacaoReportRepository repository;

    public TransacaoReportService(TransacaoReportRepository repository) {
        this.repository = repository;
    }

    public List<TransacaoReport> listTotaisTransacoesPorLoja() {


        List<Transacao> transacoes = repository.findAllByOrderByNomeDaLojaAscIdDesc();
        var reporMap = new LinkedHashMap<String, TransacaoReport>();

        transacoes.forEach(transacao -> {
            String nomeDaLoja = transacao.nomeDaLoja();
            var tipoTransacao = TipoTransacao.findByTipo(transacao.tipo());
            BigDecimal valor = transacao.valor().multiply(tipoTransacao.getSinal());
            reporMap.compute(nomeDaLoja, (key, existingReport) -> {
                var report = (existingReport != null) ? existingReport : new TransacaoReport(key, BigDecimal.ZERO, new ArrayList<>());
                return report.addTotal(valor).addTransacao(transacao.withValor(valor));
            });
        });
        return new ArrayList<>(reporMap.values());
    }
}
