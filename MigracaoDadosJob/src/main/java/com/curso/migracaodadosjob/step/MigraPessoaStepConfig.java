package com.curso.migracaodadosjob.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.migracaodadosjob.domain.Pessoa;

@Configuration
public class MigraPessoaStepConfig {

	 @Autowired
	 private StepBuilderFactory stepBuilderFactory;
	 
	 @Bean
	 public Step migraPessoaStep(
			 ItemReader<Pessoa> arquivoPessoaReader, 
			 ClassifierCompositeItemWriter<Pessoa> pessoaClassifierWriter,
			 FlatFileItemWriter<Pessoa> arquivoPessoaInvalidaWriter) {
		 
		 return stepBuilderFactory
				 .get("migraPessoaStep")
				 .<Pessoa, Pessoa> chunk(1)
				 .reader(arquivoPessoaReader)
				 .writer(pessoaClassifierWriter)
				 .stream(arquivoPessoaInvalidaWriter)
				 .build();
				 
	 }
	
}
