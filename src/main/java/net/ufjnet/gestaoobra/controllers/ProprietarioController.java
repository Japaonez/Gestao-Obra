package net.ufjnet.gestaoobra.controllers;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.ufjnet.gestaoobra.dtos.ProprietarioDTO;
import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.services.GestaoProprietario;

@RestController
@RequestMapping("/gto/proprietarios")
public class ProprietarioController {

	@Autowired //instancia o objeto
	private GestaoProprietario service;
//	private ProprietarioDAO propDAO;
	
//	@GetMapping
//	public ResponseEntity<Page<ProprietarioDTO>> buscarTodos(Pageable pageable) {
//		Page<ProprietarioDTO> result = service.findAll(pageable);
//		return ResponseEntity.ok(result);
//	}
	
	@GetMapping
	public ResponseEntity<CollectionModel<ProprietarioDTO>> buscarTodos(
		@RequestParam(value="page", defaultValue = "0") int page,
		@RequestParam(value="limit", defaultValue = "12") int limit,
		@RequestParam(value="direction", defaultValue = "asc") String direction) {
	
	
		Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
				
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
				
		Page<ProprietarioDTO> pages = service.findAll(pageable);
		pages
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(ProprietarioController.class).buscarUm(p.getCodigo())).withSelfRel()
				)
			);
			  	
		return ResponseEntity.ok(CollectionModel.of(pages));
	}

	
//	@GetMapping("/{id}")
//	public Proprietario buscarUm(@PathVariable Integer id) {
//		Optional<Proprietario> obj = propDAO.findById(id);
//		return obj.orElse(null);
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProprietarioDTO> buscarUm(@PathVariable Integer id) {
//		Optional<Proprietario> objOpt = service.findById(id);
//		Proprietario obj = objOpt.orElse(null);
//		return ResponseEntity.ok(obj);
		return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<ProprietarioDTO> buscarNome(@PathVariable String nome) {
		return service.findByName(nome).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<ProprietarioDTO> buscarCpf(@PathVariable String cpf) {
		return service.findByCpf(cpf).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<ProprietarioDTO> incluir(@Valid @RequestBody Proprietario obj){
		ProprietarioDTO objDTO = service.save(obj);
		return ResponseEntity.created(null).body(objDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProprietarioDTO> atualizar(@PathVariable Integer id, @RequestBody Proprietario obj){
		if(!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		obj.setCodigo(id);
		ProprietarioDTO objDTO = service.save(obj);
		return ResponseEntity.ok(objDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Proprietario> excluir(@PathVariable Integer id){
		if(!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
