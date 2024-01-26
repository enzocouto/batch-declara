package com.ecouto.batchdeclara.arquivolayout.processor;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ecouto.batchdeclara.model.ArquivoLayout;
import com.ecouto.batchdeclara.model.XMLGerado;

@Component
@StepScope
public class ArquivoLayoutProcessor implements ItemProcessor<XMLGerado, XMLGerado>, ItemProcessListener<XMLGerado,XMLGerado> {

	
	        @Value("#{jobParameters['nomeArquivo']}")
	        public String nomeArquivo;
	     
	        @Autowired
	    	public JdbcTemplate jdbcTemplate;
	            
	        @Override
			public XMLGerado process(XMLGerado item) throws Exception {
				ArquivoLayout arquivo = findByNomeArquivo(nomeArquivo);
				item.setIdLayotArquivo(arquivo.getId());
				return item;
			}
	        
	       
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public ArquivoLayout findByNomeArquivo(String nomeArquivo) {

		        String sql = "SELECT * FROM ARQUIVO_LAYOUT WHERE NOME_ARQUIVO = ?";

		        return (ArquivoLayout) jdbcTemplate.queryForObject(
					sql, 
					new Object[]{nomeArquivo}, 
					new BeanPropertyRowMapper(ArquivoLayout.class));
		    }


			@Override
			public void beforeProcess(XMLGerado item) {}

			 @Override
			public void afterProcess(XMLGerado item, XMLGerado result) {}
			 
			 
			@Override
			public void onProcessError(XMLGerado item, Exception e) {
				System.out.println("ArquivoLayoutProcessor - onProcessError" + item.getNomeArquivoXML() + e.getMessage());
				
			}

}
