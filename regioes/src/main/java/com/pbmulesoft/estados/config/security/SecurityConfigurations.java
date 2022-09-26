package com.pbmulesoft.estados.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pbmulesoft.estados.repository.UsuarioRepository;


@EnableWebSecurity
public class SecurityConfigurations {
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
				
	    http.authorizeRequests()
	    .antMatchers(HttpMethod.GET, "/api/v1/estados").permitAll()
	    .antMatchers(HttpMethod.GET, "/api/v1/estados/*").permitAll()
	    .antMatchers(HttpMethod.POST, "/api/v1/autenticacao").permitAll()
	    .anyRequest().authenticated()
	    .and().cors()
	    .and().csrf().disable()
	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	


}
