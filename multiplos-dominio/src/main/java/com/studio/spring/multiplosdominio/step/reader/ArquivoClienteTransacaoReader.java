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

  @Override
  public Cliente read() throws Exception {
    if (objAtual == null)
      objAtual = delegate.read();

    Cliente cliente = (Cliente) objAtual;
    objAtual = null;

    if (cliente != null) {
      while(peek() instanceof Transacao)
        cliente.getTransacoes().add((Transacao) objAtual);
    }
    return cliente;
  }

  private Object peek() throws Exception {
    objAtual = delegate.read();
    return objAtual;
  }
  
}