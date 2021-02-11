package com.curso.enviopromocoes.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.curso.enviopromocoes.domain.Cliente;
import com.curso.enviopromocoes.domain.InteresseProdutoCliente;
import com.curso.enviopromocoes.domain.Produto;

@Configuration
public class JdbcInteresseProdutoClienteReaderConfig {
	
	private static final String SQL_INTERESSE_CLIENTE = getSqlInteresseProduto();

	@Bean
	public JdbcCursorItemReader<InteresseProdutoCliente> leInteresseProdutoClienteReader(
			@Qualifier("appDataSource") DataSource dataSource) {
		
		return new JdbcCursorItemReaderBuilder<InteresseProdutoCliente>()
				.name("leInteresseProdutoClienteReader")
				.dataSource(dataSource)
				.sql(SQL_INTERESSE_CLIENTE)
				.rowMapper(rowMapper())
				.build();
	}
	
	private RowMapper<InteresseProdutoCliente> rowMapper() {
		return new RowMapper<InteresseProdutoCliente>() {

			@Override
			public InteresseProdutoCliente mapRow(ResultSet rs, int rowNum) throws SQLException {
				InteresseProdutoCliente obj = new InteresseProdutoCliente();
				obj.setCliente(new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("email")));
				obj.setProduto(new Produto(rs.getInt(6), rs.getString(7), rs.getString("descricao"), rs.getDouble("preco")));
				
				return obj;
			}
		};
	}

	private static String getSqlInteresseProduto() {
		return new StringBuilder("select * from p_interesse_produto_cliente ")
				.append("join p_cliente on (cliente = p_cliente.id) ")
				.append("join p_produto on (produto = p_produto.id) ")
				.toString();
	}

}
