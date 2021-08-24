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
import net.ufjnet.gestaoobra.models.Lancamento;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"codigoLancamento", "obra", "item", "subitem", "valorLancamento", "descricaoLancamento", "documentoLancamento", "observacoesLancamento"})
public class LancamentoDTO extends RepresentationModel<LancamentoDTO> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@JsonProperty("codigoLancamento")
	private Integer codigo;
	
	@NotNull
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ObraId.class)
	@JsonProperty("obra")
	private ObraDTO obra;
	
	@NotNull
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ItemId.class)
	@JsonProperty("item")
	private ItemDTO item;
	
	@JsonProperty("subitem")
	private SubItemDTO subitem;
	
	@JsonProperty("valorLancamento")
	private Double valor;

	@NotBlank
	@Size(max = 255)
	@JsonProperty("descricaoLancamento")
	private String descricao;
	
	@JsonProperty("documentoLancamento")
	private String documento;
	
	@JsonProperty("observacoesLancamento")
	private String observacoes;
	
	public LancamentoDTO(Lancamento obj) {
		codigo = obj.getCodigo();
		obra = new ObraDTO(obj.getObra());
		item = new ItemDTO(obj.getItem());
		subitem = new SubItemDTO(obj.getSubitem());
		valor = obj.getValor();
		descricao = obj.getDescricao();
		documento = obj.getDocumento();
		observacoes = obj.getObservacoes();
	}
}
