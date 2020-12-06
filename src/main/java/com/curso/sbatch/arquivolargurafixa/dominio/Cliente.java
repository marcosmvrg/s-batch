package com.curso.sbatch.arquivolargurafixa.dominio;

public class Cliente {

	private String nome;
	private String sobreNome;
	private Integer idade;
	private String email;

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
		builder.append("]");
		return builder.toString();
	}
	
	

}
