package com.ecouto.batchdeclara.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Entity
public class XMLGerado {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ID_LAYOUT_ARQUIVO")
	private Long idLayotArquivo;
	
	@Column(name = "NOME_ARQUIVO_XML")
	private String nomeArquivoXML;
	
	@Column(name = "QTD_VENTO")
	private Integer qtdEvento;

}
