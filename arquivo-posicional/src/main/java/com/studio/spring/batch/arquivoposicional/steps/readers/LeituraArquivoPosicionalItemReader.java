package com.studio.spring.batch.arquivoposicional.steps.readers;



import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.studio.spring.batch.arquivoposicional.model.Cliente;


@Configuration
public class LeituraArquivoPosicionalItemReader {

    @Bean
    @StepScope
    public FlatFileItemReader<Cliente> leituraArquivoPosicionalReader(@Value("#{jobParameters['arquivoClientes']}") Resource arquivo) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraArquivoPosicionalReader")
                .resource(arquivo)
                .fixedLength()
                .columns(Cliente.campos())
                .names("nome", "sobrenome", "idade", "email")
                .targetType(Cliente.class)
                .build();
    }

  


}
