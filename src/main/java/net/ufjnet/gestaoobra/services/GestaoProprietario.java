package net.ufjnet.gestaoobra.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<ProprietarioDTO> findAll(Pageable pageable) {
		Page<Proprietario> result = dao.findAll(pageable);
		return result.map(obj -> new ProprietarioDTO(obj));
	}
	

	@Transactional(readOnly = true)
	public ProprietarioDTO findById(Integer id) {
		Proprietario result = dao.findById(id).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		return new ProprietarioDTO(result);	
	}
	
	@Transactional(readOnly = true)
	public ProprietarioDTO findByNome(String nome) {
		Proprietario result = dao.findByNome(nome).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		return new ProprietarioDTO(result);
    	}
	
	@Transactional(readOnly = true)
	public ProprietarioDTO findByCPF(String cpf) {
		Proprietario result = dao.findByCpf(cpf).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		return new ProprietarioDTO(result);
    }
	
	@Transactional(readOnly = true)
	public ProprietarioDTO findByEmail(String email) {
		Proprietario result = dao.findByEmail(email).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		return new ProprietarioDTO(result);
	}
	
	@Transactional
	public ProprietarioDTO update(ProprietarioDTO obj) {
		Proprietario entity = dao.findById(obj.getCodigo())
				.orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		entity.setNome(obj.getNome());
		entity.setCpf(obj.getCpf());
		entity.setEmail(obj.getEmail());
		return new ProprietarioDTO(dao.save(entity));
	}
	
	@Transactional
	public ProprietarioDTO save(ProprietarioDTO objBody) {
		Proprietario entity = new Proprietario(objBody.getCodigo(), objBody.getNome(), objBody.getCpf(), objBody.getEmail());
		
		boolean cpfExists = dao.findByCpf(objBody.getCpf()).stream()
				.anyMatch(objResult -> !objResult.equals(objBody));
		if(cpfExists) {
			throw new BusinessException("CPF já cadastrado!");
		}
		boolean emailExists = dao.findByEmail(objBody.getEmail()).stream()
				.anyMatch(objResult -> !objResult.equals(objBody));
		if(emailExists) {
			throw new BusinessException("E-mail já cadastrado!");
		}
		
		return new ProprietarioDTO(dao.save(entity));
	}
	
	@Transactional
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
}
