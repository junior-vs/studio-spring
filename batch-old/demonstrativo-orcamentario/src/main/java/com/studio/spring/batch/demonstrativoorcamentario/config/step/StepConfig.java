package com.studio.spring.batch.demonstrativoorcamentario.config.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.studio.spring.batch.demonstrativoorcamentario.dominio.GrupoLancamento;
import com.studio.spring.batch.demonstrativoorcamentario.dominio.Lancamento;

@Configuration
public class StepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public StepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @SuppressWarnings({ "rawtypes", "null", "unchecked" })
    @Bean
    public Step demonstrativoOrcamentarioStep(MultiResourceItemReader<GrupoLancamento> demonstrativoOrcamentarioReader,
            ItemWriter demonstrativoOrcamentarioWriter) {
        return new StepBuilder("stepDemonstrativoOrcamentario", this.jobRepository)
                .<GrupoLancamento, GrupoLancamento>chunk(1, transactionManager)
                .reader(demonstrativoOrcamentarioReader)
                .writer(demonstrativoOrcamentarioWriter)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(2)
                .build();
    }

}
