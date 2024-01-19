package com.ecouto.batchdeclara.fileindice.processor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecouto.batchdeclara.enums.StatusEnvioEnum;
import com.ecouto.batchdeclara.enums.TipoEnvioEnum;
import com.ecouto.batchdeclara.job.CustomJobLauncher;
import com.ecouto.batchdeclara.model.ArquivoLayout;
import com.ecouto.batchdeclara.model.FileIndice;

@Component
public class FileIndiceProcessorConfig implements ItemProcessor<FileIndice, ArquivoLayout>{

	
	private static final Integer TIPO_LAYOUT = 2;
	private static final Integer TIPO_ENVIO = 3;
	private static final Integer ANO_MES_REF = 4; 
	
	@Autowired
	public JobLauncher jobLauncher;
	
	@Autowired
	DataSource dataSource;
	
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	CustomJobLauncher customJobLauncher;
		
	
	@Autowired
	Step salvarArquivoLayoutStep;
	
	@Override
	public ArquivoLayout process(FileIndice item) throws Exception {
		
		ArquivoLayout arquivoLayout = new ArquivoLayout();
		arquivoLayout.setNomeArquivo(item.getNomeArquivo());
		arquivoLayout.setTipoLayout(extractTipoLayout(item.getNomeArquivo()));
		arquivoLayout.setTipoEnvio(extractTipoEnvio(item.getNomeArquivo()));
		arquivoLayout.setAnoMesRef(extractAnoMesRef(item.getNomeArquivo()));
		arquivoLayout.setDtProcessamento(LocalDateTime.now());
		arquivoLayout.setStatusEnvio(StatusEnvioEnum.AGUARDANDO_PROCESSAMENTO.getValor());
		
		
		Map<String, JobParameter> mapParameters = new HashMap<>();
		mapParameters.put("nomeArquivo", new JobParameter(arquivoLayout.getNomeArquivo()));
		JobParameters parameters = new JobParameters(mapParameters);
		customJobLauncher.run(salvarArquivoLayout(), parameters);

		return arquivoLayout;
	}
	

	private Job salvarArquivoLayout() {
		
		return jobBuilderFactory.get("salvarArquivoLayoutJob")
				.start(salvarArquivoLayoutStep)
				.build();
	}


	private TipoEnvioEnum extractTipoEnvio(String nomeArquivo) {
		
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
