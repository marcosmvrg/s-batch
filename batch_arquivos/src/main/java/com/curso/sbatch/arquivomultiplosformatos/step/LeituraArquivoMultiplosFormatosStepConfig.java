package com.curso.sbatch.arquivomultiplosformatos.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.sbatch.arquivomultiplosformatos.reader.ArquivoClienteTransacaoReader;

@Configuration
public class LeituraArquivoMultiplosFormatosStepConfig {
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public Step leituraArquivoMultiplosFormatosStep(
			FlatFileItemReader arquivoMultiplosFormatosItemReader,
			ItemWriter leituraArquivoMultiplosFormatosWriter) {
		return stepBuilderFactory
				.get("leituraArquivoMultiplosFormatosStep")
				.chunk(1)
				.reader(new ArquivoClienteTransacaoReader(arquivoMultiplosFormatosItemReader))
				.writer(leituraArquivoMultiplosFormatosWriter)
				.build();
	}
}

