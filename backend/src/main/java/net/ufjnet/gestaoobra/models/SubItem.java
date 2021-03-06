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

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Subitens")
public class SubItem implements Serializable{
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer codigo;

	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@Column
	private String complemento;
	
	@ManyToOne
	private Item item;
	
	@OneToMany(mappedBy = "subitem")
	private Set<Lancamento> lancamentos;

	public SubItem(Integer codigo, String descricao, String complemento, Item item) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.complemento = complemento;
		this.item = item;
	}
}
