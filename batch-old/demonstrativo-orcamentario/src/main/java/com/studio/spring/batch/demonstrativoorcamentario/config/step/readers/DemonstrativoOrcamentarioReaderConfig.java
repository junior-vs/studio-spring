package com.studio.spring.batch.demonstrativoorcamentario.config.step.readers;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.studio.spring.batch.demonstrativoorcamentario.dominio.GrupoLancamento;

@Configuration
public class DemonstrativoOrcamentarioReaderConfig {

    /**
     * Configuration for the Demonstrativo Orcamentario multi-resource reader step.
     * This bean creates a {@link MultiResourceItemReader} that reads
     * {@link GrupoLancamento} objects from multiple resources.
     *
     * @param resources             the resources to read from
     * @param grupoLancamentoReader the reader to use for each resource
     * @return the configured multi-resource reader
     */
    @StepScope
    @Bean
    MultiResourceItemReader<GrupoLancamento> demonstrativoOrcamentarioReader(
            @Value("#{jobParameters['arquivos.path']}") Resource[] resources,
            GrupoLancamentoReader grupoLancamentoReader) {
        // Create the multi-resource reader
        return new MultiResourceItemReaderBuilder<GrupoLancamento>()
                // Set the name of the reader
                .name("demonstrativoOrcamentarioReader")
                // Set the resources to read from
                .resources(resources)
                // Set the reader to use for each resource
                .delegate(grupoLancamentoReader)
                // Build and return the reader
                .build();
    }

}
