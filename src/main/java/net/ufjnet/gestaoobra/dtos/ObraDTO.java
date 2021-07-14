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
import net.ufjnet.gestaoobra.models.Obra;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"codigoObra", "descricaoObra", "localizacaoObra", "complementoObra"})
public class ObraDTO extends RepresentationModel<ObraDTO> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@JsonProperty("codigoObra")
	private Integer codigo;
	
	@JsonProperty("descricaoObra")
	private String descricao;
	
	@JsonProperty("localizacaoObra")
	private String localizacao;
	
	@JsonProperty("complementoObra")
	private String complemento;
	
	private ProprietarioDTO proprietario;
	
	public ObraDTO(Obra obj) {
		codigo = obj.getCodigo();
		descricao = obj.getDescricao();
		localizacao = obj.getLocalizacao();
		complemento = obj.getComplemento();
		proprietario = new ProprietarioDTO(obj.getProprietario());
	}
}
