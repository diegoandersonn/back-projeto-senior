CREATE TABLE players (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    team_id UUID NOT NULL,
    nationality_id UUID NOT NULL,
    shirt_number INTEGER NOT NULL,
    current_value DOUBLE PRECISION NOT NULL,
    paid_value DOUBLE PRECISION NOT NULL,
    contract_type VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    contract_start DATE NOT NULL,
    contract_end DATE NOT NULL,
    height DOUBLE PRECISION NOT NULL,
    salary DOUBLE PRECISION NOT NULL,
    position VARCHAR(255) NOT NULL,
    CONSTRAINT fk_team FOREIGN KEY (team_id) REFERENCES teams(id),
    CONSTRAINT fk_nationality FOREIGN KEY (nationality_id) REFERENCES nationalities(id)
);