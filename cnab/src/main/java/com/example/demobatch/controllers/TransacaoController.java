package com.example.demobatch.controllers;

import com.example.demobatch.model.TransacaoReport;
import com.example.demobatch.services.TransacaoReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoReportService service;

    public TransacaoController(TransacaoReportService service) {
        this.service = service;
    }

    @GetMapping
    public List<TransacaoReport> list() {

        return service.listTotaisTransacoesPorLoja();
    }
}
