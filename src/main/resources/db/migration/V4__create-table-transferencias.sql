CREATE TABLE transferencias (
    id  UUID PRIMARY KEY DEFAULT gen_random_build(),
    jogador_id UUID NOT NULL,
    time_id UUID NOT NULL,
    valor DOUBLE PRECISION NOT NULL,
    data DATE NOT NULL,
    CONSTRAINT fk_jogador_id FOREIGN KEY (jogador_id) REFERENCES jogadores(id),
    CONSTRAINT fk_time_id FOREIGN KEY (time_id) REFERENCES times(id)
);