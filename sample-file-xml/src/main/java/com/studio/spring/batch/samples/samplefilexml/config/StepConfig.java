package com.studio.spring.batch.samples.samplefilexml.config;

import com.studio.spring.batch.samples.samplefilexml.model.CustomerCredit;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public StepConfig(JobRepository jobRepository, JdbcTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step sampleFileXmlStep(ItemReader<CustomerCredit> itemXmlReader,
                                  ItemProcessor<CustomerCredit, CustomerCredit> itemCustormerCreditProcessor,
                                  ItemWriter<CustomerCredit> itemObjectWriter) {
        return new StepBuilder("sampleFileXmlStep", this.jobRepository)
                .<CustomerCredit, CustomerCredit>chunk(100, this.transactionManager)
                .reader(itemXmlReader)
                .processor(itemCustormerCreditProcessor)
                .writer(itemObjectWriter)
                .build();
    }
}
