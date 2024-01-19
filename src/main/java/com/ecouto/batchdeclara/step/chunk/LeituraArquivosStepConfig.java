package com.ecouto.batchdeclara.step.chunk;


import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecouto.batchdeclara.arquivolayout.writer.GravarLeituraArquivoLayout;
import com.ecouto.batchdeclara.model.ArquivoLayout;


@Configuration
public class LeituraArquivosStepConfig {


	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	
	@Autowired
	GravarLeituraArquivoLayout gravarLeituraArquivoLayout;
	
	@Bean
	public Step leituraArquivoStep(
			ItemProcessor<ArquivoLayout, ArquivoLayout> processor,FlatFileItemReader<ArquivoLayout> leituraArquivoLayout) {
			      
		return stepBuilderFactory
				.get("leituraArquivoStep")
				.<ArquivoLayout, ArquivoLayout>chunk(1)
				.reader(leituraArquivoLayout)
				.processor(processor)
				.writer(gravarLeituraArquivoLayout)
				.build();
	}

}
