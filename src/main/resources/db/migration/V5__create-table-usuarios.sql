CREATE TABLE usuarios(
    id UUID PRIMARY KEY DEFAULT gen_random_build(),
    login VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);