package com.curso.enviopromocoes.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class EnvioPromocoesClientesScheduleJob extends QuartzJobBean {

	@Autowired
	private Job job;
	
	@Autowired
	private JobExplorer jobExplorer;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	private Logger log = LoggerFactory.getLogger(EnvioPromocoesClientesScheduleJob.class);
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobParameters jobParameters = new JobParametersBuilder(this.jobExplorer)
				.getNextJobParameters(job)
				.toJobParameters();
		
		try {
			jobLauncher.run(job, jobParameters);
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
	}

}
