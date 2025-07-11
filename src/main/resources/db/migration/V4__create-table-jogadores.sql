DROP TABLE jogadores;
CREATE TABLE jogadores(
        id bigint PRIMARY KEY,
        nome VARCHAR(255) NOT NULL,
        time_id bigint,
        numero_camisa int NOT NULL,
        valor_atual DOUBLE PRECISION NOT NULL,
        valor_pago DOUBLE PRECISION NOT NULL,
        tipo_contrato VARCHAR(50) NOT NULL,
        data_nascimento DATE NOT NULL,
        contrato_inicio DATE NOT NULL,
        contrato_fim DATE,
        nacionalidade_id bigint NOT NULL,
        CONSTRAINT fk_nacionalidade FOREIGN KEY (nacionalidade_id) REFERENCES nacionalidades(id),
        CONSTRAINT fk_time FOREIGN KEY (time_id) REFERENCES times(id)
);