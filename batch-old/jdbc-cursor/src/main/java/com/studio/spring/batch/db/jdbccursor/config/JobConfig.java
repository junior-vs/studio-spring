package com.studio.spring.batch.db.jdbccursor.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    private final JobRepository jobRepository;

    public JobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job jdbcCursorReaderJob(Step jdbcCursorReaderStep) {
        return new JobBuilder("jdbcCursorReaderJob", this.jobRepository)
                .start(jdbcCursorReaderStep)
                .incrementer((new RunIdIncrementer()))
                .build();
    }
}
