package com.pbmulesoft.estados.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pbmulesoft.estados.controller.form.AtualizarEstadoForm;
import com.pbmulesoft.estados.controller.form.EstadoForm;
import com.pbmulesoft.estados.dto.DetalheEstadosDto;
import com.pbmulesoft.estados.dto.EstadosDto;
import com.pbmulesoft.estados.modelo.Estados;
import com.pbmulesoft.estados.modelo.NomeRegiao;
import com.pbmulesoft.estados.repository.EstadosRepository;


@RestController
@RequestMapping("api/v1/estados")
@CrossOrigin
public class EstadosController {
		
	@Autowired
	private EstadosRepository estadoRepository;
	
	
	@GetMapping
	@ResponseBody
	public List<EstadosDto> listar(@RequestParam(required=false) NomeRegiao nomeRegiao,@PageableDefault(sort = {"populacao","area"},direction = Direction.DESC)Pageable page){

			if(nomeRegiao==null) {
				Page<Estados>  estados = estadoRepository.findAll(page);
				List <Estados> estadosLista= estados.getContent();
				return EstadosDto.converter(estadosLista);
			}
			else {
				List<Estados>estados =estadoRepository.findBynomeRegiao(nomeRegiao,page);
				return EstadosDto.converter(estados);

			}
		}

	

	@PostMapping
	@Transactional
	public ResponseEntity<EstadosDto> cadastra(@RequestBody @Valid EstadoForm formEstado,UriComponentsBuilder uriBuilder) {
		Estados estadoNovo = formEstado.converter();
		estadoRepository.save(estadoNovo);
		
		URI uri =uriBuilder.path("/estados/{id}").buildAndExpand(estadoNovo.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstadosDto(estadoNovo));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalheEstadosDto> detalhe(@PathVariable long id) {
		
		Optional<Estados> estado = estadoRepository.findById(id);
		if(estado.isPresent()) {
			return ResponseEntity.ok(new DetalheEstadosDto(estado.get()));
		}
		return ResponseEntity.notFound().build();

	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EstadosDto> cadastra(@PathVariable long id ,@RequestBody @Valid AtualizarEstadoForm formEstado) {
		Optional<Estados> opcao = estadoRepository.findById(id);
		
		if(opcao.isPresent()) {
			Estados estado = formEstado.atualizar(id,estadoRepository);
			
			return ResponseEntity.ok(new EstadosDto(estado));
		}
		return ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<EstadosDto> remover(@PathVariable long id){
		Optional<Estados> opcao = estadoRepository.findById(id);
		
		if(opcao.isPresent()) {
			estadoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	
		
	}
	

	
}
