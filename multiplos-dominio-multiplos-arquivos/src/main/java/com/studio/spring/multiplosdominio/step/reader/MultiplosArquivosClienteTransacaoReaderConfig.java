package com.studio.spring.multiplosdominio.step.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class MultiplosArquivosClienteTransacaoReaderConfig {

    @StepScope
    @Bean
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public MultiResourceItemReader multiResourceMultiplosDominioReader( @Value("file:files/clientes*.txt") Resource[] arquivos, FlatFileItemReader reader) {
        return new MultiResourceItemReaderBuilder()
                .name("multiResourceMultiplosDominioReader")
                .resources(arquivos)
                .delegate(new ArquivoClienteTransacaoReader(reader))
                .build();
    }
}
