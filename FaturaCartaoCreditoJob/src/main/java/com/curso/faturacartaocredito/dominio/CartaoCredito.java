package com.curso.faturacartaocredito.dominio;

public class CartaoCredito {

	private Cliente cliente;
	private Integer numeroCartaoCredito;

	public CartaoCredito() {
		super();
	}

	public CartaoCredito(Cliente cliente, Integer numeroCartaoCredito) {
		super();
		this.cliente = cliente;
		this.numeroCartaoCredito = numeroCartaoCredito;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getNumeroCartaoCredito() {
		return numeroCartaoCredito;
	}

	public void setNumeroCartaoCredito(Integer numeroCartaoCredito) {
		this.numeroCartaoCredito = numeroCartaoCredito;
	}

}
