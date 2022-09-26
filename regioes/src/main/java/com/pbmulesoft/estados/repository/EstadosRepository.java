package com.pbmulesoft.estados.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pbmulesoft.estados.controller.form.EstadoForm;
import com.pbmulesoft.estados.modelo.Estados;
import com.pbmulesoft.estados.modelo.NomeRegiao;

public interface EstadosRepository extends JpaRepository<Estados, Long> {
	
	Page<Estados> findAll(Pageable page);
	
	List<Estados> findBynomeRegiao(NomeRegiao nomeRegiao, Pageable page);

	void save(EstadoForm formEstado);
	
}
