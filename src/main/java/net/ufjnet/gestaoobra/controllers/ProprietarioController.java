package net.ufjnet.gestaoobra.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.ufjnet.gestaoobra.dtos.ProprietarioDTO;
import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.services.GestaoProprietario;

@RestController
@RequestMapping("/v1/gto/proprietarios")
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
		ProprietarioDTO objDTO = service.findById(id);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarUm(id)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<ProprietarioDTO> buscarNome(@PathVariable String nome) {
		ProprietarioDTO objDTO = service.findByNome(nome);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarNome(nome)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<ProprietarioDTO> buscarCpf(@PathVariable String cpf) {
		ProprietarioDTO objDTO = service.findByCPF(cpf);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarCpf(cpf)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<ProprietarioDTO> buscarEmail(@PathVariable String email) {
		ProprietarioDTO objDTO = service.findByEmail(email);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarEmail(email)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProprietarioDTO> incluir(@RequestBody Proprietario objBody) {
		ProprietarioDTO objDTO = service.save(objBody);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@PutMapping
	public ResponseEntity<ProprietarioDTO> atualizar(@PathVariable Integer id, @RequestBody Proprietario objBody) {
		if (!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		objBody.setCodigo(id);
		ProprietarioDTO objDTO = service.save(objBody);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}		

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		if (!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
