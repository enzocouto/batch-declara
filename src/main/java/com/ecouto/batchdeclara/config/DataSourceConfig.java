package com.ecouto.batchdeclara.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
	
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource springDataSource() {
		return DataSourceBuilder.create().build();
	}
	
}
