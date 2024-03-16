package com.studio.spring.batch.parimpar.step;

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
public class ImprimeParImparStep {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public ImprimeParImparStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step imprime(ItemReader<Integer> geraListNumerica,
    ItemProcessor<Integer,String> parOuImparProcessor, ItemWriter<String> imprimeWriter) {
        return new StepBuilder("imprimeParImparStep", this.jobRepository)
                .<Integer, String>chunk(2, this.transactionManager)
                .reader(geraListNumerica)
                .processor(parOuImparProcessor)
                .writer(imprimeWriter)
                .build();
    }
}
