package br.com.udemy.parimparjob.domain.imprimeParImpar;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeParImparJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job criarImprimeParImparJob(Step imprimeParImparJob) {
        return jobBuilderFactory.get("imprimeParImparJob")
                .start(imprimeParImparJob).build();
    }
}
