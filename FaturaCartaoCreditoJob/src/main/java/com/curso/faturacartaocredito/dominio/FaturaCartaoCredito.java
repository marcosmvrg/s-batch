package com.curso.faturacartaocredito.dominio;

import java.util.ArrayList;
import java.util.List;

public class FaturaCartaoCredito {

	private Cliente cliente;
	private CartaoCredito cartaoCredito;
	private List<Transacao> transacoes;

	public FaturaCartaoCredito() {
		super();
		this.transacoes = new ArrayList<Transacao>();
	}

	public FaturaCartaoCredito(Cliente cliente, CartaoCredito cartaoCredito, List<Transacao> transacoes) {
		super();
		this.cliente = cliente;
		this.cartaoCredito = cartaoCredito;
		this.transacoes = transacoes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

}
