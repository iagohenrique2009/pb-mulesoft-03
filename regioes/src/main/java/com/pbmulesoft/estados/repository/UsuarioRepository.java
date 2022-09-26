package com.pbmulesoft.estados.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pbmulesoft.estados.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);
	
	List<Usuario> findAll();


}
