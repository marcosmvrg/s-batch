package com.curso.faturacartaocredito.writer;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.ResourceSuffixCreator;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.curso.faturacartaocredito.dominio.FaturaCartaoCredito;

@Configuration
public class ArquivoFaturaCartaoCreditoWriterConfig {

	@Bean
	public MultiResourceItemWriter<FaturaCartaoCredito> arquivosFaturaCartaoCredito(){
		return new MultiResourceItemWriterBuilder<FaturaCartaoCredito>()
				.name("arquivosFaturaCartaoCredito")
				.resource(new FileSystemResource("files/fatura"))
				.itemCountLimitPerResource(1)
				.resourceSuffixCreator(suffixCreator())
				.delegate(arquivoFaturaCartaoCredito())
				.build();
	}

	private FlatFileItemWriter<FaturaCartaoCredito> arquivoFaturaCartaoCredito() {
		return new FlatFileItemWriterBuilder<FaturaCartaoCredito>()
				.name("arquivoFaturaCartaoCredito")
				.resource(new FileSystemResource("files/fatura.txt"))
				.lineAggregator(lineAggregator())
				.headerCallback(header())
				.footerCallback(footer())
				.build();
	}
	
	@Bean
	public FlatFileFooterCallback footer() {
		return new TotalTransacoesFooterCallback();
	}

	
	private FlatFileHeaderCallback header() {
		return new FlatFileHeaderCallback() {
			
			@Override
			public void writeHeader(Writer writer) throws IOException {
				writer.append(String.format("%121s\n", "Cartão Tudo Master"));
				writer.append(String.format("%121s\n\n", "Rua Alameda dos Anjos 1221"));
				
			}
		};
	}

	private LineAggregator<FaturaCartaoCredito> lineAggregator() {
		return new LineAggregator<FaturaCartaoCredito>() {

			@Override
			public String aggregate(FaturaCartaoCredito item) {
				StringBuilder write = new StringBuilder();
				write.append(String.format("Nome: %s\n", item.getCliente().getNome()));
				write.append(String.format("Endereço: %s\n\n\n ", item.getCliente().getEndereco()));
				write.append(String.format("Fatura completa do cartão: %d\n", item.getCartaoCredito().getNumeroCartaoCredito()));
				write.append("------------------------------------------------------------------------------------------------\n");
				write.append("DATA DESCRICAO VALOR\n");
				write.append("------------------------------------------------------------------------------------------------\n");
				
				item.getTransacoes().forEach(t -> {
					write.append(String.format("\n[%10s] %-80s - %s", 
							new SimpleDateFormat("dd/mm/yyyy").format(t.getDtTransacao()),
							t.getDescricao(),
							NumberFormat.getCurrencyInstance().format(t.getValor())));
				}); 
				
				return write.toString();
			}
		};
	}

	private ResourceSuffixCreator suffixCreator() {
		return new ResourceSuffixCreator() {
			
			@Override
			public String getSuffix(int index) {
				return index + ".txt";
			}
		};
	}
	
}
