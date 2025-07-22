CREATE TABLE times(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255),
    estadio VARCHAR(255),
    saldo_transferencia DOUBLE PRECISION,
    nacionalidade_id UUID,
    CONSTRAINT fk_nacionalidade FOREIGN KEY (nacionalidade_id) REFERENCES nacionalidades(id)
);
