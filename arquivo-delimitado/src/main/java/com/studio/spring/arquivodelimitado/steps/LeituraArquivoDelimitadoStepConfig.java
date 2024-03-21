package com.studio.spring.arquivodelimitado.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.studio.spring.arquivodelimitado.model.Cliente;

@Configuration
public class LeituraArquivoDelimitadoStepConfig {

    private final PlatformTransactionManager transactionManager;
    private final JobRepository jobRepository;

    public LeituraArquivoDelimitadoStepConfig(PlatformTransactionManager transactionManager, JobRepository jobRepository) {
        this.transactionManager = transactionManager;
        this.jobRepository = jobRepository;
    }

    
    @Bean
    Step leituraArquivoDelimitadoStep(@Qualifier("leituraArquivoDelimitadoReader") ItemReader<Cliente> leituraArquivoDelimitadoReader,
    ItemWriter<Cliente> processaPseudoEscrita) {
        return new StepBuilder("leituraArquivoDelimitadoStep", this.jobRepository)
                .<Cliente, Cliente>chunk(2, this.transactionManager)
                .reader(leituraArquivoDelimitadoReader)
                .writer(processaPseudoEscrita)
                .build();
    }

}
