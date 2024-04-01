package com.studio.spring.batch.lab1.config.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StepConfig {

    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;

    public StepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step importUserStep(FlatFileItemReader<Person> personReader, PersonItemProcessor personProcessor,
            JdbcBatchItemWriter<Person> personwriter) {
        return new StepBuilder("importUserStep", this.jobRepository)
                .<Person, Person>chunk(10, this.transactionManager)
                .reader(personReader)
                .processor(personProcessor)
                .writer(personwriter)
                .build();
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

}