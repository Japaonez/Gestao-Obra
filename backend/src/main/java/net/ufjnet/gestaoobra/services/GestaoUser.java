package net.ufjnet.gestaoobra.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.ufjnet.gestaoobra.models.User;
import net.ufjnet.gestaoobra.repositories.UserDAO;

@AllArgsConstructor
@Service
public class GestaoUser implements UserDetailsService{
	
	private UserDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = dao.findByUsername(username);
		if(user != null) {
			return user;
		}else {
			throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
		}
	}
}
