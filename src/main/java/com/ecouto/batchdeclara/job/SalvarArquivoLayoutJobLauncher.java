package com.ecouto.batchdeclara.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SalvarArquivoLayoutJobLauncher extends SimpleJobLauncher {
	
	
	
  @Autowired
  public SalvarArquivoLayoutJobLauncher(JobRepository jobRepository) {
    this.setJobRepository(jobRepository);
  }
  
  @Override
  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  public JobExecution run(Job job, JobParameters jobParameters) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
    return super.run(job, jobParameters);
  }
}