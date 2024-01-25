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
public class ArquivoLayoutProcessor implements ItemProcessor<XMLGerado, ArquivoLayout>, ItemProcessListener<XMLGerado,ArquivoLayout> {

	
	        @Value("#{jobParameters['nomeArquivo']}")
	        public String nomeArquivo;
	     
	        @Autowired
	    	public JdbcTemplate jdbcTemplate;
	        
	        
	        private Long qtdEvento = 0L;
	        
			@Override
			public ArquivoLayout process(XMLGerado xMLGerado) throws Exception {
							
				//System.out.println("ArquivoLayoutProcessor ItemProcessor:"+nomeArquivo); 
				
				ArquivoLayout arquivo = findByNomeArquivo(nomeArquivo);
				
			    this.qtdEvento = this.qtdEvento + xMLGerado.getQtdEvento();
			    arquivo.setQtdEvento(qtdEvento);
			    xMLGerado.setIdLayotArquivo(arquivo.getId());
				
				arquivo.setXmlsGerado(xMLGerado);
				//System.out.println("ArquivoLayoutProcessor ItemProcessor:"+arquivo); 
				return arquivo;
			}
			
			
			@SuppressWarnings({ "unchecked", "unchecked", "rawtypes" })
			public ArquivoLayout findByNomeArquivo(String nomeArquivo) {

		        String sql = "SELECT * FROM ARQUIVO_LAYOUT WHERE NOME_ARQUIVO = ?";

		        return (ArquivoLayout) jdbcTemplate.queryForObject(
					sql, 
					new Object[]{nomeArquivo}, 
					new BeanPropertyRowMapper(ArquivoLayout.class));
		    }


			@Override
			public void beforeProcess(XMLGerado item) {
				//System.out.println("ArquivoLayoutProcessor - beforeProcess: "+item);
				
			}


			@Override
			public void afterProcess(XMLGerado item, ArquivoLayout result) {
				//result.setQtdEvento(qtdEvento);
				//System.out.println("ArquivoLayoutProcessor - afterProcess: "+item + " - "+ result);
				
			}


			@Override
			public void onProcessError(XMLGerado item, Exception e) {
				System.out.println("ArquivoLayoutProcessor - onProcessError" + item.getNomeArquivoXML() + e.getMessage());
				
			}


}
