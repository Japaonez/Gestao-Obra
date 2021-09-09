package net.ufjnet.gestaoobra.dtos;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.ufjnet.gestaoobra.models.SubItem;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"codigoSubItem", "descricaoSubItem", "localizacaoSubItem", "complementoSubItem", "Item"})
public class SubItemDTO extends RepresentationModel<SubItemDTO> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@JsonProperty("codigoSubItem")
	private Integer codigo;

	@NotBlank
	@Size(max = 255)
	@JsonProperty("descricaoSubItem")
	private String descricao;
	
	@JsonProperty("complementoSubItem")
	private String complemento;
	
	@NotNull
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ProprietarioId.class)
	private ItemDTO item;
	
	public SubItemDTO(SubItem obj) {
		codigo = obj.getCodigo();
		descricao = obj.getDescricao();
		complemento = obj.getComplemento();
		item = new ItemDTO(obj.getItem());
	}
}
