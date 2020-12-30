package com.springbatch.skipexception.dominio;

public class Cliente {
	private String nome;
	private String sobrenome;
	private Integer idade;
	private String email;

	public Cliente() {
		super();
	}

	public Cliente(String nome, String sobrenome, Integer idade, String email) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.idade = idade;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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

	@Override
	public String toString() {
		return "Cliente{" + "nome='" + nome + "'" + ", sobrenome ='" + sobrenome + "'" + ", idade='" + idade + "'"
				+ ", email='" + email + "'" + '}';
	}
}