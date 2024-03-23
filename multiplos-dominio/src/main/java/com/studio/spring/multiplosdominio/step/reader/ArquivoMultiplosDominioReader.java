package com.studio.spring.multiplosdominio.step.reader;


import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ArquivoMultiplosDominioReader {

    @Bean
    @StepScope
    FlatFileItemReader processa(@Value("#{jobParameters['arquivo.multiplos.clientes']}") Resource resource, LineMapper lineMapper) {
        return new FlatFileItemReaderBuilder<>()
                .name("arquivoMultiplosDominioReader")
                .resource(resource)
                .lineMapper(lineMapper)
                .build();
    }

}
