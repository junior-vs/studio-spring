package com.studio.spring.batch.demonstrativoorcamentario.config.step.writers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WritterGenerico {

    private static final Logger logger = LoggerFactory.getLogger(WritterGenerico.class);

    @Bean
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ItemWriter processaPseudoEscrita() {
        return items -> items.forEach(i -> logger.info(i.toString()));
    }
}
