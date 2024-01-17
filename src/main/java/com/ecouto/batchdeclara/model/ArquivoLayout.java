package com.ecouto.batchdeclara.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ecouto.batchdeclara.enums.TipoEnvioEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ArquivoLayout {

	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tipo_layout")
	private String tipoLayout;
	
	@Column(name = "nome_arquivo")
	private String nomeArquivo;
	
	@Column(name = "tipo_envio")
	private TipoEnvioEnum tipoEnvio;
	
	@Column(name = "qtdEvento")
	private Integer qtdEvento;
	
	@Column(name = "ano_mes_ref")
	private String anoMesRef;
	
	@Column(name = "data_processamento")
	private LocalDateTime dtProcessamento;
	
	@Column(name = "status_envio")
	private Integer statusEnvio;
}
