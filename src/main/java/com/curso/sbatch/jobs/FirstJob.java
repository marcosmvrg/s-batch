package com.curso.sbatch.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class FirstJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job helloWorld() {
		return jobBuilderFactory
				.get("helloWorld")
				.start(printHelloWorld())
				.incrementer(new RunIdIncrementer()) //incrementa a execucao do job porque o padrão é uma unica execucao. 
				.build();
	}

	protected Step printHelloWorld() {
		return stepBuilderFactory
				.get("printHelloWorld")
				.tasklet(printTasklet(null))
				.build();
	}

	@Bean
	@StepScope
	protected Tasklet printTasklet(@Value("#{jobParameters['name']}") String name) {
		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println(String.format("Hello %s!", name));
				return RepeatStatus.FINISHED;
			}
		};
	}
}
