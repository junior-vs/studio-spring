package com.studio.spring.batch.steps;

import com.studio.spring.arquivodelimitado.model.Cliente;
import com.studio.spring.batch.steps.readers.ArquivoReader;
import com.studio.spring.batch.steps.writers.PseudoWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ProcessaArquivoNaoEstruturadoStepConfig {

    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step processaArquivoNaoEstruturadoStep(ArquivoReader arquivoNaoEstruturadoReader, PseudoWriter arquivoNaoEstruturadoWriter) {
        return new StepBuilder("processaArquivoNaoEstruturadoStep", this.jobRepository)
                .<Cliente, Cliente>chunk(10, this.transactionManager)
                .reader(arquivoNaoEstruturadoReader)
                .writer(arquivoNaoEstruturadoWriter)
                .build();
    }
}
