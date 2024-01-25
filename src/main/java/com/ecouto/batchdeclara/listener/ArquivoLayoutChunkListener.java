package com.ecouto.batchdeclara.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

public class ArquivoLayoutChunkListener implements ChunkListener {

 

	@Override
	public void beforeChunk(ChunkContext context) {
		
		System.out.println("INICIO DO PROCESSAMENTO DO ARQUIVO");
		
	}

	@Override
	public void afterChunk(ChunkContext context) {
		System.out.println("FIM DO PROCESSAMENTO DO ARQUIVO");
		
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		System.out.println("Ocorreu um erro processando step: : " + context.getStepContext().getStepName());
		
	}
}
