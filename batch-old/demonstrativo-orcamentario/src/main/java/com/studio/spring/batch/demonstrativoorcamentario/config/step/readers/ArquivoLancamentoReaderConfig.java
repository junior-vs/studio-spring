package com.studio.spring.batch.demonstrativoorcamentario.config.step.readers;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindException;

import com.studio.spring.batch.demonstrativoorcamentario.dominio.GrupoLancamento;
import com.studio.spring.batch.demonstrativoorcamentario.dominio.Lancamento;

@Configuration
public class ArquivoLancamentoReaderConfig {

    /**
     * This method creates a FlatFileItemReader bean for reading arquivoLancamento
     * file.
     * It uses FlatFileItemReaderBuilder to build the reader and specify the
     * names of the columns in the file. The reader also uses the
     * grupoLancamentoFieldSetMapper to map the data from the file to
     * GrupoLancamento object.
     *
     * @return FlatFileItemReader bean for reading arquivoLancamento file
     */
    @Bean
    FlatFileItemReader<GrupoLancamento> arquivoLancamentoReader() {
        // Use FlatFileItemReaderBuilder to build the reader
        return new FlatFileItemReaderBuilder<GrupoLancamento>()
                // Name the reader
                .name("arquivoLancamentoReader")
                // The file is delimited
                .delimited()
                // Specify the names of the columns in the file
                .names("codigoNaturezaDespesa",
                        "descricaoNaturezaDespesa",
                        "descricaoLancamento",
                        "dataLancamento",
                        "valorLancamento")
                // Map the data from the file to GrupoLancamento object
                .fieldSetMapper(grupoLancamentoFieldSetMapper())
                .build();
    }

    private FieldSetMapper<GrupoLancamento> grupoLancamentoFieldSetMapper() {
        return new FieldSetMapper<GrupoLancamento>() {

            @Override
            public GrupoLancamento mapFieldSet(FieldSet fieldSet) throws BindException {
                GrupoLancamento grupo = new GrupoLancamento();
                grupo.setCodigoNaturezaDespesa(fieldSet.readInt("codigoNaturezaDespesa"));
                grupo.setDescricaoNaturezaDespesa(fieldSet.readString("descricaoNaturezaDespesa"));
                grupo.setLancamentoTmp(new Lancamento());
                grupo.getLancamentoTmp().setData(fieldSet.readDate("dataLancamento"));
                grupo.getLancamentoTmp().setDescricao(fieldSet.readString("descricaoLancamento"));
                grupo.getLancamentoTmp().setValor(fieldSet.readDouble("valorLancamento"));
                return grupo;
            }

        };
    }

}
