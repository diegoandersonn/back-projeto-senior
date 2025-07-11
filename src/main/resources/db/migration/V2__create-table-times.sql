CREATE TABLE times(
    id UUID PRIMARY KEY,
    nome VARCHAR(255),
    estadio VARCHAR(255),
    saldo_transferencia DOUBLE PRECISION,
    nacionalidade_id UUID,
    CONSTRAINT fk_nacionalidade FOREIGN KEY (nacionalidade_id) REFERENCES nacionalidades(id)
);
