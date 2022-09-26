package com.pbmulesoft.estados.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.pbmulesoft.estados.modelo.Estados;
import com.pbmulesoft.estados.modelo.NomeRegiao;


public class EstadosDto {
	
	
	private long id;
	private String nome;
	private NomeRegiao regiao;
	private long populacao;
	private String capital;
	private double area;
	
	public EstadosDto(Estados estado) {
		
		this.id=estado.getId();
		this.nome=estado.getNome();
		this.regiao=estado.getNomeRegiao();
		this.populacao=estado.getPopulacao();
		this.capital=estado.getCapital();
		this.area=estado.getArea();
		
	}


	public String getNome() {
		return nome;
	}


	public long getId() {
		return id;
	}


	public NomeRegiao getRegiao() {
		return regiao;
	}


	public long getPopulacao() {
		return populacao;
	}


	public String getCapital() {
		return capital;
	}


	public double getArea() {
		return area;
	}


	public static List<EstadosDto> converter(List<Estados> estados) {
		
		return estados.stream().map(EstadosDto::new).collect(Collectors.toList());
	}

	
}
