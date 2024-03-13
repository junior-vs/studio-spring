package com.example.demobatch.repositories;

import com.example.demobatch.model.Transacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoReportRepository extends CrudRepository<Transacao, Long> {

    List<Transacao> findAllByOrderByNomeDaLojaAscIdDesc();
}
