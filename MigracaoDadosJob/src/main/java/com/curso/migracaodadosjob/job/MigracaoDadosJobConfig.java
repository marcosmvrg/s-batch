package com.curso.migracaodadosjob.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigracaoDadosJobConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job migracaoDadosJob(Step migraPessoaStep, Step migraDadoBancario) {
		return jobBuilderFactory
				.get("migracaoDadosJob")
				.start(migraPessoaStep)
				.next(migraDadoBancario)
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
}
