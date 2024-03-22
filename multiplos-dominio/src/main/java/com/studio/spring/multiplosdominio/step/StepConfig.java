package com.studio.spring.multiplosdominio.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
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
    
    @Bean
    public Step stepRepository(FlatFileItemReader reader, ItemWriter writer) {
        return new StepBuilder("multiplos-dominio-step", this.jobRepository)
        .chunk(1, transactionManager)
        .reader(reader)
        .writer(writer)
        
        .build();
    }
}
