package com.studio.spring.batch.arquivoposicional.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.studio.spring.batch.arquivoposicional.model.Cliente;

@Configuration
public class LeituraArquivoPosicionalStepConfig {
    
    private final PlatformTransactionManager transactionManager;
    private final JobRepository jobRepository;
    
    public LeituraArquivoPosicionalStepConfig(PlatformTransactionManager transactionManager, JobRepository jobRepository) {
        this.transactionManager = transactionManager;
        this.jobRepository = jobRepository;
    }
    
    
    @Bean    
    Step leituraArquivoLarguraFixaStep(@Qualifier("leituraArquivoPosicionalReader") ItemReader<Cliente> leituraArquivoPosicionalReader,
    ItemWriter<Cliente> processaPseudoEscrita) {
        return new StepBuilder("leituraArquivoLarguraFixaStep", this.jobRepository)
                .<Cliente, Cliente>chunk(2, this.transactionManager)
                .reader(leituraArquivoPosicionalReader)
                .writer(processaPseudoEscrita)
                .build();
    }

}
