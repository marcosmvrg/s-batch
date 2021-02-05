package com.curso.faturacartaocredito.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.curso.faturacartaocredito.dominio.CartaoCredito;
import com.curso.faturacartaocredito.dominio.Cliente;
import com.curso.faturacartaocredito.dominio.Transacao;

@Configuration
public class LerTransacoesReaderConfig {

	@Bean
	public JdbcCursorItemReader<Transacao> lerTransacoesReader(
			@Qualifier("appDataSource") DataSource dataSource){
		
		return new JdbcCursorItemReaderBuilder<Transacao>()
				.name("lerTransacoesReader")
				.dataSource(dataSource)
				.sql("select * from transacao join cartao_credito using (numero_cartao_credito) order by numero_cartao_credito")
				.rowMapper(rowMapperTransacao())				
				.build();
	}

	private RowMapper<Transacao> rowMapperTransacao() {
		return new RowMapper<Transacao>() {
			
			@Override
			public Transacao mapRow(ResultSet rs, int rowNum) throws SQLException {
				CartaoCredito cc = new CartaoCredito();
				cc.setNumeroCartaoCredito(rs.getInt("numero_cartao_credito"));
				Cliente c = new Cliente();
				c.setId(rs.getInt("cliente"));
				cc.setCliente(c);
				
				Transacao t = new Transacao();
				t.setId(rs.getInt("id"));
				t.setCartaoCredito(cc);
				t.setDtTransacao(rs.getDate("data"));
				t.setValor(rs.getBigDecimal("valor"));
				t.setDescricao(rs.getString("descricao"));
				
				return t;
			}
		};
	}
	
}
