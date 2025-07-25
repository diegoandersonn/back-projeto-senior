CREATE TABLE transfers (
    id  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    player_id UUID NOT NULL,
    team_id UUID NOT NULL,
    value DOUBLE PRECISION NOT NULL,
    date DATE NOT NULL,
    transfer_type VARCHAR(55) NOT NULL,
    CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES players(id),
    CONSTRAINT fk_team_id FOREIGN KEY (team_id) REFERENCES teams(id)
);