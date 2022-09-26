package com.pbmulesoft.estados.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pbmulesoft.estados.modelo.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${regioes.jwt.expiration}")
	private String expiration;
	
	@Value("${regioes.jwt.expiration}")
	private String secret;
	
	
	public String gerarToken(Authentication autenticacao) {
		
		Usuario logado =(Usuario) autenticacao.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime()+Long.parseLong(expiration));
		
		return Jwts.builder()
			.setIssuer("API avaliacao da sprint 3 ^^")
			.setSubject(logado.getId().toString())
			.setIssuedAt(hoje)
			.setExpiration(dataExpiracao)
			.signWith(SignatureAlgorithm.HS256, secret)
			.compact();
	}


	public boolean isValido(String token) {
		try {
			Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token);
			return true;
			
		}catch (Exception e) {
			return false;
		}
		
	}


	public Long getIdUsuario(String token) {
		Claims claims= Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		
		return Long.parseLong(claims.getSubject());
	}
	
	

}
