-- Criação de um usuário administrador
CREATE USER postgres WITH PASSWORD 'postgres';
ALTER USER postgres WITH SUPERUSER;

-- Criação de um banco de dados
CREATE DATABASE spring_batch OWNER postgres;
CREATE DATABASE parimparjob OWNER postgres;
CREATE DATABASE app OWNER postgres;
CREATE DATABASE xpto OWNER postgres;
CREATE DATABASE migracao_dados OWNER postgres;
