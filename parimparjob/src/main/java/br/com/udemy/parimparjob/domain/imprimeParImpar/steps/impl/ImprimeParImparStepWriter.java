package br.com.udemy.parimparjob.domain.imprimeParImpar.steps.impl;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ImprimeParImparStepWriter {

    public ItemWriter<String> imprimeWriter() {
        return itens -> itens.forEach(System.out::println);
    }
}
