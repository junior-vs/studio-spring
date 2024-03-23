package com.studio.spring.batch.arquivoxml.steps.readers;


import com.studio.spring.batch.arquivoxml.model.Trade;
import com.thoughtworks.xstream.security.ExplicitTypePermission;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class LeitorArquivoXML {

    @Bean
    @StepScope
    public StaxEventItemReader itemXMLReader(@Value("#{jobParameters['arquivoXML']}") Resource resource){
        return new StaxEventItemReaderBuilder<Trade>()
        .name("itemXMLReader")
        .resource(resource)
        .addFragmentRootElements("trade")
        .unmarshaller(tradeUnMarshaller())
        .build();
    }

    private XStreamMarshaller tradeUnMarshaller() {

        Map<String, Class> aliases = new HashMap<>();
        aliases.put("trade", Trade.class);
        aliases.put("price", BigDecimal.class);
        aliases.put("isin", String.class);
        aliases.put("customer", String.class);
        aliases.put("quantity", Long.class);

        
        XStreamMarshaller unMarshaller = new XStreamMarshaller();
        unMarshaller.setAliases(aliases);
        unMarshaller.setTypePermissions(new ExplicitTypePermission(new Class[]{Trade.class}));
        return unMarshaller;        
    }
    
}
