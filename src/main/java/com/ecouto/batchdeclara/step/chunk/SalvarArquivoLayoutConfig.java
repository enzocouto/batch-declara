package com.ecouto.batchdeclara.step.chunk;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ecouto.batchdeclara.model.ArquivoLayout;

@Configuration
public class SalvarArquivoLayoutConfig {


	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Bean
	public Step salvarArquivoLayoutStep() {
		return stepBuilderFactory
				.get("salvarArquivoLayout")
				.tasklet(tasklet())
				.build();
	}

    private Tasklet tasklet() {
		
    	return new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
				
				if(jobParameters.getString("nomeArquivo") != null) {
					
					String nomeArquivo = jobParameters.getString("nomeArquivo");
					
					ArquivoLayout arquivoLayout = new ArquivoLayout(nomeArquivo);
					
					StringBuilder strBuilder = new StringBuilder();
					strBuilder.append("INSERT INTO ARQUIVO_LAYOUT(TIPO_LAYOUT,NOME_ARQUIVO,TIPO_ENVIO,");
					strBuilder.append(" ANO_MES_REF,DATA_PROCESSAMENTO,STATUS_ENVIO) VALUES (?,?,?,?,?,?)");
					
					
					KeyHolder keyHolder = new GeneratedKeyHolder();
					
					
					 jdbcTemplate.update(connection -> {
					        PreparedStatement ps = connection
					          .prepareStatement(strBuilder.toString(), Statement.RETURN_GENERATED_KEYS);
					          ps.setString(1, arquivoLayout.getTipoLayout());
					          ps.setString(2, arquivoLayout.getNomeArquivo());
					          ps.setInt(3, arquivoLayout.getTipoEnvio().getValor());
					          ps.setString(4, arquivoLayout.getAnoMesRef());
					          ps.setTimestamp(5, Timestamp.valueOf(arquivoLayout.getDtProcessamento()));
					          ps.setInt(6, arquivoLayout.getStatusEnvio());
					          return ps;
					        }, keyHolder);
					
			             System.out.println("KEY: "+ keyHolder.getKey());
			             arquivoLayout.setId(keyHolder.getKey().longValue());
				}
				 															
				return RepeatStatus.FINISHED;
			}
		};
    	

	}

}
