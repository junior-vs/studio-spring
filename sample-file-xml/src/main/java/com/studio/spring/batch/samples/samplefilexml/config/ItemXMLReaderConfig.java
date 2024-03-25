package com.studio.spring.batch.samples.samplefilexml.config;

import com.studio.spring.batch.samples.samplefilexml.model.CustomerCredit;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
public class ItemXMLReaderConfig {

    @Bean
    @StepScope
    public StaxEventItemReader<CustomerCredit> itemXMLWriter(@Value("#{jobParameters['output']}") Resource resource, XStreamMarshaller customerCreditMarshaller) {

        return new StaxEventItemReaderBuilder<CustomerCredit>()
                .name("itemXMLWriter")
                .resource(resource)
                .addFragmentRootElements("customer")
                .unmarshaller(customerCreditMarshaller)
                .build();
    }




}
