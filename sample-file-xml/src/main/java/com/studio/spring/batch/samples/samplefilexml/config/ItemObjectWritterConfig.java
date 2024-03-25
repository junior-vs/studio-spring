package com.studio.spring.batch.samples.samplefilexml.config;

import com.studio.spring.batch.samples.samplefilexml.model.CustomerCredit;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.WritableResource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
public class ItemObjectWritterConfig {

    @Bean
    @StepScope
    public StaxEventItemWriter<CustomerCredit> itemObjectWritter(@Value("#{jobParameters['output']}") WritableResource resource, XStreamMarshaller customerCreditMarshaller) {
        return new StaxEventItemWriterBuilder<CustomerCredit>()
                .name("itemObjectWritter")
                .resource(resource)
                .marshaller(customerCreditMarshaller)
                .rootTagName("customer")
                .overwriteOutput(true)
                .build();
    }
}

