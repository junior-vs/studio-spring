package com.studio.spring.batch.lab1.config.step;

import javax.sql.DataSource;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonItemWriter {

    @Bean
    JdbcBatchItemWriter<Person> personWriter(@Qualifier("dataSourceBusiness") DataSource dataSourceBusiness) {

        return new JdbcBatchItemWriterBuilder<Person>()
                .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
                .dataSource(dataSourceBusiness)
                .beanMapped()
                .build();
    }

}
