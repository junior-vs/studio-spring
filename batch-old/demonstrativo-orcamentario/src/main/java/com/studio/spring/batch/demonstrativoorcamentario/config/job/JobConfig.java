package com.studio.spring.batch.demonstrativoorcamentario.config.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    private final JobRepository jobRepositoru;

    public JobConfig(JobRepository jobRepositoru) {
        this.jobRepositoru = jobRepositoru;
    }

    @SuppressWarnings("null")
    @Bean
    public Job jobDemonstrativoOrcamentario(Step demonstrativoOrcamentarioStep) {
        return new JobBuilder("jobDemonstrativoOrcamentario", this.jobRepositoru)
                .start(demonstrativoOrcamentarioStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
