package com.studio.spring.arquivodelimitado.steps.writters;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studio.spring.arquivodelimitado.model.Cliente;


@Configuration
public class PseudoEscritaCliente {

    @Bean
    public ItemWriter<Cliente> processaPseudoEscrita() {
        return items -> items.forEach(System.out::println);
     
    }

}
