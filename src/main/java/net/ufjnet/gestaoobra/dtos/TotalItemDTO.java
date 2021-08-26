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
public class TotalItemDTO extends RepresentationModel<TotalItemDTO> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String itemDescricao;
	
	private Double total;
	
	public TotalItemDTO(Item obj, Double total) {
		this.itemDescricao = obj.getDescricao();
		this.total = total;
	}
}
