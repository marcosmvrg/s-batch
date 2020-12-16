package com.curso.sbatch.parimpar.chunk;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;

public class ParImparReader {

	public ItemReader<Integer> contaReader() {
		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		return new IteratorItemReader<Integer>(numeros.iterator());
	}
}
