package com.ecouto.batchdeclara.listener;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ecouto.batchdeclara.enums.StatusEnvioEnum;



public class JobListenerExecution implements JobExecutionListener {

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("JobListenerExecution beforeJob");
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("JobListenerExecution afterJob");
		
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("UPDATE arquivo_layout SET qtd_evento = (SELECT SUM(qtd_evento)");
		strBuilder.append(" FROM xml_gerado WHERE xml_gerado.ID_LAYOUT_ARQUIVO  = arquivo_layout.id)");
		strBuilder.append(" where arquivo_layout.STATUS_ENVIO = ?");
		
		
		 jdbcTemplate.update(connection -> {
		        PreparedStatement ps = connection
		          .prepareStatement(strBuilder.toString(), Statement.RETURN_GENERATED_KEYS);
		          ps.setLong(1, StatusEnvioEnum.AGUARDANDO_PROCESSAMENTO.getValor());	         
		          return ps;
		        });
		
	}
  
}
