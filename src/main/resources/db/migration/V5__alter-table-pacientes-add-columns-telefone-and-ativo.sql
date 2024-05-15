ALTER TABLE pacientes ADD telefone VARCHAR(20) not null;

ALTER TABLE pacientes ADD ativo tinyint;

UPDATE pacientes SET ativo = 1;