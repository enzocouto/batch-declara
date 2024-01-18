package com.ecouto.batchdeclara.fileindice.writer;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.stereotype.Component;

import com.ecouto.batchdeclara.model.ArquivoLayout;

//@Component
public class JdbcArquivoLayoutWriterConfig {


	public JdbcBatchItemWriter<ArquivoLayout> jdbcArquivoWriter(DataSource dataSource){
		
		
		//System.out.println("STEP 1 - WRITER - GRAVANDO ARQUIVO LAYOUT");
		return new JdbcBatchItemWriterBuilder<ArquivoLayout>()
				.dataSource(dataSource)
				.sql("INSERT INTO ARQUIVO_LAYOUT(TIPO_LAYOUT,NOME_ARQUIVO,TIPO_ENVIO,"
						+ "ANO_MES_REF,DATA_PROCESSAMENTO,STATUS_ENVIO) VALUES (?,?,?,?,?,?)")
				.itemPreparedStatementSetter(itemPreparedStatementSetter())
				.build();
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
