package com.projuris.webservice.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull @NotEmpty @Length(min = 3)
	private String nome;

	public Produto() {
	}

	public Produto(long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

}
