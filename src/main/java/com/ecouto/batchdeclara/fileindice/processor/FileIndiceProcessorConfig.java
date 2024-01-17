package com.ecouto.batchdeclara.fileindice.processor;

import java.time.LocalDateTime;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.ecouto.batchdeclara.enums.StatusEnvio;
import com.ecouto.batchdeclara.enums.TipoEnvioEnum;
import com.ecouto.batchdeclara.model.ArquivoLayout;
import com.ecouto.batchdeclara.model.FileIndice;

@Component
public class FileIndiceProcessorConfig implements ItemProcessor<FileIndice, ArquivoLayout>{

	
	private static final Integer TIPO_LAYOUT = 2;
	private static final Integer TIPO_ENVIO = 3;
	private static final Integer ANO_MES_REF = 4; 
	
	@Override
	public ArquivoLayout process(FileIndice item) throws Exception {
		
		System.out.println("FileIndiceProcessorConfig");
		ArquivoLayout arquivoLayout = new ArquivoLayout();
		arquivoLayout.setNomeArquivo(item.getNomeArquivo());
		arquivoLayout.setTipoLayout(extractTipoLayout(item.getNomeArquivo()));
		arquivoLayout.setTipoEnvio(extractTipoEnvio(item.getNomeArquivo()));
		arquivoLayout.setAnoMesRef(extractAnoMesRef(item.getNomeArquivo()));
		arquivoLayout.setDtProcessamento(LocalDateTime.now());
		arquivoLayout.setStatusEnvio(StatusEnvio.AGUARDANDO_PROCESSAMENTO.getValor());
		
		return arquivoLayout;
	}

	private TipoEnvioEnum extractTipoEnvio(String nomeArquivo) {
		
		String strTipoEnvio = null;
		String[] splitNomeArquivo = getNomeArquivoSplit(nomeArquivo);
		if(splitNomeArquivo != null) 
			strTipoEnvio = splitNomeArquivo[TIPO_ENVIO];
		
		return TipoEnvioEnum.getTipoEnvioByName(strTipoEnvio);
	}

	private String extractTipoLayout(String nomeArquivo) {
		String tipoLayout = null;
		String[] splitNomeArquivo = getNomeArquivoSplit(nomeArquivo);
		if(splitNomeArquivo != null) 
			tipoLayout = splitNomeArquivo[TIPO_LAYOUT];
		
		return tipoLayout;
	}
	
	private String extractAnoMesRef(String nomeArquivo) {
		String anoMesRef = null;
		String[] splitNomeArquivo = getNomeArquivoSplit(nomeArquivo);
		if(splitNomeArquivo != null) 
			anoMesRef = splitNomeArquivo[ANO_MES_REF];
		
		return anoMesRef;
	}
	
	private String[] getNomeArquivoSplit(String nomeArquivo) {
		String[] splitNomeArquivo = nomeArquivo.split("_");
		return splitNomeArquivo;
	}
	
}
