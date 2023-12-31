package br.com.udemy.migracaodadosjdbc.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@EnableBatchProcessing
@Configuration
public class MigracaoDadosJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job migracaoDadosJob(
            @Qualifier("migrarPessoasStep") Step migrarPessoasStep,
            @Qualifier("migrarDadosBancariosStep") Step migrarDadosBancariosStep
    ) {
        return jobBuilderFactory
                .get("migracaoDadosJob")

                /* starta primeiro o step "migrarPessoaStep"
                para executar depois o "migrarDadosBancarios",
                não é executado paralelamente */

//                .start(migrarPessoasStep)
//                .next(migrarDadosBancariosStep)


                .start(stepsParalelos(migrarPessoasStep, migrarDadosBancariosStep))
                .end()
                .incrementer(new RunIdIncrementer())
                .build();
    }

    private Flow stepsParalelos(Step migrarPessoasStep, Step migrarDadosBancariosStep) {
        Flow migrarDadosBancariosFlow = new FlowBuilder<Flow>("migrarDadosBancariosFlow")
                .start(migrarDadosBancariosStep)
                .build();

        return new FlowBuilder<Flow>("stepsParalelosFlow")
                .start(migrarPessoasStep)
                .split(new SimpleAsyncTaskExecutor())
                .add(migrarDadosBancariosFlow)
                .build();
    }
}
