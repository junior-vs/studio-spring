package com.studio.spring.batch.samples.samplefilexml.config;

import com.studio.spring.batch.samples.samplefilexml.model.CustomerCredit;
import com.thoughtworks.xstream.security.ExplicitTypePermission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.math.BigDecimal;
import java.util.Map;

@Configuration
public class CustomerCreditMarshallerConfig {

    @Bean
    public XStreamMarshaller customerCreditMarshaller() {
        XStreamMarshaller marshaller = new XStreamMarshaller();
        marshaller
                .setAliases(Map.of("customer", CustomerCredit.class, "credit", BigDecimal.class, "name", String.class));
        marshaller.setTypePermissions(new ExplicitTypePermission(new Class[] { CustomerCredit.class }));
        return marshaller;
    }
}
