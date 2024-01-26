package com.ecouto.batchdeclara.arquivolayout.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecouto.batchdeclara.model.XMLGerado;


@Configuration
public class GravarLeituraArquivoLayout {
	
	@Bean
	public JdbcBatchItemWriter<XMLGerado> write(DataSource dataSource) throws Exception {
		
		
		      StringBuilder strBuilder = new StringBuilder();
		      strBuilder.append("INSERT INTO XML_GERADO(");
		      strBuilder.append("ID_LAYOUT_ARQUIVO,");
		      strBuilder.append("NOME_ARQUIVO_XML,");
		      strBuilder.append("QTD_EVENTO");
		      strBuilder.append(") VALUES(");
		      strBuilder.append(":idLayotArquivo, :nomeArquivoXML, :qtdEvento)");
		      
              return new JdbcBatchItemWriterBuilder<XMLGerado>()
            		  .dataSource(dataSource)
            		  .sql(strBuilder.toString())
            		  .beanMapped()
            		  .build();
		
	}
}
