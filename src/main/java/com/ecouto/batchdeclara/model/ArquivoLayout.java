package com.ecouto.batchdeclara.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ecouto.batchdeclara.enums.StatusEnvioEnum;
import com.ecouto.batchdeclara.enums.TipoEnvioEnum;

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
@Entity
@ToString
@EqualsAndHashCode
public class ArquivoLayout {

	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tipo_layout")
	private String tipoLayout;
	
	@Column(name = "nome_arquivo")
	private String nomeArquivo;
	
	@Column(name = "tipo_envio")
	private Integer tipoEnvio;
	
	@Column(name = "qtdEvento")
	private Integer qtdEvento;
	
	@Column(name = "ano_mes_ref")
	private String anoMesRef;
	
	@Column(name = "data_processamento")
	private LocalDateTime dtProcessamento;
	
	@Column(name = "status_envio")
	private Integer statusEnvio;
	
	
	private static final Integer TIPO_LAYOUT = 2;
	private static final Integer TIPO_ENVIO = 3;
	private static final Integer ANO_MES_REF = 4;
	private static final String SPLIT_CARACT = "_"; 
	
	public ArquivoLayout(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
		this.tipoLayout = extractTipoLayout(nomeArquivo);
		this.tipoEnvio = extractTipoEnvio(nomeArquivo);
		this.qtdEvento = 0;
		this.anoMesRef = extractAnoMesRef(nomeArquivo);
		this.dtProcessamento = LocalDateTime.now();
		this.statusEnvio = StatusEnvioEnum.AGUARDANDO_PROCESSAMENTO.getValor();
		
	}
	
	private static String extractTipoLayout(String nomeArquivo) {
		String tipoLayout = null;
		String[] splitNomeArquivo = getNomeArquivoSplit(nomeArquivo);
		if(splitNomeArquivo != null) 
			tipoLayout = splitNomeArquivo[TIPO_LAYOUT];
		
		return tipoLayout;
	}
	
	private static String[] getNomeArquivoSplit(String nomeArquivo) {
		String[] splitNomeArquivo = nomeArquivo.split(SPLIT_CARACT);
		return splitNomeArquivo;
	}
	
    private static Integer extractTipoEnvio(String nomeArquivo) {
		
		String strTipoEnvio = null;
		String[] splitNomeArquivo = getNomeArquivoSplit(nomeArquivo);
		if(splitNomeArquivo != null) 
			strTipoEnvio = splitNomeArquivo[TIPO_ENVIO];
		
		return TipoEnvioEnum.getTipoEnvioByName(strTipoEnvio).getValor();
	}
    
    private String extractAnoMesRef(String nomeArquivo) {
		String anoMesRef = null;
		String[] splitNomeArquivo = getNomeArquivoSplit(nomeArquivo);
		if(splitNomeArquivo != null) 
			anoMesRef = splitNomeArquivo[ANO_MES_REF];
		
		return anoMesRef;
	}
}
