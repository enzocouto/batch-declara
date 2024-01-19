package com.ecouto.batchdeclara.fileindice.processor;

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

import com.ecouto.batchdeclara.job.SalvarArquivoLayoutJobLauncher;
import com.ecouto.batchdeclara.model.ArquivoLayout;
import com.ecouto.batchdeclara.model.FileIndice;

@Component
public class FileIndiceProcessorConfig implements ItemProcessor<FileIndice, ArquivoLayout>{


	@Autowired
	public JobLauncher jobLauncher;
	
	@Autowired
	DataSource dataSource;
	
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	SalvarArquivoLayoutJobLauncher customJobLauncher;
		
	
	@Autowired
	Step salvarArquivoLayoutStep;
	
	@Override
	public ArquivoLayout process(FileIndice item) throws Exception {
		
		ArquivoLayout arquivoLayout = new ArquivoLayout(item.getNomeArquivo());
	
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
}
