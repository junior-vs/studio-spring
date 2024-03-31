package com.studio.spring.batch.db.jdbccursor.config;

import com.studio.spring.batch.db.jdbccursor.domain.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
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

    @SuppressWarnings("null")
    @Bean
    Step jdbcCursorReaderStep(ItemReader<Cliente> jdbcCursorReader, ItemWriter<Cliente> jdbcCursorWriter) {
        return new StepBuilder("jdbcCursorReaderStep", this.jobRepository)
                .<Cliente, Cliente>chunk(10, this.transactionManager)
                .reader(jdbcCursorReader)
                .writer(jdbcCursorWriter)
                .build();
    }
}
