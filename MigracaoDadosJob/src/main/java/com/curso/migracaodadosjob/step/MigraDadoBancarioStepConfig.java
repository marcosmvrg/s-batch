package com.curso.migracaodadosjob.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.migracaodadosjob.domain.DadoBancario;

@Configuration
public class MigraDadoBancarioStepConfig {

	 @Autowired
	 private StepBuilderFactory stepBuilderFactory;
	 
	 @Bean
	 public Step migraDadoBancarioStep(
			 ItemReader<DadoBancario> arquivoDadoBancarioReader, 
			 ItemWriter<DadoBancario> bancoDadoBancarioWriter) {
		 
		 return stepBuilderFactory
				 .get("migraDadoBancarioStep")
				 .<DadoBancario, DadoBancario> chunk(1000)
				 .reader(arquivoDadoBancarioReader)
				 .writer(bancoDadoBancarioWriter)
				 .build();
				 
	 }
	
}
