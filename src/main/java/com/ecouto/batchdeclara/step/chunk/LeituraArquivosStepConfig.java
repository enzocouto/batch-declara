package com.ecouto.batchdeclara.step.chunk;


import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecouto.batchdeclara.arquivolayout.writer.GravarLeituraArquivoLayout;
import com.ecouto.batchdeclara.listener.ArquivoLayoutChunkListener;
import com.ecouto.batchdeclara.model.ArquivoLayout;
import com.ecouto.batchdeclara.model.XMLGerado;


@Configuration
public class LeituraArquivosStepConfig {


	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	
	@Autowired
	GravarLeituraArquivoLayout gravarLeituraArquivoLayout;
	
	@Bean
	@StepScope
	public Step leituraArquivoStep(
			FlatFileItemReader<XMLGerado> leituraArquivoLayout,ItemProcessor<XMLGerado, ArquivoLayout> processor) {
			      
		return stepBuilderFactory
				.get("leituraArquivoStep")
				.<XMLGerado, ArquivoLayout>chunk(1000)
				.reader(leituraArquivoLayout)
				.processor(processor)
				.writer(gravarLeituraArquivoLayout)
				.listener(chunkListener())
				.build();
	}
	
	@StepScope
    public ChunkListener chunkListener() {
		
		return new ArquivoLayoutChunkListener();
	}
	
}
