package com.studio.spring.batch.arquivoposicional.steps.writters;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studio.spring.batch.arquivoposicional.model.Cliente;


@Configuration
public class PseudoEscritaCliente {

    @Bean
    public ItemWriter<Cliente> processaPseudoEscrita() {
        return items -> items.forEach(System.out::println);
     
    }

}
