package com.ecouto.batchdeclara.arquivolayout.writer;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ecouto.batchdeclara.model.ArquivoLayout;
import com.ecouto.batchdeclara.model.XMLGerado;


@Component
public class GravarLeituraArquivoLayout implements ItemWriter<ArquivoLayout> {
	
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public void write(List<? extends ArquivoLayout> items) throws Exception {
		
		for (ArquivoLayout arquivoLayout : items) {
			XMLGerado xmlGerado = arquivoLayout.getXmlsGerado();
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("INSERT INTO XML_GERADO (ID_LAYOUT_ARQUIVO,NOME_ARQUIVO_XML,QTD_EVENTO)");
			strBuilder.append(" VALUES(?,?,?)");
			
			
			 jdbcTemplate.update(connection -> {
			        PreparedStatement ps = connection
			          .prepareStatement(strBuilder.toString(), Statement.RETURN_GENERATED_KEYS);
			          ps.setLong(1, xmlGerado.getIdLayotArquivo());
			          ps.setString(2, xmlGerado.getNomeArquivoXML());
			          ps.setInt(3, xmlGerado.getQtdEvento());
			 
			          return ps;
			        });
		}
		
	}
}
