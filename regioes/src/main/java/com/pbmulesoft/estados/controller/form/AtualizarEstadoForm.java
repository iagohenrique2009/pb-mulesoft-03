package com.pbmulesoft.estados.controller.form;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pbmulesoft.estados.modelo.Estados;
import com.pbmulesoft.estados.modelo.NomeRegiao;
import com.pbmulesoft.estados.repository.EstadosRepository;

public class AtualizarEstadoForm {
	
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
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public NomeRegiao getRegiao() {
		return regiao;
	}
	
	public void setRegiao(NomeRegiao regiao) {
		this.regiao = regiao;
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

	public Estados atualizar(long id, @Valid EstadosRepository estadoRepository) {
		Estados estado = estadoRepository.getReferenceById(id);
		estado.setNome(this.nome);
		estado.setNomeRegiao(this.regiao);
		estado.setPopulacao(this.populacao);
		estado.setCapital(this.capital);
		estado.setArea(this.area);
		return estado;
	}

}
