ALTER TABLE times
ADD COLUMN usuario_id UUID;

ALTER TABLE times
ADD CONSTRAINT fk_usuario_id
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id);