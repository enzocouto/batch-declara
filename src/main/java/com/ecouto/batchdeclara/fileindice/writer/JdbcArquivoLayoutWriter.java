package com.ecouto.batchdeclara.fileindice.writer;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecouto.batchdeclara.model.ArquivoLayout;

@Configuration
public class JdbcArquivoLayoutWriter {

	@Bean
	public ItemWriter<ArquivoLayout> jdbcArquivoWriter(DataSource dataSource){
		
		System.out.println("GRAVANDO TABELA ARQUIVO_LAYOUT");
		/*
		JdbcBatchItemWriter<ArquivoLayout> jdbcBatchItemWriterBuilder = new JdbcBatchItemWriterBuilder<ArquivoLayout>()
				.dataSource(dataSource)
				.sql("INSERT INTO ARQUIVO_LAYOUT(TIPO_LAYOUT,NOME_ARQUIVO,TIPO_ENVIO,"
						+ "ANO_MES_REF,DATA_PROCESSAMENTO,STATUS_ENVIO) VALUES (?,?,?,?,?,?)")
				.itemPreparedStatementSetter(itemPreparedStatementSetter())
				.build();
		
		
		
		return jdbcBatchItemWriterBuilder;
		*/
		
		
		
		
		return items -> items.forEach(arquivo -> {
			System.out.println("arquivo: "+arquivo.getNomeArquivo());
		});
		
	}

	private ItemPreparedStatementSetter<ArquivoLayout> itemPreparedStatementSetter() {
		
		return new ItemPreparedStatementSetter<ArquivoLayout>() {

			@Override
			public void setValues(ArquivoLayout arquivoLayout, PreparedStatement ps) throws SQLException {
				ps.setString(1, arquivoLayout.getTipoLayout());
				ps.setString(2, arquivoLayout.getNomeArquivo());
				ps.setInt(3, arquivoLayout.getTipoEnvio().getValor());
				ps.setString(4, arquivoLayout.getAnoMesRef());
				ps.setTimestamp(5, Timestamp.valueOf(arquivoLayout.getDtProcessamento()));
				ps.setInt(6, arquivoLayout.getStatusEnvio());
			}
			
		};
	}
	                                                          
}
