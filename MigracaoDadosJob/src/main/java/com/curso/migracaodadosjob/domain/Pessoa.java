package com.curso.migracaodadosjob.domain;

import java.util.Date;

import org.apache.logging.log4j.util.Strings;

public class Pessoa {

	private Integer id;
	private String nome;
	private String email;
	private Date dtNascimento;
	private Integer idade;

	public Pessoa() {
		super();
	}

	public Pessoa(Integer id, String nome, String email, Date dtNascimento, Integer idade) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dtNascimento = dtNascimento;
		this.idade = idade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public boolean isValida() {
		return Strings.isNotBlank(getNome()) && Strings.isNotBlank(getEmail()) && getDtNascimento() != null;
	}

}
