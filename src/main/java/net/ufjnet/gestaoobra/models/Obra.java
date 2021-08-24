package net.ufjnet.gestaoobra.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "Obras")
public class Obra implements Serializable{
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer codigo;

	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private String localizacao;
	
	@Column
	private String complemento;
	
	@ManyToOne
	private Proprietario proprietario;
	
	@OneToMany(mappedBy = "obra")
	private Set<Lancamento> lancamentos;

	public Obra(Integer codigo, String descricao, String localizacao, String complemento, Proprietario proprietario) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.localizacao = localizacao;
		this.complemento = complemento;
		this.proprietario = proprietario;
	}
	
}
