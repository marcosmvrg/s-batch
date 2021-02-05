package com.curso.faturacartaocredito.dominio;

import java.util.Date;

public class Transacao {

	private Integer id;
	private CartaoCredito cartaoCredito;
	private String descricao;
	private Double valor;
	private Date dtTransacao;

	public Transacao() {
		super();
	}

	public Transacao(Integer id, CartaoCredito cartaoCredito, String descricao, Double valor, Date dtTransacao) {
		super();
		this.id = id;
		this.cartaoCredito = cartaoCredito;
		this.descricao = descricao;
		this.valor = valor;
		this.dtTransacao = dtTransacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDtTransacao() {
		return dtTransacao;
	}

	public void setDtTransacao(Date dtTransacao) {
		this.dtTransacao = dtTransacao;
	}

}
