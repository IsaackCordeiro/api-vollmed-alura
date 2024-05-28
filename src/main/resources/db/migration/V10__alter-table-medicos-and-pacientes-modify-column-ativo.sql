ALTER TABLE medicos ALTER COLUMN ativo BOOLEAN;
UPDATE medicos SET ativo = true;

ALTER TABLE pacientes ALTER COLUMN ativo BOOLEAN;
UPDATE pacientes SET ativo = true;