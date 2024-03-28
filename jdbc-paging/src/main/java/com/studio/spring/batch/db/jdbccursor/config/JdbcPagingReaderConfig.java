package com.studio.spring.batch.db.jdbccursor.config;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.studio.spring.batch.db.jdbccursor.domain.Cliente;

@Configuration
public class JdbcPagingReaderConfig {

    @SuppressWarnings("null")
    @Bean
    JdbcPagingItemReader<Cliente> jdbcCursorReader(@Qualifier("appDataSource") DataSource datasource, PagingQueryProvider queryProvider) {
        return new JdbcPagingItemReaderBuilder<Cliente>()
                .name("JdbcCursorItemReader")
                .dataSource(datasource)
                .queryProvider(queryProvider)
                .pageSize(1)
                .rowMapper(new BeanPropertyRowMapper<Cliente>(Cliente.class))
                .build();
    }

    @Bean
    SqlPagingQueryProviderFactoryBean providerFactoryBean(@Qualifier("appDataSource") DataSource dataSource) {
        SqlPagingQueryProviderFactoryBean factoryBean = new SqlPagingQueryProviderFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setSelectClause("select *");
        factoryBean.setFromClause("from cliente");
        factoryBean.setSortKey("email");
        return factoryBean;
    }
}
