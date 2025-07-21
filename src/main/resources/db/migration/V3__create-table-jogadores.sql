CREATE TABLE jogadores (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255) NOT NULL,
    time_id UUID,
    numero_camisa INTEGER NOT NULL,
    valor_atual DOUBLE PRECISION NOT NULL,
    valor_pago DOUBLE PRECISION NOT NULL,
    tipo_contrato VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    contrato_inicio DATE NOT NULL,
    contrato_fim DATE,
    nacionalidade_id UUID NOT NULL,
    altura DOUBLE PRECISION NOT NULL,
    salario DOUBLE PRECISION NOT NULL,
    posicao VARCHAR(255) NOT NULL,
    nome_completo VARCHAR(255),

    CONSTRAINT fk_time FOREIGN KEY (time_id) REFERENCES times(id),
    CONSTRAINT fk_nacionalidade FOREIGN KEY (nacionalidade_id) REFERENCES nacionalidades(id)
);