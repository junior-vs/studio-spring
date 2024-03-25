package com.studio.spring.batch.samples.samplefilexml.config;

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
    public Job sampleFileXmlJob(Step sampleFileXmlStep) {
        return new JobBuilder("sampleFileXmlJob", this.jobRepository)
                .start(sampleFileXmlStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
