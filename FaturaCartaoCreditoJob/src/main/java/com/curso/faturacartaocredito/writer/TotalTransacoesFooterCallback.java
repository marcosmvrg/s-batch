package com.curso.faturacartaocredito.writer;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.List;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.file.FlatFileFooterCallback;

import com.curso.faturacartaocredito.dominio.FaturaCartaoCredito;

public class TotalTransacoesFooterCallback implements FlatFileFooterCallback {

	private Double total = 0.0D;
		
	@Override
	public void writeFooter(Writer writer) throws IOException {
		writer.write(String.format("\n%121s", "Total: "+NumberFormat.getCurrencyInstance().format(total)));
	}
	
	@BeforeWrite
	public void beforeWrite(List<FaturaCartaoCredito> faturas) {
		total = faturas.stream().mapToDouble(FaturaCartaoCredito::getTotal).reduce(0.0, Double::sum);
	}
	
	@AfterChunk
	public void afterChunck(ChunkContext context) {
		total = 0.0D;
	}

}
