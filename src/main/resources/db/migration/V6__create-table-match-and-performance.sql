CREATE TABLE matches (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    date DATE NOT NULL,
    team_id UUID NOT NULL,
    CONSTRAINT fk_team_id FOREIGN KEY (team_id) REFERENCES teams(id)
);

CREATE TABLE performances (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nota DOUBLE PRECISION NOT NULL,
    match_id UUID NOT NULL,
    player_id UUID NOT NULL,
    CONSTRAINT fk_match_id FOREIGN KEY (match_id) REFERENCES matches(id),
    CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES players(id)
);