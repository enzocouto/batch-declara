package com.ecouto.batchdeclara.listener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ecouto.batchdeclara.enums.StatusEnvioEnum;
import com.ecouto.batchdeclara.model.FileIndice;


public class JobListenerExecution implements JobExecutionListener {

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		
		System.out.println("JobListenerExecution beforeJob");
		logarFileIndice();
		 		
	}

	private void logarFileIndice() {
		String sql = "SELECT ID, NOMEARQUIVO, STATUS FROM file_indice";

		 List<FileIndice>  listaFiles = jdbcTemplate.query(sql,new RowMapper<FileIndice>() {

			@Override
			public FileIndice mapRow(ResultSet rs, int rowNum) throws SQLException {
				FileIndice FileIndice = new FileIndice();
				FileIndice.setId(rs.getLong(1));
				FileIndice.setNomeArquivo(rs.getString(2));
				FileIndice.setStatus(rs.getInt(3));
				return FileIndice;
			}
			 
		 });
		 
		 
		 if(listaFiles.isEmpty()) {
			 System.out.println("Nâo existem arquivos na tabela File_indice para serem processados.");
		 }
		 
		 System.out.println("Serão processados os seguintes arquivos:");
		 for(FileIndice file: listaFiles) {
			 System.out.print(file.getNomeArquivo() + " -  status: " + file.getStatus()+"\t");
			 if(file.getStatus() != null && file.getStatus() == StatusEnvioEnum.PROCESSAMENTO_FINALIZADO.getValor() ) {
				 System.out.println("ARQUIVO JÁ FOI PROCESSADO. STATUS É FINALIZADO");
			 }else {
				 System.out.println("");
			 }
		 }
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("JobListenerExecution afterJob");
		
		updateQtdEventoAndQtdXML();
		updateFileIndiceToFinished();
		
	}
	
	private void updateQtdEventoAndQtdXML() {
		
		        //ÄTUALIZA QUANTIDADE DE EVENTOS , QUANTIDADE DE XML E STATUS_ENVIO PARA PROCESSAMENTO_FINALIZADO 
		
				StringBuilder strBuilder = new StringBuilder();
				strBuilder.append("UPDATE arquivo_layout SET qtd_evento = (SELECT SUM(qtd_evento)");
				strBuilder.append(" FROM xml_gerado WHERE xml_gerado.ID_LAYOUT_ARQUIVO  = arquivo_layout.id), QTD_XML = (SELECT COUNT(*)");
				strBuilder.append(" FROM xml_gerado WHERE xml_gerado.ID_LAYOUT_ARQUIVO  = arquivo_layout.id), status_envio = ? ");
				strBuilder.append(" where arquivo_layout.STATUS_ENVIO = ?");
				
				
				 jdbcTemplate.update(connection -> {
				        PreparedStatement ps = connection
				          .prepareStatement(strBuilder.toString(), Statement.RETURN_GENERATED_KEYS);
				          ps.setLong(1, StatusEnvioEnum.PROCESSAMENTO_FINALIZADO.getValor());       
				          ps.setLong(2, StatusEnvioEnum.PROCESSANDO.getValor());          
				          return ps;
				        });
	}
	
	
	private void updateFileIndiceToFinished() {
			
		//ÄTUALIZA file_indice para status Processamento para Finalizado 	
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("UPDATE file_indice SET STATUS = ? ");
		
		 jdbcTemplate.update(connection -> {
		        PreparedStatement ps = connection
		          .prepareStatement(strBuilder.toString(), Statement.RETURN_GENERATED_KEYS);		         
		          ps.setLong(1, StatusEnvioEnum.PROCESSAMENTO_FINALIZADO.getValor());          
		          return ps;
		        });
	}
  
}
