package com.pbmulesoft.estados.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pbmulesoft.estados.controller.form.AtualizarUsuarioForm;
import com.pbmulesoft.estados.controller.form.UsuarioForm;
import com.pbmulesoft.estados.dto.UsuarioDto;
import com.pbmulesoft.estados.modelo.Usuario;
import com.pbmulesoft.estados.repository.UsuarioRepository;


@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@GetMapping
	public List<UsuarioDto> lista(){
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		return UsuarioDto.converter(usuarios);
		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm formUsuario,UriComponentsBuilder uriBuilder){
		Usuario usuarioNovo = formUsuario.converter();
		usuarioNovo.setSenha(passwordEncoder.encode(usuarioNovo.getSenha()));
		usuarioRepository.save(usuarioNovo);
		
		URI uri =uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioNovo.getId()).toUri();
		return ResponseEntity.created(uri).build();		
		
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id,@RequestBody @Valid AtualizarUsuarioForm atualizarformUsuario){
		Optional<Usuario> opcao = usuarioRepository.findById(id);
		
		if(opcao.isPresent()) {
			Usuario usuario = atualizarformUsuario.atualizar(id, usuarioRepository);
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	

}
