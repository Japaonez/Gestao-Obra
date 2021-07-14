package net.ufjnet.gestaoobra.dtos;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.ufjnet.gestaoobra.models.Proprietario;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"codigoProp", "nomeProp", "emailProp", "cpfProp"})
public class ProprietarioDTO extends RepresentationModel<ProprietarioDTO> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@JsonProperty("codigoProp")
	private Integer codigo;
	
	@JsonProperty("nomeProp")
	private String nome;
	
	@JsonProperty("emailProp")
	private String email;
	
	@JsonProperty("cpfProp")
	private String cpf;
	
	public ProprietarioDTO(Proprietario obj) {
		this.codigo = obj.getCodigo();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.cpf = obj.getCpf();
	}
}
