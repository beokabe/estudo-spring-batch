package br.com.udemy.migracaodadosjdbc.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DadosBancarios {
    private int id;
    private int pessoaId;
    private int agencia;
    private int conta;
    private int banco;
}
