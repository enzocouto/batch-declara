package com.ecouto.batchdeclara.fileindice.writer;


import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ecouto.batchdeclara.model.ArquivoLayout;

@Configuration
public class JdbcArquivoLayoutWriterConfig implements ItemWriter<ArquivoLayout> {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	List<ArquivoLayout> arquivosLayout = new ArrayList<ArquivoLayout>();
	
	

	@Override
	public void write(List<? extends ArquivoLayout> items) throws Exception {
		
		for(ArquivoLayout arquivoLayout : items) {
			ArquivoLayout arquivoSalvo = findByNomeArquivo(arquivoLayout.getNomeArquivo());
			arquivosLayout.add(arquivoSalvo);		
			//System.out.println("JdbcArquivoLayoutWriter write - arquivo consultado : "+arquivoSalvo);
		}
		
		items = arquivosLayout;
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
