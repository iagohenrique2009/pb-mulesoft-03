package com.pbmulesoft.estados.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbmulesoft.estados.config.security.TokenService;
import com.pbmulesoft.estados.controller.form.LoginForm;
import com.pbmulesoft.estados.dto.TokenDto;



@RestController
@RequestMapping("/api/v1/autenticacao")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication autenticacao = authenticationManager.authenticate(dadosLogin);
			String token= tokenService.gerarToken(autenticacao);
			return ResponseEntity.ok(new TokenDto(token,"Bearer"));
		}
		catch (AuthenticationException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
}
