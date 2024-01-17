package com.ecouto.batchdeclara.step.chunk;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecouto.batchdeclara.fileindice.processor.FileIndiceProcessorConfig;
import com.ecouto.batchdeclara.fileindice.writer.GravarArquivoLayoutWriter;
import com.ecouto.batchdeclara.model.ArquivoLayout;
import com.ecouto.batchdeclara.model.FileIndice;

@Configuration
public class LeituraNomesArquivosStepConfig {

	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public GravarArquivoLayoutWriter gravarArquivoLayoutWriter;
	
	@Autowired
	FileIndiceProcessorConfig fileIndiceProcessor;
	
	@Bean
	public Step leituraNomesArquivosStep(ItemReader<FileIndice> leituraFileIndiceReader) {
		
		return stepBuilderFactory.get("leituraNomesArquivosStep")
				.<FileIndice,ArquivoLayout>chunk(1)
				.reader(leituraFileIndiceReader)
				.processor(fileIndiceProcessor)
				.writer(gravarArquivoLayoutWriter)
				.build();
	}
}
