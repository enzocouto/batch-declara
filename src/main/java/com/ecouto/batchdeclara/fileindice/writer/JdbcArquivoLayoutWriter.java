package com.ecouto.batchdeclara.fileindice.writer;


import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecouto.batchdeclara.model.ArquivoLayout;

@Configuration
public class JdbcArquivoLayoutWriter {

	@Bean
	public ItemWriter<ArquivoLayout> jdbcArquivoWriter(DataSource dataSource){
		
		System.out.println("GRAVANDO TABELA ARQUIVO_LAYOUT");

		return items -> items.forEach(arquivo -> {
			
			System.out.println("arquivo: "+arquivo.getNomeArquivo());
		});
		
	}	                                                          
}
