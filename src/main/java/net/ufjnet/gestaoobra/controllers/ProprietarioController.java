package net.ufjnet.gestaoobra.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.services.GestaoProprietario;

@RestController
@RequestMapping("/gto/proprietarios")
public class ProprietarioController {

	@Autowired //instancia o objeto
	private GestaoProprietario service;
//	private ProprietarioDAO propDAO;
	
	@GetMapping
	public List<Proprietario> buscarTodos() {
		return service.findAll();
//		return "primeiro endpoint de Gestão de Obras";
	}
	
//	@GetMapping("/{id}")
//	public Proprietario buscarUm(@PathVariable Integer id) {
//		Optional<Proprietario> obj = propDAO.findById(id);
//		return obj.orElse(null);
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Proprietario> buscarUm(@PathVariable Integer id) {
//		Optional<Proprietario> objOpt = service.findById(id);
//		Proprietario obj = objOpt.orElse(null);
//		return ResponseEntity.ok(obj);
		return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Proprietario> incluir(@RequestBody Proprietario obj){
		obj = service.save(obj);
		return ResponseEntity.created(null).body(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Proprietario> atualizar(@PathVariable Integer id, @RequestBody Proprietario obj){
		if(!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		obj.setCodigo(id);
		obj = service.save(obj);
		return ResponseEntity.ok(obj);
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
