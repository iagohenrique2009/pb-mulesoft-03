package com.pbmulesoft.estados.modelo;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Estados {
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;

	@Enumerated(EnumType.STRING)
	private NomeRegiao nomeRegiao;
	private String capital;
	private long populacao;
	private double area;
	
	
	public Estados() {
		
	}
	
	public Estados(String nome,NomeRegiao nomeRegiao, String capital, long populacao, double area) {
		this.nome=nome;
		this.nomeRegiao = nomeRegiao;
		this.capital = capital;
		this.populacao = populacao;
		this.area = area;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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

	public NomeRegiao getNomeRegiao() {
		return nomeRegiao;
	}

	public void setNomeRegiao(NomeRegiao nomeRegiao) {
		this.nomeRegiao = nomeRegiao;
	}

	

}
