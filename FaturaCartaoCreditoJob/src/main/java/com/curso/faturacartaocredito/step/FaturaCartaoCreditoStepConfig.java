package com.curso.faturacartaocredito.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.faturacartaocredito.dominio.FaturaCartaoCredito;
import com.curso.faturacartaocredito.dominio.Transacao;
import com.curso.faturacartaocredito.reader.FaturaCartaoCreditoReader;
import com.curso.faturacartaocredito.writer.TotalTransacoesFooterCallback;

@Configuration
public class FaturaCartaoCreditoStepConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step faturaCartaoCreditoStep(
			ItemStreamReader<Transacao> lerTransacoesReader,
			ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> carregaDadoClienteProcessor,
			ItemWriter<FaturaCartaoCredito> arquivosFaturaCartaoCredito,
			TotalTransacoesFooterCallback listener) {
		
		return stepBuilderFactory
				.get("faturaCartaoCreditoStep")
				.<FaturaCartaoCredito, FaturaCartaoCredito>chunk(1)
				.reader(new FaturaCartaoCreditoReader(lerTransacoesReader))
				.processor(carregaDadoClienteProcessor)
				.writer(arquivosFaturaCartaoCredito)
				.listener(listener)
				.build();
	}
}
