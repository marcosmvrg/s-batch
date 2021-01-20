package com.springbatch.contasbancarias.writer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.contasbancarias.dominio.Conta;

@Configuration
public class JdbcContaWriterConfig {
	
	@Bean
	public JdbcBatchItemWriter<Conta> jdbcContaWriter(@Qualifier("appDataSource") DataSource dataSource) {
		
		return new JdbcBatchItemWriterBuilder<Conta>()
				.dataSource(dataSource)
				.sql("insert into conta(tipo, limite, cliente_id) values (?, ?, ?)")
				.itemPreparedStatementSetter(itemPreparedStatementSetter())
				.build();
		
	}

	private ItemPreparedStatementSetter<Conta> itemPreparedStatementSetter() {
		return new ItemPreparedStatementSetter<Conta>() {

			@Override
			public void setValues(Conta item, PreparedStatement ps) throws SQLException {				
				ps.setString(1, item.getTipo().name());
				ps.setDouble(2, item.getLimite());
				ps.setString(3, item.getClienteId());
			}
			
		};
	}
}
