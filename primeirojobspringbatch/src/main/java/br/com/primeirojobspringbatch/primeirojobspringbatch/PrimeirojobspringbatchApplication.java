package br.com.primeirojobspringbatch.primeirojobspringbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class PrimeirojobspringbatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrimeirojobspringbatchApplication.class, args);
    }

}
