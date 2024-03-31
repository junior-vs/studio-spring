package com.studio.spring.batch.udemy.first.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobConfig {

    private final JobRepository jobRepository;


    public JobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    private static final Logger logger = LoggerFactory.getLogger(JobConfig.class);

    @Bean
    public Job job(Step step) {
        return new JobBuilder("job2", this.jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }


}