package br.com.udemy.migracaodadosjdbc.step;

import br.com.udemy.migracaodadosjdbc.dominio.DadosBancarios;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MigrarDadosBancariosStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step migrarDadosBancariosStep(
            ItemReader<DadosBancarios> dadosBancariosReader,
            ItemWriter<DadosBancarios> bancoDadosBancariosWriter
    ) {
        return stepBuilderFactory.get("migrarDadosBancariosStep")
                .<DadosBancarios, DadosBancarios>chunk(1)
                .reader(dadosBancariosReader)
                .writer(bancoDadosBancariosWriter)
                .build();
    }
}
