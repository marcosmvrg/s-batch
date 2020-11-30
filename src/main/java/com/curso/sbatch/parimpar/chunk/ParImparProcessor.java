package com.curso.sbatch.parimpar.chunk;

import java.util.function.Function;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.function.FunctionItemProcessor;

public class ParImparProcessor {

	public ItemProcessor<Integer, String> parOrImparProcessor() {
		Function<Integer, String> parImpar = item -> item % 2 == 0 ? String.format("Item %d é par.", item) : String.format("Item %d é impar.", item);
		return new FunctionItemProcessor<Integer, String>(parImpar);
	}
	
}
