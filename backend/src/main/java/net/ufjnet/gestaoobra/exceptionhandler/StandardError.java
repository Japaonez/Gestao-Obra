package net.ufjnet.gestaoobra.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
public class StandardError {
	private Integer codigo;
	private LocalDateTime momento;
	private String descricao;
	private List<Fields> campos;

	@AllArgsConstructor
	@Getter
	@Setter
	public static class Fields {
		private String nome;
		private String mensagem;
	}
	
}