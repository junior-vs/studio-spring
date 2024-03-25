package com.studio.spring.batch.db.jdbccursor.config;

import com.studio.spring.batch.db.jdbccursor.domain.Cliente;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JdbcCursorReaderConfig {

    private static final String QUERY = "select * from cliente";
    @Bean
    JdbcCursorItemReader<Cliente> jdbcCursorReader(@Qualifier("appDataSource") DataSource datasource) {
        return new JdbcCursorItemReaderBuilder<Cliente>()
                .name("JdbcCursorItemReader")
                .dataSource(datasource)
                .sql(QUERY)
                .rowMapper(new BeanPropertyRowMapper<Cliente>(Cliente.class))
                .build();
    }
}
