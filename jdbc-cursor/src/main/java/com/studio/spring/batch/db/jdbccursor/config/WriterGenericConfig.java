package com.studio.spring.batch.db.jdbccursor.config;

import com.studio.spring.batch.db.jdbccursor.domain.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterGenericConfig {


    @Bean
    ItemWriter<Cliente> jdbcCursorWriter() {
        return clientes -> clientes.forEach(System.out::println);
    }
}
