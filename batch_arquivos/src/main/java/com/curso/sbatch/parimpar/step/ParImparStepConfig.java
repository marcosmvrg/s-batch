package com.curso.sbatch.parimpar.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.sbatch.parimpar.chunk.ParImparProcessor;
import com.curso.sbatch.parimpar.chunk.ParImparReader;
import com.curso.sbatch.parimpar.chunk.ParImparWriter;

@Configuration(value = "parImparStep")
public class ParImparStepConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public ItemReader<Integer> itemReader() {
		return new ParImparReader().contaReader();
	}

	@Bean
	public ItemProcessor<Integer, String> itemProcessor() {
		return new ParImparProcessor().parOrImparProcessor();
	}

	@Bean
	public ItemWriter<String> itemWriter() {
		return new ParImparWriter().imprimeSaida();
	}

	/**
	 * chunk é o intervalo de commit no banco
	 * 
	 * @return
	 */
	@Bean
	public Step imprimeParImparStep(ItemReader<Integer> reader, ItemProcessor<Integer, String> processor, ItemWriter<String> writer) {
		return stepBuilderFactory.get("imprimeParImparStep")
				.<Integer, String>chunk(10)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}

}
