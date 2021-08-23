package net.ufjnet.gestaoobra.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Itens")
public class Item implements Serializable{
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer codigo;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private String complemento;
	
	@OneToMany(mappedBy = "item")
	private List<SubItem> subitens = new ArrayList<>();

	public Item(Integer codigo, String descricao, String complemento) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.complemento = complemento;
	}
}
