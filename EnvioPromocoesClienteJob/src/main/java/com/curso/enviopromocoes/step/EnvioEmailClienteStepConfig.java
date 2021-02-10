package com.curso.enviopromocoes.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

import com.curso.enviopromocoes.domain.InteresseProdutoCliente;

@Configuration
public class EnvioEmailClienteStepConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step envioEmailClienteStep(ItemReader<InteresseProdutoCliente> leInteresseProdutoClienteReader,
			ItemProcessor<InteresseProdutoCliente, SimpleMailMessage> processaEmailProdutoClienteProcessor,
			ItemWriter<SimpleMailMessage> enviaEmailProdutoClienteWriter) {
		
		return stepBuilderFactory
				.get("envioEmailClienteStep")
				.<InteresseProdutoCliente, SimpleMailMessage>chunk(1)
				.reader(leInteresseProdutoClienteReader)
				.processor(processaEmailProdutoClienteProcessor)
				.writer(enviaEmailProdutoClienteWriter)
				.build();
	}
}
