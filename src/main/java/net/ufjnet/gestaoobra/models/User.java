package net.ufjnet.gestaoobra.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table
public class User implements UserDetails, Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(unique = true)
	private String username;
	
	private String fullName;
	
	private String password;
	
	private Boolean AccountNonExpired;
	
	private Boolean AccountNonLocked;
	
	private Boolean CredentialsNonExpired;
	
	private Boolean Enabled;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "userPermission", joinColumns = @JoinColumn(name = "idUser"), 
			   inverseJoinColumns = @JoinColumn(name = "idPermission"))
	private Set<Permission> permission;

	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		for(Permission permission : this.permission) {
			roles.add(permission.getDescricao());
		}
		return roles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}