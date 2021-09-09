package net.ufjnet.gestaoobra.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ufjnet.gestaoobra.models.Proprietario;

public interface ProprietarioDAO extends JpaRepository<Proprietario, Integer>{
	public Optional<Proprietario> findByNome(String nome);
	public Optional<Proprietario> findByCpf(String cpf);
	public Optional<Proprietario> findByEmail(String email);
	
}
