CREATE DATABASE projeto_pcp;

CREATE TABLE aluno (
	id bigserial NOT NULL,
	data_nascimento date,
	nome varchar(255),
	id_usuario int8,
	id_turma int8,
	CONSTRAINT pk_aluno PRIMARY KEY (id)
);

CREATE TABLE curso (
	id bigserial NOT NULL,
	nome varchar(255),
	CONSTRAINT pk_curso PRIMARY KEY (id)
);

CREATE TABLE docente (
	id bigserial NOT NULL,
	data_entrada date,
	nome varchar(255),
	id_usuario int8,
	CONSTRAINT pk_docente PRIMARY KEY (id)
);

CREATE TABLE materia (
	id bigserial NOT NULL,
	nome varchar(255),
	id_curso int8,
	CONSTRAINT pk_materia PRIMARY KEY (id)
);

CREATE TABLE nota (
	id bigserial NOT NULL,
	data date,
	valor numeric(19, 2),
	id_aluno int8,
	id_docente int8,
	id_materia int8,
	CONSTRAINT pk_nota PRIMARY KEY (id)
);

CREATE TABLE papel (
	id bigserial NOT NULL,
	nome varchar(255),
	CONSTRAINT pk_papel PRIMARY KEY (id)
);

CREATE TABLE turma (
	id bigserial NOT NULL,
	nome varchar(255),
	id_docente int8,
	id_curso int8,
	CONSTRAINT pk_turma PRIMARY KEY (id)
);

CREATE TABLE usuario (
	id bigserial NOT NULL,
	login varchar(255),
	nome varchar(255),
	senha varchar(255),
	id_papel int8,
	CONSTRAINT pk_usuario PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS aluno
	add CONSTRAINT fk_aluno_usuario
	FOREIGN KEY (id_usuario)
	REFERENCES usuario;

CREATE INDEX idx_aluno_id_usuario ON aluno (id_usuario);

ALTER TABLE IF EXISTS aluno
	add CONSTRAINT fk_aluno_turma
	FOREIGN KEY (id_turma)
	REFERENCES turma;

CREATE INDEX idx_aluno_id_turma ON aluno (id_turma);

ALTER TABLE IF EXISTS docente
	add CONSTRAINT fk_docente_usuario
	FOREIGN KEY (id_usuario)
	REFERENCES usuario;

CREATE INDEX idx_docente_id_usuario ON docente (id_usuario);

ALTER TABLE IF EXISTS materia
	add CONSTRAINT fk_materia_curso
	FOREIGN KEY (id_curso)
	REFERENCES curso;

CREATE INDEX idx_materia_id_curso ON materia (id_curso);

ALTER TABLE IF EXISTS nota
	add CONSTRAINT fk_nota_aluno
	FOREIGN KEY (id_aluno)
	REFERENCES aluno;

CREATE INDEX idx_nota_id_aluno ON nota (id_aluno);

ALTER TABLE IF EXISTS nota
	add CONSTRAINT fk_nota_docente
	FOREIGN KEY (id_docente)
	REFERENCES docente;

CREATE INDEX idx_nota_id_docente ON nota (id_docente);

ALTER TABLE IF EXISTS nota
	add CONSTRAINT fk_nota_materia
	FOREIGN KEY (id_materia)
	REFERENCES materia;

CREATE INDEX idx_nota_id_materia ON nota (id_materia);

ALTER TABLE IF EXISTS turma
	add CONSTRAINT fk_turma_docente
	FOREIGN KEY (id_docente)
	REFERENCES docente;

CREATE INDEX idx_turma_id_docente ON turma (id_docente);

ALTER TABLE IF EXISTS turma
	add CONSTRAINT fk_turma_curso
	FOREIGN KEY (id_curso)
	REFERENCES curso;

CREATE INDEX idx_turma_id_curso ON turma (id_curso);

ALTER TABLE IF EXISTS usuario
	add CONSTRAINT fk_usuario_papel
	FOREIGN KEY (id_papel)
	REFERENCES papel;

CREATE INDEX idx_usuario_id_papel ON usuario (id_papel);

ALTER TABLE IF EXISTS public.usuario ALTER COLUMN login SET NOT NULL;
ALTER TABLE IF EXISTS public.usuario ADD CONSTRAINT uk_login UNIQUE (login);

INSERT INTO public.papel(id, nome) VALUES (1, 'ADM');
INSERT INTO public.papel(id, nome) VALUES (2, 'PEDAGOGICO');
INSERT INTO public.papel(id, nome) VALUES (3, 'RECRUITER');
INSERT INTO public.papel(id, nome) VALUES (4, 'PROFESSOR');
INSERT INTO public.papel(id, nome) VALUES (5, 'ALUNO');

INSERT INTO public.usuario(id, login, nome, senha, id_papel) VALUES (1, 'admin', 'admin', '$2a$10$yLsrjeOc5hUYJKFxgO13XejuUi3AWR26MmFSF9JH57EFwMb/i7OVW', 1);
