package com.ecouto.batchdeclara.fileindice.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.ecouto.batchdeclara.model.FileIndice;

@Configuration
public class LeituraFileIndiceReaderConfig {

	
	@Bean
	public JdbcCursorItemReader<FileIndice> leituraFileIndiceReader(DataSource dataSource){
		return new JdbcCursorItemReaderBuilder<FileIndice>()
				.name("jdbcCursorReader")
				.dataSource(dataSource)
				.sql("select * from file_indice where status = 1")
				.rowMapper(new BeanPropertyRowMapper<FileIndice>(FileIndice.class))
				.build();
	}
	
}
