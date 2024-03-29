package com.ecouto.batchdeclara.arquivolayout.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.ecouto.batchdeclara.model.XMLGerado;



@Configuration
public class LeituraArquivoLayoutConfig {
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	@StepScope
	public FlatFileItemReader<XMLGerado> leituraArquivoLayout(@Value("#{jobParameters['nomeArquivo']}") String nomeArquivoLayout) throws UnexpectedInputException, ParseException, Exception {
		

		Resource resource = new FileSystemResource("src/main/resources/"+nomeArquivoLayout);
		
		return new FlatFileItemReaderBuilder()
				.name("leituraArquivoLayout")
				.resource(resource)
				.delimited()
				.delimiter(";")
				.names("nomeArquivo","qtdEvento")
				.targetType(XMLGerado.class)
				.build();	
		 
	}

}
