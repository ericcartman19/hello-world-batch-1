package io.valoyes.spring1.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.valoyes.spring1.batch.impl.HolaMundoTaskletImpl;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.tasklet(new Tasklet() {
					
					@Override
					public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
						System.out.println("Hola Mundo con batchs");
						return RepeatStatus.FINISHED;
					}
				}).build();
	}
	
	@Bean
	public Step step2_withInterfaceImplementation() {
		return stepBuilderFactory.get("step2_withInterfaceImplementation")
				.tasklet(new HolaMundoTaskletImpl())
				.build();
	}
	
	@Bean
	public Step step3_withLambda() {
		return stepBuilderFactory.get("step3_withLambda")
				.tasklet( (sc, cc) -> {
					System.out.println("Haciendo un step con lamdas!!! Oh yeah babe ;) ");
					return RepeatStatus.FINISHED;
				} ).build();
	}
	
	@Bean
	public Job holaMundo() {
		return jobBuilderFactory.get("holaMundo")
				.start(step3_withLambda())
				.build();
	}

}
