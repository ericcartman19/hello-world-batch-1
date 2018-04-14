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
	public Step step4_withLamdaToo() {
		return stepBuilderFactory.get("step4_withLamdaToo")
				.tasklet( (stepContribution, chunkContext) -> {
					System.out.println("Another step with Lamda too");
					return RepeatStatus.FINISHED;
				}).build();
	}
	
//	@Bean
//	public Job holaMundo() {
//		return jobBuilderFactory.get("holaMundo")
//				.start(step3_withLambda())
//				.build();
//	}
	
//	@Bean
//	public Job transitionJobSimpleNext() {
//		return jobBuilderFactory.get("transitionJobSimpleNext")
//				.start(step1())
//				.next(step2_withInterfaceImplementation())
//				.next(step3_withLambda())
//				.next(step4_withLamdaToo())
//				.build();
//	}
	
	@Bean
	public Job transitionJobSimpleNextExplicit() {
		return jobBuilderFactory.get("transitionJobSimpleNextExplicit")
				.start(step1())
				.on("COMPLETED").to(step2_withInterfaceImplementation())
				.from(step2_withInterfaceImplementation()).on("COMPLETED").to(step3_withLambda())
				.from(step3_withLambda()).on("COMPLETED").to(step4_withLamdaToo())
				.from(step4_withLamdaToo()).end()
				.build();
				
	}

}
