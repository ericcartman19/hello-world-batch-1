package io.valoyes.spring1.batch.impl;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class HolaMundoTaskletImpl implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		System.out.println("Hola mundo con batch utilizando interface implementation");
		return RepeatStatus.FINISHED;
	}

}
