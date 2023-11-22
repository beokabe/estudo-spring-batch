package br.com.udemy.migracaodadosjdbc;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MigracaoDadosJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MigracaoDadosJdbcApplication.class, args);
    }

}
