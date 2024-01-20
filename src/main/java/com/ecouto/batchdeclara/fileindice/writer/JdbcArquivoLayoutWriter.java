package com.ecouto.batchdeclara.fileindice.writer;


import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ecouto.batchdeclara.model.ArquivoLayout;

@Configuration
public class JdbcArquivoLayoutWriter  {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	List<ArquivoLayout> arquivosLayout = new ArrayList<ArquivoLayout>();
	
	

	@Bean
	public ItemWriter<ArquivoLayout> jdbcArquivoWriter(DataSource dataSource){
		
		System.out.println("GRAVANDO TABELA ARQUIVO_LAYOUT");
	
	    return items -> items.forEach(arquivo -> {
	    	ArquivoLayout arquivoSalvo = findByNomeArquivo(arquivo.getNomeArquivo());
			arquivosLayout.add(arquivoSalvo);		
			System.out.println("arquivo consultado: "+arquivoSalvo);
	    });
		
	}	  
	
	@SuppressWarnings({ "unchecked", "unchecked", "rawtypes" })
	public ArquivoLayout findByNomeArquivo(String nomeArquivo) {

        String sql = "SELECT * FROM ARQUIVO_LAYOUT WHERE NOME_ARQUIVO = ?";

        return (ArquivoLayout) jdbcTemplate.queryForObject(
			sql, 
			new Object[]{nomeArquivo}, 
			new BeanPropertyRowMapper(ArquivoLayout.class));

    }

}
