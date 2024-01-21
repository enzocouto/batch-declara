package com.ecouto.batchdeclara.model;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class XMLGerado {
	
	
	
	@Column(name = "idLayotArquivo")
	private Long idLayotArquivo;
	
	@Column(name = "nome_arquivo")
	private String nomeArquivo;
	
	@Column(name = "qtdEvento")
	private Integer qtdEvento;

}
