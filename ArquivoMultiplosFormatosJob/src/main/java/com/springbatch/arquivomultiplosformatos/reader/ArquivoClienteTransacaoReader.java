package com.springbatch.arquivomultiplosformatos.reader;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;
import com.springbatch.arquivomultiplosformatos.dominio.Transacao;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

public class ArquivoClienteTransacaoReader implements ItemStreamReader<Cliente>, ResourceAwareItemReaderItemStream<Cliente> {

    /**
     * Esta classe personaliza a leitura de dados no contexto de um job Spring Batch.
     * Mesmo que a leitura seja configurada para processar 10 objetos, a arquitetura
     * implementada faz com que o contador `read_count` da tabela batch_step_execution
     * seja limitado a 3. Isso ocorre porque a classe está configurada para ler
     * apenas objetos da classe `Clientes`, ignorando completamente a classe `Transacao`.
     * <p>
     * O motivo para essa limitação está na natureza do arquivo de entrada, localizado
     * em "/files/clientes.txt" no projeto. Este arquivo contém apenas 3 entradas que
     * serão mapeadas para objetos da classe `Clientes`. O restante do conteúdo do
     * arquivo consiste em transações, e essas transações são agregadas aos clientes mapeados.
     */

    private Object objAtual;

    //delegate é um objeto com várias operações são delegadas a ele
    //private ItemStreamReader<Object> delegate;
    private FlatFileItemReader<Object> delegate;

    public ArquivoClienteTransacaoReader(FlatFileItemReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Cliente read() throws Exception {
        if (objAtual == null) //se o objeto ainda não foi lido
            objAtual = delegate.read();

        Cliente cliente = (Cliente) objAtual;
        objAtual = null; //zerar para ser lido novamente

        if (cliente != null) {
            while (peek() instanceof Transacao) //Se o cliente for do tipo transação ela é adicionado no objeto Cliente
                cliente.getTransacoes().add((Transacao) objAtual);
        }
        //se não for transação, retorna o cliente
        return cliente;

    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }

    private Object peek() throws Exception {
        objAtual = delegate.read(); //leitura do próximo item
        return objAtual;
    }

    @Override
    public void setResource(Resource resource) {
        delegate.setResource(resource);
    }
}
