package br.com.udemy.parimparjob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

import java.util.Arrays;

/** Existem dois tipos para Steps: os que executam tarefas simples (Tasklet)
 e os complexos chamados de Chunk, que terão etapas de leitura (ItemReader), processamento (ItemProcessor, é opcional) e escrita de dados (ItemWriter). **/
@Configuration
public class ParImparBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /** Realiza 11 transações (commit count) para completar o job. Para descobrir isso, acesse a tabela "batch_step_execution".
    Para reduzir o custo em realizar transações, deve ser aumentado o tamanho dos chunks (cuidado com o tamanho, pois os dados em processamento ficam em memória de execução).
    É interessante escolher o valor analisando a infraestrutura que vai executar o Job. **/
    @Bean
    public Job imprimeParImparJob() {
        return jobBuilderFactory.get("imprimeParImparJob").start(imprimeParImparStep()).incrementer(new RunIdIncrementer()).build();
    }

    private Step imprimeParImparStep() {
        return stepBuilderFactory
                .get("imprimeParImparStep")
                // O Step Chunk realiza a leitura e escrita de dados em blocos, recebendo um tamanho como parâmetro, que determina a quantidade de dados lidos e escritos.
                .<Integer, String>chunk(1)
                .reader(contaAteDezReader())
                .processor(parOuImparProcessor())
                .writer(imprimeWriter())
                .build();
    }

    //IteratorItemReader é uma classe que implementa a interface ItemReader e retorna um Iterator.
    public IteratorItemReader<Integer> contaAteDezReader()  {

        //O leitor recebe uma coleção.

        List<Integer> numeroDeUmAteDez = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        return new IteratorItemReader<Integer>(numeroDeUmAteDez.iterator());
    }

    //FunctionItemProcessor é uma classe que implementa a interface ItemProcessor e retorna uma função.
    public FunctionItemProcessor<Integer, String> parOuImparProcessor() {

        //O ItemProcessor recebe um item da coleção do leitor de cada vez.

        return new FunctionItemProcessor<Integer, String>(item -> item % 2 == 0 ? String.format("Item %s é par", item) : String.format("Item %s é impar", item));
    }

    public ItemWriter<String> imprimeWriter() {
        //O writer recebe uma coleção de objetos processadas, depois que a etapa ItemProcessor é concluída.
        //O tamanho dessa coleção é o mesmo tamanho do chunk que foi informado ao fabricar a step.
        return itens -> itens.forEach(System.out::println);
    }
}
