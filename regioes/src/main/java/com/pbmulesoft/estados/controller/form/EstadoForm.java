package com.pbmulesoft.estados.controller.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pbmulesoft.estados.modelo.Estados;
import com.pbmulesoft.estados.modelo.NomeRegiao;


public class EstadoForm {
	
	@NotNull(message = "Nome nâo pode ser nulo!") @NotEmpty(message="nome não pode ser vazio!")
	private String nome;
	@NotNull(message="regiao nao pode ser nulo!")
	private NomeRegiao regiao;
	@NotNull(message="populacao nao pode ser nulo!") @Min(value = 1L,message = "valor deve ser maior que 1!")
	private long populacao;
	@NotNull(message = "capital nao pode ser nulo!") @NotEmpty(message="capital não pode ser vazio!")
	private String capital;
	@NotNull(message="area nao pode ser nulo!") @Min(value=1,message ="valor deve ser maior que 1!")
	private double area;
	
	
	public NomeRegiao getRegiao() {
		return regiao;
	}


	public void setRegiao(NomeRegiao regiao) {
		this.regiao = regiao;
	}
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public long getPopulacao() {
		return populacao;
	}


	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}


	public String getCapital() {
		return capital;
	}


	public void setCapital(String capital) {
		this.capital = capital;
	}


	public double getArea() {
		return area;
	}


	public void setArea(double area) {
		this.area = area;
	}


	public Estados converter() {

		return new Estados(nome,regiao,capital,populacao,area);
	}

	
	
	

}
