package com.ecouto.batchdeclara.arquivolayout.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.ecouto.batchdeclara.model.ArquivoLayout;



@Configuration
public class LeituraArquivoLayoutConfig {
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public FlatFileItemReader<ArquivoLayout> reader(@Value("jobParameters['arquivo']") Resource arquivoLayout) throws UnexpectedInputException, ParseException, Exception {
		
		return new FlatFileItemReaderBuilder()
				.name("leituraArquivoLayout")
				.resource(arquivoLayout)
				.delimited()
				.delimiter(";")
				.names("nomeArquivo","qtdEvento")
				.targetType(ArquivoLayout.class)
				.build();	
		 
	}

	

}
