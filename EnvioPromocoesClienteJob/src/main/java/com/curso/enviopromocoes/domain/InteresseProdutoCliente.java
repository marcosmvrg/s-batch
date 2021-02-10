package com.curso.enviopromocoes.domain;

public class InteresseProdutoCliente {

	private Cliente cliente;
	private Produto produto;

	public InteresseProdutoCliente() {
		super();
	}

	public InteresseProdutoCliente(Cliente cliente, Produto produto) {
		super();
		this.cliente = cliente;
		this.produto = produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
