package com.studio.spring.multiplosdominio.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public StepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public Step stepRepository(MultiResourceItemReader multiResourceMultiplosDominioReader, ItemWriter processaPseudoEscrita) {
        return new StepBuilder("multiplos-dominio-multiplos-arquivos-step", this.jobRepository)
                .chunk(1, transactionManager)
                .reader(multiResourceMultiplosDominioReader)
                .writer(processaPseudoEscrita)
                .build();
    }
}
