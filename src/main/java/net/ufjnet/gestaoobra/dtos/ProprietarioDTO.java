package net.ufjnet.gestaoobra.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.ufjnet.gestaoobra.dtos.ValidationGroups.ProprietarioId;
import net.ufjnet.gestaoobra.models.Proprietario;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"codigoProp", "nomeProp", "emailProp", "cpfProp"})
public class ProprietarioDTO extends RepresentationModel<ProprietarioDTO> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull(groups = ProprietarioId.class)
	@EqualsAndHashCode.Include
	@JsonProperty("codigoProp")
	private Integer codigo;
	
	@NotBlank
	@Size(max = 60)
	@JsonProperty("nomeProp")
	private String nome;
	
	@Email
	@JsonProperty("emailProp")
	private String email;
	
	@NotBlank
	@Size(max = 14)
	@JsonProperty("cpfProp")
	private String cpf;
	
	public ProprietarioDTO(Proprietario obj) {
		this.codigo = obj.getCodigo();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.cpf = obj.getCpf();
	}
}
