package com.studio.spring.batch.steps.readers;

import com.studio.spring.arquivodelimitado.model.Cliente;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ArquivoReader {


    @Bean
    public FlatFileItemReader<Cliente> arquivoNaoEstruturadoReader(@Value("#{jobParameters['arquivoClientes']}") Resource resource, LineMapper<Cliente> lineMapper) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("arquivoNaoEstruturadoReader")
                .resource(resource)
                .lineMapper(lineMapper)
                .build();
    }

}
