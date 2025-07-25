CREATE TABLE nationalities (
 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
 name VARCHAR(255) NOT NULL,
 image TEXT NOT NULL,
 acronym VARCHAR(10) NOT NULL
);