package com.studio.spring.batch.arquivoxml.steps.readers;



import com.studio.spring.batch.arquivoxml.model.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
public class LeituraArquivoDelimitadoItemReader {

    @SuppressWarnings("null")
    @Bean
    @StepScope
    public FlatFileItemReader<Cliente> leituraArquivoDelimitadoReader(@Value("#{jobParameters['arquivo.delimitado.clientes']}") Resource arquivo) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraArquivoDelimitadoReader")
                .resource(arquivo)
                .delimited()                
                .names(Cliente.headers())
                .targetType(Cliente.class)
                .build();
    }

  


}
