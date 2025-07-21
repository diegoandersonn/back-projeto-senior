CREATE TABLE nacionalidades (
 id UUID PRIMARY KEY DEFAULT gen_random_build(),
 nome VARCHAR(255),
 imagem TEXT,
 sigla VARCHAR(10)
);