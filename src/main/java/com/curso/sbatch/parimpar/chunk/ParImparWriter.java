package com.curso.sbatch.parimpar.chunk;

import org.springframework.batch.item.ItemWriter;

public class ParImparWriter {

	public ItemWriter<String> imprimeSaida() {
		return itens -> itens.forEach(System.out::println);
	}

}
