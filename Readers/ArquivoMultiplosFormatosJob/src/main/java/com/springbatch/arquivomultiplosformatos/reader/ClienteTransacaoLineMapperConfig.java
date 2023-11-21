package com.springbatch.arquivomultiplosformatos.reader;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;
import com.springbatch.arquivomultiplosformatos.dominio.Transacao;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ClienteTransacaoLineMapperConfig {

    @SuppressWarnings({ "rawtypes", "unchecked"})
    @Bean
    public PatternMatchingCompositeLineMapper lineMapper() {
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper<>();
        lineMapper.setTokenizers(tokenizers()); //dividem as linhas em palavra
        lineMapper.setFieldSetMappers(fieldSetMappers()); //recebe as palavras e as mapeia para um objeto de domínio
        return lineMapper;
    }

    private Map<String, LineTokenizer> tokenizers() {
        Map<String, LineTokenizer> tokenizers = new HashMap<>();
        tokenizers.put("0*", clienteLineTokenizer()); // Chave '0' para linhas de clientes
        tokenizers.put("1*", transacaoLineTokenizer()); // Chave '1' para linhas de transações
        return tokenizers;
    }

    private LineTokenizer clienteLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("nome", "sobrenome", "idade", "email");
        lineTokenizer.setIncludedFields(1, 2, 3, 4); // Ajuste dos índices para incluir todos os campos mapeados
        return lineTokenizer;
    }

    private LineTokenizer transacaoLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("id", "descricao", "valor");
        lineTokenizer.setIncludedFields(1, 2, 3);
        return lineTokenizer;
    }
    @SuppressWarnings({ "rawtypes", "unchecked"})
    private Map<String, FieldSetMapper> fieldSetMappers() {
        Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>();
        fieldSetMappers.put("0*", fieldSetMapper(Cliente.class));
        fieldSetMappers.put("1*", fieldSetMapper(Transacao.class));
        return fieldSetMappers;
    }

    @SuppressWarnings({ "rawtypes", "unchecked"})
    private FieldSetMapper fieldSetMapper(Class classe) {
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(classe);
        return fieldSetMapper;
    }
}
