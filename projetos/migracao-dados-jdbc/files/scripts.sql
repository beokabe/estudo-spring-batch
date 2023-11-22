CREATE DATABASE migracao_dados OWNER postgres;

-- Criar tabela pessoa
DROP TABLE IF EXISTS pessoa;
CREATE TABLE pessoa (
                        id SERIAL PRIMARY KEY,
                        nome VARCHAR(500),
                        email VARCHAR(500),
                        data_nascimento TIMESTAMP,
                        idade INT
);

-- Criar tabela dados_bancarios
DROP TABLE IF EXISTS dados_bancarios;
CREATE TABLE dados_bancarios (
                                 id SERIAL PRIMARY KEY,
                                 pessoa_id INT,
                                 agencia INT,
                                 conta INT,
                                 banco INT,
                                 FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);
