package net.ufjnet.gestaoobra.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.ufjnet.gestaoobra.dtos.ProprietarioDTO;
import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.repositories.ProprietarioDAO;
import net.ufjnet.gestaoobra.services.exceptions.BusinessException;

@AllArgsConstructor
@Service
public class GestaoProprietario {
	
	private ProprietarioDAO dao;

	public List<ProprietarioDTO> findAll() {
		List<Proprietario> result = dao.findAll();
		return result.stream().map(obj -> new ProprietarioDTO(obj)).collect(Collectors.toList());
//		return dao.findAll();
	}
	
	public Optional<Proprietario> findById(Integer id) {
		return dao.findById(id);
	}
	
	public Optional<Proprietario> findByName(String nome) {
		return dao.findByNome(nome);
	}
	
	public Optional<Proprietario> findByCpf(String nome) {
		return dao.findByCpf(nome);
	}
	
	public Optional<Proprietario> findByEmail(String email) {
		return dao.findByEmail(email);
	}
	
	@Transactional
	public Proprietario save(Proprietario obj) {
		boolean cpfExists = dao.findByCpf(obj.getCpf()).stream()
				.anyMatch(objResult -> !objResult.equals(obj));
		if(cpfExists) {
			throw new BusinessException("CPF já cadastrado!");
		}
		boolean emailExists = dao.findByEmail(obj.getEmail()).stream()
				.anyMatch(objResult -> !objResult.equals(obj));
		if(emailExists) {
			throw new BusinessException("E-mail já cadastrado!");
		}
		return dao.save(obj);
	}
	
	@Transactional
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
}
