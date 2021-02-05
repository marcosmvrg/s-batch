package com.curso.faturacartaocredito.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;

import com.curso.faturacartaocredito.dominio.FaturaCartaoCredito;
import com.curso.faturacartaocredito.dominio.Transacao;

public class FaturaCartaoCreditoReader implements ItemStreamReader<FaturaCartaoCredito>{

	private ItemStreamReader<Transacao> delegate;
	private Transacao transacaoAtual;
	
	public FaturaCartaoCreditoReader() {
		super();
	}
	
	public FaturaCartaoCreditoReader(ItemStreamReader<Transacao> delegate) {
		super();
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
	public FaturaCartaoCredito read() throws Exception {
		if(transacaoAtual == null) {
			transacaoAtual = delegate.read();
		}
		
		FaturaCartaoCredito fatura = null;
		Transacao transacao = transacaoAtual;
		transacaoAtual = null;
		
		if(transacao != null) {
			fatura = new FaturaCartaoCredito();
			fatura.setCartaoCredito(transacao.getCartaoCredito());
			fatura.setCliente(transacao.getCartaoCredito().getCliente());
			fatura.getTransacoes().add(transacao);
			
			while(isTransacaoRelacionada(transacao)) {
				fatura.getTransacoes().add(transacaoAtual);
			}
		}
		
		return fatura;
		
	}

	private boolean isTransacaoRelacionada(Transacao transacao) throws Exception {
		return peek() != null && transacao.getCartaoCredito().getNumeroCartaoCredito()
				.equals(transacaoAtual.getCartaoCredito().getNumeroCartaoCredito());
	}

	private Transacao peek() throws Exception {
		transacaoAtual = delegate.read();
		return transacaoAtual;
	}

	
	
}
