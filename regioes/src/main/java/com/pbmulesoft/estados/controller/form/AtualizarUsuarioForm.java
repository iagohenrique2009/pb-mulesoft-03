package com.pbmulesoft.estados.controller.form;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pbmulesoft.estados.modelo.Usuario;
import com.pbmulesoft.estados.repository.UsuarioRepository;

public class AtualizarUsuarioForm {
	
	@NotNull(message = "Nome nâo pode ser nulo!") @NotEmpty(message="nome não pode ser vazio!")
	private String nome;
	@NotNull(message = "email nâo pode ser nulo!") @NotEmpty(message="email não pode ser vazio!")
	private String email;
	@NotNull(message = "senha nâo pode ser nulo!") @NotEmpty(message="senha não pode ser vazio!")
	private String senha;
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Usuario atualizar(Long id,@Valid UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getReferenceById(id);
		usuario.setNome(this.nome);
		usuario.setEmail(this.email);
		usuario.setSenha(this.senha);
		return usuario;
	}

}
