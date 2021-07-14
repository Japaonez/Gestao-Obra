package net.ufjnet.gestaoobra.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.ufjnet.gestaoobra.models.Item;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"codigoItem", "descricaoItem", "complementoItem"})
public class ItemDTO extends RepresentationModel<ItemDTO> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@JsonProperty("codigoItem")
	private Integer codigo;

	@NotBlank
	@Size(max = 255)
	@JsonProperty("descricaoItem")
	private String descricao;

	@JsonProperty("complementoItem")
	private String complemento;
	
	public ItemDTO(Item obj) {
		codigo = obj.getCodigo();
		descricao = obj.getDescricao();
		complemento = obj.getComplemento();
	}
}
