package com.ecouto.batchdeclara.step.chunk;


import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecouto.batchdeclara.arquivolayout.writer.GravarLeituraArquivoLayout;
import com.ecouto.batchdeclara.listener.LeituraArquivoStepChunkListener;
import com.ecouto.batchdeclara.listener.LeituraNomesStepChunkListener;
import com.ecouto.batchdeclara.model.ArquivoLayout;
import com.ecouto.batchdeclara.model.XMLGerado;


@Configuration
public class LeituraArquivosStepConfig {


	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
		
	@Bean
	@StepScope
	public Step leituraArquivoStep(
			FlatFileItemReader<XMLGerado> leituraArquivoLayout,ItemProcessor<XMLGerado, XMLGerado> processor,
			JdbcBatchItemWriter<XMLGerado> write) {
			      
		return stepBuilderFactory
				.get("leituraArquivoStep")
				.<XMLGerado, XMLGerado>chunk(1000)
				.reader(leituraArquivoLayout)
				.processor(processor)
				.writer(write)
				.listener(chunkListener())
				.build();
	}
	
	@StepScope
    public ChunkListener chunkListener() {
		
		return new LeituraArquivoStepChunkListener();
	}
	
}
