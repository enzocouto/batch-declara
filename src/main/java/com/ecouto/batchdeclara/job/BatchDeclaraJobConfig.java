package com.ecouto.batchdeclara.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecouto.batchdeclara.listener.JobListenerExecution;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchDeclaraJobConfig {
	
	
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
    @Bean
	public Job batchDeclaraJob(Step leituraNomesArquivosStep, Step leituraArquivoStep) {
		
		return jobBuilderFactory
				.get("batchDeclaraJob")			
				.incrementer(new RunIdIncrementer())
				.start(leituraNomesArquivosStep)
				.listener(jobListener())
				.build();
		
	}
    
	@Bean
	public JobListenerExecution jobListener() {
		return new JobListenerExecution();
	}

}
