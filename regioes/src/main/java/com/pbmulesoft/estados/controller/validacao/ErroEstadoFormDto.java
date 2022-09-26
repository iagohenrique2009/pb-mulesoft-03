package com.pbmulesoft.estados.controller.validacao;

public class ErroEstadoFormDto {
	
	private String campo;
	private String erro;
	
	
	
	public ErroEstadoFormDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}
	
	public String getErro() {
		return erro;
	}
	
	public void setErro(String erro) {
		this.erro = erro;
	}
	
	public String getCampo() {
		return campo;
	}
	
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	
	
	
}
