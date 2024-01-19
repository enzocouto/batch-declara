package com.ecouto.batchdeclara.step.chunk;

import java.time.LocalDateTime;

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

import com.ecouto.batchdeclara.enums.StatusEnvioEnum;
import com.ecouto.batchdeclara.enums.TipoEnvioEnum;
import com.ecouto.batchdeclara.model.ArquivoLayout;

@Configuration
public class SalvarArquivoLayoutConfig {

	
	private static final Integer TIPO_LAYOUT = 2;
	private static final Integer TIPO_ENVIO = 3;
	private static final Integer ANO_MES_REF = 4; 
	
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
					
					ArquivoLayout arquivoLayout = new ArquivoLayout();
					arquivoLayout.setNomeArquivo(nomeArquivo);
					arquivoLayout.setTipoLayout(extractTipoLayout(nomeArquivo));
					arquivoLayout.setTipoEnvio(extractTipoEnvio(nomeArquivo));
					arquivoLayout.setAnoMesRef(extractAnoMesRef(nomeArquivo));
					arquivoLayout.setDtProcessamento(LocalDateTime.now());
					arquivoLayout.setStatusEnvio(StatusEnvioEnum.AGUARDANDO_PROCESSAMENTO.getValor());
					
					StringBuilder strBuilder = new StringBuilder();
					strBuilder.append("INSERT INTO ARQUIVO_LAYOUT(TIPO_LAYOUT,NOME_ARQUIVO,TIPO_ENVIO,");
					strBuilder.append(" ANO_MES_REF,DATA_PROCESSAMENTO,STATUS_ENVIO) VALUES (?,?,?,?,?,?)");
			        jdbcTemplate.update(strBuilder.toString(), 
			        		arquivoLayout.getTipoLayout(), 
			        		arquivoLayout.getNomeArquivo(),
			        		arquivoLayout.getTipoEnvio().getValor(),
			        		arquivoLayout.getAnoMesRef(),
			        		arquivoLayout.getDtProcessamento(),
			        		arquivoLayout.getStatusEnvio()); 
								
				}
				 															
				return RepeatStatus.FINISHED;
			}
		};
    	

	}

	public TipoEnvioEnum extractTipoEnvio(String nomeArquivo) {
 		
		String strTipoEnvio = null;
		String[] splitNomeArquivo = getNomeArquivoSplit(nomeArquivo);
		if(splitNomeArquivo != null) 
			strTipoEnvio = splitNomeArquivo[TIPO_ENVIO];
		
		return TipoEnvioEnum.getTipoEnvioByName(strTipoEnvio);
	}

	private String extractTipoLayout(String nomeArquivo) {
		String tipoLayout = null;
		String[] splitNomeArquivo = getNomeArquivoSplit(nomeArquivo);
		if(splitNomeArquivo != null) 
			tipoLayout = splitNomeArquivo[TIPO_LAYOUT];
		
		return tipoLayout;
	}
	
	private String extractAnoMesRef(String nomeArquivo) {
		String anoMesRef = null;
		String[] splitNomeArquivo = getNomeArquivoSplit(nomeArquivo);
		if(splitNomeArquivo != null) 
			anoMesRef = splitNomeArquivo[ANO_MES_REF];
		
		return anoMesRef;
	}
	
	private String[] getNomeArquivoSplit(String nomeArquivo) {
		String[] splitNomeArquivo = nomeArquivo.split("_");
		return splitNomeArquivo;
	}
}
