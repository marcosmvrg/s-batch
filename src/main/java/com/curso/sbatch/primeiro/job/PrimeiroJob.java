package com.curso.sbatch.primeiro.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class PrimeiroJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job imprimeOlaJob(Step imprimeOlaStep) {
		return jobBuilderFactory
				.get("imprimeOlaJob")
				.start(imprimeOlaStep)
				.incrementer(new RunIdIncrementer()) //incrementa a execucao do job porque o padrão é uma unica execucao. 
				.build();
	}

	
}
