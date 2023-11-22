package com.springbatch.arquivolargurafixa.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.arquivolargurafixa.dominio.Cliente;
import org.springframework.core.io.Resource;


@Configuration
public class ArquivoLarguraFixaWriterConfig {
    @Bean
    @StepScope
    public FlatFileItemWriter<Cliente> escritaArquivoLarguraFixaWriter(
            @Value("#{jobParameters['arquivoClientesSaida']}") Resource arquivoClientesSaida) {
		return new FlatFileItemWriterBuilder<Cliente>()
                .name("escritaArquivoLarguraFixaWriter")
                .resource(arquivoClientesSaida)
                .formatted()
                //se a string não tiver tamanho especificado, ele completa com espaço
                //por padrão o formato sempre acontece do lado ESQUERDO, para que ele reflita para
                // o lado DIREITO do char, precisa ser colocado um "-" antes do número
                .format("%-9s %-9s %-2s %-19s")
                .names(new String[] {"nome", "sobrenome", "idade", "email"})
                .build();
    }

//.columns(new Range[]{new Range(1,10), new Range(11, 20), new Range(21, 23), new Range(24, 43)})
    //10 - 1 = 9, 20-11 = 9, 23 - 21 = 2, 43-24 = 19
}
