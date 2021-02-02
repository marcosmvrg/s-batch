package com.curso.migracaodadosjob.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.migracaodadosjob.domain.DadoBancario;

@Configuration
public class BancoDadoBancarioWriterConfig {

	@Bean
	public JdbcBatchItemWriter<DadoBancario> bancoDadoBancarioWriter(
			@Qualifier("appDataSource") DataSource dataSource){
		
		return new JdbcBatchItemWriterBuilder<DadoBancario>()
				.dataSource(dataSource)
				.sql("insert into dados_bancarios(id, pessoa_id, agencia, conta, banco) "
						+ "values(:id, :pessoaId, :agencia, :conta, :banco)")
				.beanMapped()
				.build();
	}

	
	
}
