package com.curso.sbatch.jobs;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ParImparBatch {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job imprimeParImparJob() {
		return jobBuilderFactory.get("imprimeParImparJob").start(imprimeParImparStep())
				.incrementer(new RunIdIncrementer()).build();
	}

	/**
	 * chunk é o intervalo de commit no banco
	 * @return
	 */
	protected Step imprimeParImparStep() {
		return stepBuilderFactory
				.get("imprimeParImparStep")
				.<Integer, String>chunk(10)
				.reader(contaReader())
				.processor(parOrImparProcessor())
				.writer(imprimeSaida())
				.build();
	}

	private ItemWriter<String> imprimeSaida() {
		return itens -> itens.forEach(System.out::println);
	}

	private ItemProcessor<Integer, String> parOrImparProcessor() {
		Function<Integer, String> parImpar = item -> item % 2 == 0 ? String.format("Item %d é par.", item) : String.format("Item %d é impar.", item);
		return new FunctionItemProcessor<Integer, String>(parImpar);
	}

	private ItemReader<Integer> contaReader() {
		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);		
		return new IteratorItemReader<Integer>(numeros.iterator());		
	}

}
