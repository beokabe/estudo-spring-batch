package br.com.udemy.parimparjob.domain.imprimeParImpar.steps.impl;


import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ImprimeParImparStepProcessor {
    public FunctionItemProcessor<Integer, String> parOuImparProcessor() {
        return new FunctionItemProcessor<Integer, String>(item -> item % 2 == 0 ? String.format("Item %s é par", item) : String.format("Item %s é impar", item));
    }
}
