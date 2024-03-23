package com.studio.spring.multiplosdominio.step.reader;


import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;

import com.studio.spring.multiplosdominio.model.Cliente;
import com.studio.spring.multiplosdominio.model.Transacao;

public class ArquivoClienteTransacaoReader implements ItemStreamReader<Cliente> {
  private Object objAtual;
  private ItemStreamReader<Object> delegate;


  public ArquivoClienteTransacaoReader(ItemStreamReader<Object> delegate) {
    this.delegate = delegate;
  }

  @Override
  public void open(ExecutionContext executionContext) throws ItemStreamException {
    delegate.open(executionContext);
  }

  @Override
  public void update(ExecutionContext executionContext) throws ItemStreamException {
    delegate.update(executionContext);
  }

  @Override
  public void close() throws ItemStreamException {
    delegate.close();
  }

  /**
   * Reads a Cliente object from the delegate reader.
   * If the current object is null, it reads the next object from the delegate.
   * If the current object is not null, it adds all Transacao objects to the Cliente's transacoes list.
   *
   * @return The read Cliente object, or null if there are no more objects to read.
   * @throws Exception If an error occurs while reading.
   */
  @Override
  public Cliente read() throws Exception {
    // If the current object is null, read the next object from the delegate
    if (objAtual == null)
      objAtual = delegate.read();

    // Cast the current object to a Cliente
    Cliente cliente = (Cliente) objAtual;
    objAtual = null;

    // If the cliente is not null, add all Transacao objects to its transacoes list
    if (cliente != null) {
      while(peek() instanceof Transacao)
        cliente.getTransacoes().add((Transacao) objAtual);
    }

    // Return the read Cliente object
    return cliente;
  }

  private Object peek() throws Exception {
    objAtual = delegate.read();
    return objAtual;
  }
  
}