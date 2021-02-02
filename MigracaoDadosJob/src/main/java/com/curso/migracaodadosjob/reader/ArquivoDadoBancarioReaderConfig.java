package com.curso.migracaodadosjob.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.curso.migracaodadosjob.domain.DadoBancario;

@Configuration
public class ArquivoDadoBancarioReaderConfig {

	@Bean
	public FlatFileItemReader<DadoBancario> dadoBancarioReader(){
		return new FlatFileItemReaderBuilder<DadoBancario>()
				.name("dadoBancarioReader")
				.resource(new FileSystemResource("files/dados_bancarios.csv"))
				.delimited()
				.names("pessoaId", "agencia", "conta", "banco", "id")
				.addComment("--")//ignora
				.targetType(DadoBancario.class)
				.build();
	}
	
}
