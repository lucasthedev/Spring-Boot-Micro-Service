package com.projuris.webservice.demo;

public class ErroDTO {

	private String erro;
	private String campo;

	public ErroDTO(String erro, String campo) {
		super();
		this.erro = erro;
		this.campo = campo;
	}
	
	public String getErro() {
		return erro;
	}

	public String getCampo() {
		return campo;
	}

}
