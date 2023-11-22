package br.com.udemy.parimparjob.domain.imprimeParImpar.steps;

import br.com.udemy.parimparjob.domain.imprimeParImpar.steps.impl.ImprimeParImparStepProcessor;
import br.com.udemy.parimparjob.domain.imprimeParImpar.steps.impl.ImprimeParImparStepReader;
import br.com.udemy.parimparjob.domain.imprimeParImpar.steps.impl.ImprimeParImparStepWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeParImparStep {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ImprimeParImparStepReader imprimeParImparStepReader;

    @Autowired
    private ImprimeParImparStepProcessor imprimeParImparStepProcessor;

    @Autowired
    private ImprimeParImparStepWriter imprimeParImparStepWriter;

    @Bean
    public Step criarImprimeParImparStep() {
        return stepBuilderFactory.get("imprimeParImparStepImpl")
                .<Integer, String>chunk(10)
                .reader(imprimeParImparStepReader.contaAteDezReader())
                .processor(imprimeParImparStepProcessor.parOuImparProcessor())
                .writer(imprimeParImparStepWriter.imprimeWriter())
                .build();
    }
}
