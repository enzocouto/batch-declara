package com.ecouto.batchdeclara.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

public class LeituraArquivoStepChunkListener implements ChunkListener {

 
	@Override
	public void beforeChunk(ChunkContext context) {
		
		int count = context.getStepContext().getStepExecution().getReadCount();
		if(count > 0) {
			String nomeArquivo = (String) context.getStepContext().getJobParameters().get("nomeArquivo");
			System.out.println("Lendo arquivo "+ nomeArquivo + ". Nº arquivos XML Processados:" + count);
		}
		
		
	}

	@Override
	public void afterChunk(ChunkContext context) {
		//System.out.println("FIM DO PROCESSAMENTO DO ARQUIVO");
		
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		System.out.println("Ocorreu um erro processando step: : " + context.getStepContext().getStepName());
		
	}
}
