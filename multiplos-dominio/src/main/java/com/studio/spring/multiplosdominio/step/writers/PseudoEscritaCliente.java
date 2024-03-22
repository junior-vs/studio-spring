package com.studio.spring.multiplosdominio.step.writers;


import com.studio.spring.multiplosdominio.model.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PseudoEscritaCliente {

    @Bean
    @SuppressWarnings("rawtypes")
    public ItemWriter processaPseudoEscrita() {
        return items -> items.forEach(System.out::println);
    }

}
