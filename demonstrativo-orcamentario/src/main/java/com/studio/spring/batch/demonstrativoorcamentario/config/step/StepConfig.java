package com.studio.spring.batch.demonstrativoorcamentario.config.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
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

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Bean
    public Step stepDemonstrativoOrcamentario(ItemReader<?> readerDemonstrativoOrcamentario,
                                              ItemProcessor processorDemonstrativoOrcamentario,
                                              ItemWriter writerDemonstrativoOrcamentario) {
        return new StepBuilder("stepDemonstrativoOrcamentario", this.jobRepository)
                .chunk(10, this.transactionManager)
                .reader(readerDemonstrativoOrcamentario)
                .processor(processorDemonstrativoOrcamentario)
                .writer(writerDemonstrativoOrcamentario)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(2)
                .build();
    }


}
