package com.curso.migracaodadosjob.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.curso.migracaodadosjob.domain.Pessoa;

@Configuration
public class ArquivoPessoaInvalidaWriterConfig {

	@Bean
	public FlatFileItemWriter<Pessoa> arquivoPessoaInvalidaWriter(){
		return new FlatFileItemWriterBuilder<Pessoa>()
				.name("arquivoPessoaInvalidaWriter")
				.resource(new FileSystemResource("files/pessoa-invalida.csv"))
				.delimited()
				.names("id")
				.build();
				
	}
	
}
