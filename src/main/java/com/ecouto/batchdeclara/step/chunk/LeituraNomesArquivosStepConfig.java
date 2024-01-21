package com.ecouto.batchdeclara.step.chunk;

import javax.sql.DataSource;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecouto.batchdeclara.fileindice.processor.FileIndiceProcessorConfig;
import com.ecouto.batchdeclara.fileindice.writer.JdbcArquivoLayoutWriterConfig;
import com.ecouto.batchdeclara.listener.GravarArquivoLayoutWriterListener;
import com.ecouto.batchdeclara.model.ArquivoLayout;
import com.ecouto.batchdeclara.model.FileIndice;

@Configuration
public class LeituraNomesArquivosStepConfig {

	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	

	@Autowired
	private FileIndiceProcessorConfig fileIndiceProcessor;
	
	
	@Autowired
	private JdbcArquivoLayoutWriterConfig jdbcArquivoLayoutWriter;
	
	@Bean
	public Step leituraNomesArquivosStep(ItemReader<FileIndice> leituraFileIndiceReader, DataSource dataSource) {
		
		return stepBuilderFactory.get("leituraNomesArquivosStep")
				.<FileIndice,ArquivoLayout>chunk(1)
				.reader(leituraFileIndiceReader)
				.processor(fileIndiceProcessor)
				.writer(jdbcArquivoLayoutWriter)
				.listener(writeListener())
				.build();
	}

	@Bean
	public ItemWriteListener writeListener() {
		return new GravarArquivoLayoutWriterListener();
	}

}
