package com.springbatch.arquivolargurafixa.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.arquivolargurafixa.dominio.Cliente;

@Configuration
public class LeituraArquivoLarguraFixaWriterConfig {

    @Bean
    public ItemWriter<Cliente> leituraArquivoLarguraFixaWriter() {
        return items -> items.forEach(System.out::println);
    }

    /**Nesse exemplo está forçando o lançamento de um erro. Quando o cliente com nome "Maria" é detectado, a leitura é interrompida. É possível olhar
     * o ponteiro que salva onde parou o Step na tabela batch_step_execution_context. Caso a Maria faça parte de um chunk maior do que 2, o chunk inteiro será reinicializado ao
     * executar novamente a operação **/
//	@Bean
//	public ItemWriter<Cliente> leituraArquivoLarguraFixaWriter() {
//		return items -> items.forEach(System.out::println);
//		return items -> {
//			for (Cliente cliente : items) {
//				if (cliente.getNome().equals("Maria"))
//					throw new Exception();
//				else
//					System.out.println(cliente);
//			}
//		};
//	}
}
