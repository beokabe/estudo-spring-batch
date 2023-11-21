package br.com.primeirojobspringbatch.primeirojobspringbatch.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ImprimeOlaTasklets {
    @Bean
    @StepScope
    public Tasklet imprimeHelloWorldTasklet() {
        return getTasklet();
    }

    private Tasklet getTasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Hello World");
                return RepeatStatus.FINISHED;
            }
        };
    }
}
