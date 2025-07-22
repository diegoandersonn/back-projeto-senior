CREATE TABLE partidas(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    data DATE NOT NULL,
    time_id UUID NOT NULL,
    CONSTRAINT fk_time_id FOREIGN KEY (id) REFERENCES times(id)
);

CREATE TABLE atuacoes(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nota DOUBLE PRECISION NOT NULL,
    partida_id UUID NOT NULL,
    jogador_id UUID NOT NULL,
    CONSTRAINT fk_partida_id FOREIGN KEY (id) REFERENCES partidas(id),
    CONSTRAINT fk_jogador_id FOREIGN KEY (id) REFERENCES jogadores(id)
);