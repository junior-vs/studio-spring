package com.studio.spring.batch.arquivoxml.steps;

import com.studio.spring.batch.arquivoxml.model.Trade;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ProcessarArquivoXMLStepsConfig {

    private final PlatformTransactionManager transactionManager;
    private final JobRepository jobRepository;

    public ProcessarArquivoXMLStepsConfig(PlatformTransactionManager transactionManager, JobRepository jobRepository) {
        this.transactionManager = transactionManager;
        this.jobRepository = jobRepository;
    }

    
    @Bean
    Step processarArquivoTradeXML(ItemReader<Trade> itemXMLReader,
                                  ItemWriter<Trade> itemXMLWriter) {
        return new StepBuilder("processarArquivoTradeXML", this.jobRepository)
                .<Trade, Trade>chunk(2, this.transactionManager)
                .reader(itemXMLReader)
                .writer(itemXMLWriter)
                .build();
    }

}
