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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Essa estrutura transforma a classe em uma tabela com suas respectivas campos, usando a Persistencia.

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Limitando o Hash Code a um atributo especifico
@NoArgsConstructor
// com essas anotações acima do Lombok, nao necessita de criar linhas de codigo
@Entity //"Transoforma" a classe em tabela na persistencia pro banco de dados
@Table(name = "Proprietarios")
public class Proprietario implements Serializable{
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include // incluindo o atributo no Hash Code
	@Id //Informar que esse atributo é uma chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto incremento
	@Column
	private Integer codigo;
	
	@NotBlank
	@Size(max = 60)
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Email
	@Column(nullable = false)
	private String email;
	
	@NotBlank
	@Size(max = 14)
	@Column(nullable = false)
	private String cpf;
	
	@OneToMany(mappedBy = "proprietario")
	private List<Obra> obras = new ArrayList<>();

	public Proprietario(Integer codigo, String nome, String email, String cpf) {
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
	}

	/* Sem o uso de Lombok
	public Proprietario() {
		
	}
	
	public Proprietario(Integer codigo, String nome, String email, String cpf) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proprietario other = (Proprietario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}*/
}
