CREATE TABLE usuarios(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    login VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);