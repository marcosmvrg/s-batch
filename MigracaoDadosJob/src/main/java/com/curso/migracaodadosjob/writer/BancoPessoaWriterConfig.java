package com.curso.migracaodadosjob.writer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.migracaodadosjob.domain.Pessoa;

@Configuration
public class BancoPessoaWriterConfig {

	@Bean
	public JdbcBatchItemWriter<Pessoa> bancoPessoaWriter(
			@Qualifier("appDataSource") DataSource dataSource){
		
		return new JdbcBatchItemWriterBuilder<Pessoa>()
				.dataSource(dataSource)
				.sql("insert into pessoa(id, nome, email, data_nascimento, idade) values(?,?,?,?,?)")
				.itemPreparedStatementSetter(itemPreparedStatementSetter())
				.build();
	}

	private ItemPreparedStatementSetter<Pessoa> itemPreparedStatementSetter() {
		return new ItemPreparedStatementSetter<Pessoa>() {

			@Override
			public void setValues(Pessoa p, PreparedStatement ps) throws SQLException {			
				ps.setInt(1, p.getId());
				ps.setString(2, p.getNome());
				ps.setString(3, p.getEmail());
				ps.setDate(4, new Date(p.getDtNascimento().getTime()));
				ps.setInt(5, p.getIdade());				
			}
		};
	}
	
}
