package br.com.udemy.parimparjob.domain.imprimeParImpar.steps.impl;

import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ImprimeParImparStepReader {
    public IteratorItemReader<Integer> contaAteDezReader()  {
        List<Integer> numeroDeUmAteDez = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        return new IteratorItemReader<Integer>(numeroDeUmAteDez.iterator());
    }
}
