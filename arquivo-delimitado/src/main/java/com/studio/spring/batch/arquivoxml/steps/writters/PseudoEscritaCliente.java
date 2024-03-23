package com.studio.spring.batch.arquivoxml.steps.writters;

import com.studio.spring.batch.arquivoxml.model.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PseudoEscritaCliente {

    @Bean
    public ItemWriter<Cliente> processaPseudoEscrita() {
        return items -> items.forEach(System.out::println);
     
    }

}
