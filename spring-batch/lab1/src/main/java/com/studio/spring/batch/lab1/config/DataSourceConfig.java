package com.studio.spring.batch.lab1.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    DataSource springDataSourceInfra() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource")
    DataSource dataSourceBusiness() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name ="mainJdbcTemplate")
    public JdbcTemplate mainJdbcTemplate(@Qualifier("dataSourceBusiness") DataSource mainDataSource) {
        return new JdbcTemplate(mainDataSource);
    }
}
