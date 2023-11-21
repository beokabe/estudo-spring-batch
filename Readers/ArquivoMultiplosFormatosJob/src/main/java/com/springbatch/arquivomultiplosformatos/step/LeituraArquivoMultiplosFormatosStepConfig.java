package com.springbatch.arquivomultiplosformatos.step;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoMultiplosFormatosStepConfig {
	@Autowired
	public StepBuilderFactory stepBuilderFactory;


	/*Esse Step funcionará apenas com o argumento
	arquivoClientes=file:ArquivoMultiplosFormatosJob/files/clientes.txt
	pois ele ainda não lê multiplos arquivos */

//	@SuppressWarnings({ "rawtypes", "unchecked"})
//	@Bean
//	public Step leituraArquivoMultiplosFormatosStep(
//			FlatFileItemReader leituraArquivoMultiplosFormatosReader,
//			ItemWriter leituraArquivoMultiplosFormatosItemWriter) {
//		return stepBuilderFactory
//				.get("leituraArquivoMultiplosFormatosStep")
//				.chunk(1)
////				.reader(leituraArquivoMultiplosFormatosReader)
//				.reader(new ArquivoClienteTransacaoReader(leituraArquivoMultiplosFormatosReader))
//				.writer(leituraArquivoMultiplosFormatosItemWriter)
//				.build();
//	}




	/* Esse Step funcionará apenas com o argumento
	arquivosClientes=file:ArquivoMultiplosFormatosJob/files/clientes*
	pois ele deve ler tudo que começar com a palavra "clientes" */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Bean
	public Step leituraArquivoMultiplosFormatosStep(
			MultiResourceItemReader<Cliente> multiplosArquivosClienteTransacaoReader,
			ItemWriter leituraArquivoMultiplosFormatosItemWriter) {
		return stepBuilderFactory
				.get("leituraArquivoMultiplosFormatosStep")
				.chunk(1)
				.reader(multiplosArquivosClienteTransacaoReader)
				.writer(leituraArquivoMultiplosFormatosItemWriter)
				.build();
	}
}
