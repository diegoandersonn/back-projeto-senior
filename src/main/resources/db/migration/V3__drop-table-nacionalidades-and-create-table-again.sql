DROP TABLE times;
DROP TABLE nacionalidades;

CREATE TABLE nacionalidades (
 id bigint PRIMARY KEY,
 nome VARCHAR(255),
 imagem TEXT,
 sigla VARCHAR(10)
);