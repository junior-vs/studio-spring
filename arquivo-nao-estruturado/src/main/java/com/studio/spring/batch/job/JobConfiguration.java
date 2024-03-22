package com.studio.spring.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {

    private final JobRepository jobRepository;

    public JobConfiguration(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job getJob(Step arquivoNaoEstruturadoStep) {
        return new JobBuilder("arquivo-nao-estruturado",this.jobRepository)
                .start(arquivoNaoEstruturadoStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
