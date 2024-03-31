package com.studio.spring.batch.udemy.first.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ImprimiOlaStepConfig {


    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public ImprimiOlaStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }


    @Bean
    public Step step(Tasklet imprimiOlaTasklet) {
        return new StepBuilder("step", this.jobRepository)
                .tasklet(imprimiOlaTasklet, this.transactionManager)
                .build();
    }



}

