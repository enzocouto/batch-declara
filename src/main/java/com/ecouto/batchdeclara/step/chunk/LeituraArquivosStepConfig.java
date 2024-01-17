package com.ecouto.batchdeclara.step.chunk;



import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecouto.batchdeclara.model.ArquivoLayout;
import com.ecouto.batchdeclara.model.FileIndice;
import com.ecouto.batchdeclara.writer.GravarLeituraArquivoLayout;


@Configuration
public class LeituraArquivosStepConfig implements StepExecutionListener {

	
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	
	@Autowired
	GravarLeituraArquivoLayout gravarLeituraArquivoLayout;
	
	@Bean
	public Step leituraArquivoStep(MultiResourceItemReader<ArquivoLayout> multiplosArquivosLayoutReader,
			ItemProcessor<ArquivoLayout, ArquivoLayout> processor) {
		
		
		return stepBuilderFactory
				.get("leituraArquivoStep")
				.<ArquivoLayout, ArquivoLayout>chunk(1)
				.reader(multiplosArquivosLayoutReader)
				.processor(processor)
				.writer(gravarLeituraArquivoLayout)
				.listener(this)
				.build();
	}


	


	@Override
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
	
		System.out.println("Step iniciado em: " + stepExecution.getEndTime());
	}


	@Override
	@AfterStep
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("Step finalizado em: " + stepExecution.getEndTime());
		return null;
	}

}
