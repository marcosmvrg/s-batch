package com.curso.migracaodadosjob.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class MigracaoDadosJobConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job migracaoDadosJob(
			@Qualifier("migraPessoaStep") Step migraPessoaStep, 
			@Qualifier("migraDadoBancarioStep") Step migraDadoBancarioStep) {
		return jobBuilderFactory
				.get("migracaoDadosJob")
				.start(stepsParalelos(migraPessoaStep, migraDadoBancarioStep))
				.end()
				.incrementer(new RunIdIncrementer())
				.build();
	}

	/**
	 * Criando uma execução simultanea com multi threads
	 * @param migraPessoaStep
	 * @param migraDadoBancarioStep
	 * @return
	 */
	private Flow stepsParalelos(Step migraPessoaStep, Step migraDadoBancarioStep) {
		Flow migrarDadoBancarioFlow = new FlowBuilder<Flow>("migrarDadoBancarioFlow")
				.start(migraDadoBancarioStep)
				.build(); 
		
		return new FlowBuilder<Flow>("stepsParalelosFlow")
				.start(migraPessoaStep)
				.split(new SimpleAsyncTaskExecutor())
				.add(migrarDadoBancarioFlow)
				.build();
		
	}
	
	/**
	 * .start(migraPessoaStep) faz este e depois o proximo. o exemplo 
	 * foi modificado para usar multi treads
				.next(migraDadoBancarioStep)
	 */
	
}
