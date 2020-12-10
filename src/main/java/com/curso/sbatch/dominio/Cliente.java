package com.curso.sbatch.dominio;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private String nome;
	private String sobreNome;
	private Integer idade;
	private String email;
	private List<Transacao> transacoes = new ArrayList<Transacao>();

	public Cliente() {
		super();		
	}

	public Cliente(String nome, String sobreNome, Integer idade, String email) {
		super();
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.idade = idade;
		this.email = email;		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}
	
	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cliente [nome=");
		builder.append(nome);
		builder.append(", sobreNome=");
		builder.append(sobreNome);
		builder.append(", idade=");
		builder.append(idade);
		builder.append(", email=");
		builder.append(email);
		builder.append(transacoes.isEmpty() ? "": ", transacoes="+transacoes);
		builder.append("]");
		return builder.toString();
	}
	
	

}
