package com.curso.migracaodadosjob.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import com.curso.migracaodadosjob.domain.Pessoa;

@Configuration
public class ArquivoPessoaReaderConfig {

	@Bean
	public FlatFileItemReader<Pessoa> arquivoPessoaReader(){
		return new FlatFileItemReaderBuilder<Pessoa>()
				.name("arquivoPessoaReader")
				.resource(new FileSystemResource("files/pessoas.csv"))
				.delimited()
				.names("nome", "email", "dtNascimento", "idade", "id")
				.addComment("--")//ignora
				.fieldSetMapper(fieldSetMapper())				
				.build();
	}

	private FieldSetMapper<Pessoa> fieldSetMapper() {
		return new FieldSetMapper<Pessoa>() {

			@Override
			public Pessoa mapFieldSet(FieldSet fieldSet) throws BindException {
				Pessoa p = new Pessoa();
				p.setNome(fieldSet.readString("nome"));
				p.setEmail(fieldSet.readString("email"));
				p.setIdade(fieldSet.readInt("idade"));
				p.setId(fieldSet.readInt("id"));
				p.setDtNascimento(fieldSet.readDate("dtNascimento", "yyyy-MM-dd HH:mm:ss"));
				
				return p;
			}
		};
	}
	
}
