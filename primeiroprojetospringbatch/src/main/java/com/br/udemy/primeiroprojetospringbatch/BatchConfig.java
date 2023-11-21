package com.br.udemy.primeiroprojetospringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /** O uso do Incrementer não é recomendado em ambientes de produção, pois interrompe e reinicializa um job a partir do zero.
    Isso pode ser indesejável no ambiente de produção, já que o progresso de execução do job é perdido.**/

    @Bean
    public Job imprimeHelloWorldJob() {
        return jobBuilderFactory
                .get("imprimeHelloWorldJob")
                .start(imprimeHelloWorldStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    private Step imprimeHelloWorldStep() {
        return stepBuilderFactory
                .get("imprimeHelloWorldStep")
                .tasklet(helloWorldTasklet(null))
                .build();
    }


    @Bean
    @StepScope
    public Tasklet helloWorldTasklet(@Value("#{jobParameters['nome']}") String nome) {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                //O nome em jobParameters é o argumento capturado em tempo de execução na aplicação.
                // Para ter acesso ao jobParameters, o método precisa ser reconhecido como um componente do Spring Batch e precisa ser reconhecido dentro do contexto da aplicação.
                System.out.printf("Hello %s!%n", nome);
                return RepeatStatus.FINISHED;
            }
        };
    }
}
