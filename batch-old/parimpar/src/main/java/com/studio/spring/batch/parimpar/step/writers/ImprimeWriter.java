package com.studio.spring.batch.parimpar.step.writers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Configuration
public class ImprimeWriter{

    private final Logger logger = LoggerFactory.getLogger(ImprimeWriter.class);

    @Bean
    ItemWriter<String> write() {
        return items -> items.forEach(System.out::println);
    }

}