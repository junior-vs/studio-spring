package com.studio.spring.batch.arquivoposicional.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {
    private final JobRepository jobRepository;

    public JobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job jobLeituraArquivoPosicional(@Qualifier("leituraArquivoLarguraFixaStep") Step leituraArquivoLarguraFixaStep) {
        return  new JobBuilder("jobLeituraArquivoPosicional", this.jobRepository)
                .start(leituraArquivoLarguraFixaStep)
                .incrementer(new RunIdIncrementer())
                .build();
        
    }

}

