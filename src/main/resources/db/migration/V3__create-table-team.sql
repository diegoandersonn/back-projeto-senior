CREATE TABLE teams (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    stadium VARCHAR(255) NOT NULL,
    transfer_balance DOUBLE PRECISION NOT NULL,
    nationality_id UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_nationality FOREIGN KEY (nationality_id) REFERENCES nationalities(id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);
