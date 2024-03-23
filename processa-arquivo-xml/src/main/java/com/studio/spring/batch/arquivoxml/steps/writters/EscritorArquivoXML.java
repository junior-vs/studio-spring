package com.studio.spring.batch.arquivoxml.steps.writters;

import com.studio.spring.batch.arquivoxml.model.Trade;
import com.thoughtworks.xstream.security.ExplicitTypePermission;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.WritableResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class EscritorArquivoXML {

    @Bean
    @StepScope
    public StaxEventItemWriter<Trade> itemXMLWriter(@Value("#{jobParameters['destinoFolder']}") WritableResource resource) {
        return new StaxEventItemWriterBuilder<Trade>()
                .name("itemXMLWriter")
                .resource(resource)
                .marshaller(tradeMarshaller())
                .rootTagName("trade")
                .overwriteOutput(true)
                .build();
    }

    private XStreamMarshaller tradeMarshaller() {
        XStreamMarshaller marshaller = new XStreamMarshaller();
        Map<String, Class> aliases = new HashMap<>();
        aliases.put("trade", Trade.class);
        aliases.put("price", BigDecimal.class);
        aliases.put("isin", String.class);
        aliases.put("customer", String.class);
        aliases.put("quantity", Long.class);

        marshaller.setAliases(aliases);
        marshaller.setTypePermissions(new ExplicitTypePermission(new Class[]{Trade.class}));
        return marshaller;
    }

}
