package br.com.primeirojobspringbatch.primeirojobspringbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job imprimeHelloWorldJob(Step imprimeOlaStep) {
        return jobBuilderFactory
                .get("imprimeHelloWorldJob")
                .start(imprimeOlaStep)
                .build();
    }
}
