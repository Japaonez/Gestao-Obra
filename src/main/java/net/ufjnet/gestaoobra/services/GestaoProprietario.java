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

	@Transactional(readOnly = true)
	public List<ProprietarioDTO> findAll() {
		List<Proprietario> result = dao.findAll();
		return result.stream().map(obj -> new ProprietarioDTO(obj)).collect(Collectors.toList());
//		return dao.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<ProprietarioDTO> findById(Integer id) {
		Optional<Proprietario> result = dao.findById(id);
		return result.map(obj -> new ProprietarioDTO(obj));
//		return dao.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<ProprietarioDTO> findByName(String nome) {
		Optional<Proprietario> result = dao.findByNome(nome);
		return result.map(obj -> new ProprietarioDTO(obj));
//		return dao.findByNome(nome);
	}
	
	@Transactional(readOnly = true)
	public Optional<ProprietarioDTO> findByCpf(String cpf) {
		Optional<Proprietario> result = dao.findByCpf(cpf);
		return result.map(obj -> new ProprietarioDTO(obj));
//		return dao.findByCpf(cpf);
	}
	
	@Transactional(readOnly = true)
	public Optional<ProprietarioDTO> findByEmail(String email) {
		Optional<Proprietario> result = dao.findByEmail(email);
		return result.map(obj -> new ProprietarioDTO(obj));
//		return dao.findByEmail(email);
	}
	
	@Transactional
	public ProprietarioDTO save(Proprietario obj) {
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
		
		return new ProprietarioDTO(dao.save(obj));
	}
	
	@Transactional
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
}
