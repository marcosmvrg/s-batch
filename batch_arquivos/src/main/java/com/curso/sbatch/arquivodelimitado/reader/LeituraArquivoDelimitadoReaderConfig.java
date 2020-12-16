package com.curso.sbatch.arquivodelimitado.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.curso.sbatch.dominio.Cliente;

@Configuration
public class LeituraArquivoDelimitadoReaderConfig {


	@StepScope	
	@Bean
	public FlatFileItemReader<Cliente> leituraArquivoDelimitadoReader(@Value("#{jobParameters['arquivoDelimitado']}") Resource arquivoDelimitado){
		
		return new FlatFileItemReaderBuilder<Cliente>()
				.name("leituraArquivoDelimitadoReader")
				.resource(arquivoDelimitado)
				.delimited()				
				.delimiter(";")
				.names(new String[] {"nome", "sobrenome", "idade", "email"})
				.targetType(Cliente.class)				
				.build();		
	}
}
