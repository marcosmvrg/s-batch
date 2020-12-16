package com.curso.sbatch.primeiro.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component(value = "imprimeOlaTasklet")
public class ImprimeOlaTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		System.out.println(String.format("Olá %s!", "Marcos"));
		return RepeatStatus.FINISHED;

	}

}
