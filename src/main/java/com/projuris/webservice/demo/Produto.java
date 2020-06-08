package com.projuris.webservice.demo;

public class Produto {
	
	private long id;
	private String nome;
	
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
