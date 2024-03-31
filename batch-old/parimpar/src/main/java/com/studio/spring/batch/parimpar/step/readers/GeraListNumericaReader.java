package com.studio.spring.batch.parimpar.step.readers;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeraListNumericaReader {


    @Value("${list.limit}")
    private String limit;


    @Bean
    public IteratorItemReader<Integer> gera() {
        return new IteratorItemReader<Integer>(IntStream.range(1, Integer.valueOf(limit) + 1)
                .boxed()
                .collect(Collectors.toList()));
    }
}