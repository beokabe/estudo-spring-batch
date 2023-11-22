package br.com.udemy.parimparjob;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class ParimparjobApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParimparjobApplication.class, args);
    }

}
