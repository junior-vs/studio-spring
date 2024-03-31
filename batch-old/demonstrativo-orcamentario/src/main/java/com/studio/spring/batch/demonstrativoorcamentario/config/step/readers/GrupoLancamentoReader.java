package com.studio.spring.batch.demonstrativoorcamentario.config.step.readers;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.studio.spring.batch.demonstrativoorcamentario.dominio.GrupoLancamento;

@Component
public class GrupoLancamentoReader implements ResourceAwareItemReaderItemStream<GrupoLancamento> {

    private FlatFileItemReader<GrupoLancamento> delegate;
    private GrupoLancamento lancamentoAtual;

    public GrupoLancamentoReader(FlatFileItemReader<GrupoLancamento> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        this.delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        this.delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        this.delegate.close();
    }

    @Override
    public void setResource(Resource resource) {
        this.delegate.setResource(resource);
    }

    /**
     * Reads the next group of lancamentos.
     *
     * This method reads the next group of lancamentos from the delegate
     * reader and checks if each lancamento in the group is related to the
     * previous group. If a lancamento is related, it is added to the group.
     * Once a lancamento is not related, the group of lancamentos is returned.
     *
     * @return The next group of lancamentos.
     * @throws Exception If there is an error reading the lancamentos.
     */
    @Override
    public GrupoLancamento read() throws Exception {
        // If there is no current lancamento, read the next one from the delegate
        if (lancamentoAtual == null) {
            lancamentoAtual = delegate.read();
        }

        // The group of lancamentos to be returned
        GrupoLancamento grupoLancamento = lancamentoAtual;

        // Reset the current lancamento
        lancamentoAtual = null;

        // If there is a group of lancamentos
        if (grupoLancamento != null) {
            // Get the next lancamento in the group
            GrupoLancamento proxLancamento = peek();

            // While the next lancamento is related to the group
            while (isLancamentoRelacionado(grupoLancamento, proxLancamento)) {
                // Add the next lancamento to the group
                grupoLancamento.getLancamentos().add(lancamentoAtual.getLancamentoTmp());

                // Get the next lancamento in the group
                proxLancamento = peek();
            }

            // Add the current lancamento to the group
            grupoLancamento.getLancamentos().add(grupoLancamento.getLancamentoTmp());
        }

        // Return the group of lancamentos
        return grupoLancamento;
    }

    /**
     * Checks if a lancamento is related to a given group of lancamentos.
     * Two lancamentos are considered related if they have the same nature
     * of expense.
     *
     * @param grupoLancamento The group of lancamentos to compare.
     * @param proxLancamento  The lancamento to compare.
     * @return True if the lancamento is related to the group, false
     *         otherwise.
     */
    private boolean isLancamentoRelacionado(GrupoLancamento grupoLancamento, GrupoLancamento proxLancamento) {
        /*
         * If the next lancamento is not null and its nature of expense is
         * equal to the group's nature of expense, then it is related.
         */
        return proxLancamento != null
                && proxLancamento.getCodigoNaturezaDespesa().equals(grupoLancamento.getCodigoNaturezaDespesa());
    }

    /**
     * Retrieves the next lancamento in the reader without removing it from the
     * reader.
     *
     * @return The next lancamento to be read, or null if there are no more
     *         lancamentos to be read.
     * @throws Exception If there is an error retrieving the next lancamento.
     */
    public GrupoLancamento peek() throws Exception {
        // Read the next lancamento without removing it from the delegate reader
        lancamentoAtual = delegate.read();
        // Return the next lancamento
        return lancamentoAtual;
    }

}
