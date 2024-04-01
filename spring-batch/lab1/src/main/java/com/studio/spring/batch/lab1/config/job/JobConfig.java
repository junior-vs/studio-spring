package com.studio.spring.batch.lab1.config.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    private JobRepository jobRepository;

    public JobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job importUserJob(JobExecutionListener listener, Step importUserStep) {
        return new JobBuilder("importUserJob", this.jobRepository)
                .listener(listener)
                .start(importUserStep)
                .incrementer((new RunIdIncrementer()))
                .build();

    }



}
