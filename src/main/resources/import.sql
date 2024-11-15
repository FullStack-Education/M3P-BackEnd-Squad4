INSERT INTO public.papel(id, nome) VALUES (1, 'ADM');
INSERT INTO public.papel(id, nome) VALUES (2, 'PEDAGOGICO');
INSERT INTO public.papel(id, nome) VALUES (3, 'RECRUITER');
INSERT INTO public.papel(id, nome) VALUES (4, 'PROFESSOR');
INSERT INTO public.papel(id, nome) VALUES (5, 'ALUNO');

INSERT INTO public.usuario(email, nome, senha, id_papel) VALUES ('admin@mail.com', 'admin', '$2a$10$yLsrjeOc5hUYJKFxgO13XejuUi3AWR26MmFSF9JH57EFwMb/i7OVW', 1)
on conflict (email) do nothing;

INSERT INTO public.curso(nome) VALUES ('Curso de A');
INSERT INTO public.curso(nome) VALUES ('Curso de B');
INSERT INTO public.curso(nome) VALUES ('Curso de C');

INSERT INTO public.materia(nome) VALUES ('Materia 1');
INSERT INTO public.materia(nome) VALUES ('Materia 2');
INSERT INTO public.materia(nome) VALUES ('Materia 3');
INSERT INTO public.materia(nome) VALUES ('Materia 4');
INSERT INTO public.materia(nome) VALUES ('Materia 5');
INSERT INTO public.materia(nome) VALUES ('Materia 6');
INSERT INTO public.curso_materia(id_curso, id_materia) VALUES (1, 1);
INSERT INTO public.curso_materia(id_curso, id_materia) VALUES (1, 2);
INSERT INTO public.curso_materia(id_curso, id_materia) VALUES (2, 3);
INSERT INTO public.curso_materia(id_curso, id_materia) VALUES (2, 4);
INSERT INTO public.curso_materia(id_curso, id_materia) VALUES (3, 5);
INSERT INTO public.curso_materia(id_curso, id_materia) VALUES (3, 6);

INSERT INTO public.turma(nome_turma, data_inicio, data_termino, horario, id_curso)
VALUES ('Turma Inicializada A', '2024-02-01', '2024-12-15', '08:00 - 12:00', 1);
INSERT INTO public.turma(nome_turma, data_inicio, data_termino, horario, id_curso)
VALUES ('Turma Inicializada B', '2024-03-01', '2024-11-30', '13:00 - 17:00', 2);
INSERT INTO public.turma(nome_turma, data_inicio, data_termino, horario, id_curso)
VALUES ('Turma Inicializada C', '2024-01-15', '2024-10-20', '18:00 - 22:00', 3);
INSERT INTO public.turma(nome_turma, data_inicio, data_termino, horario, id_curso)
VALUES ('Turma Inicializada D', '2024-04-01', '2024-12-30', '09:00 - 13:00', 1);
INSERT INTO public.turma(nome_turma, data_inicio, data_termino, horario, id_curso)
VALUES ('Turma Inicializada E', '2024-05-01', '2024-11-15', '14:00 - 18:00', 2);
INSERT INTO public.turma(nome_turma, data_inicio, data_termino, horario, id_curso)
VALUES ('Turma Inicializada F', '2024-06-10', '2024-12-10', '19:00 - 23:00', 3);

CREATE OR REPLACE FUNCTION create_usuario_for_docente()
    RETURNS trigger AS $$
DECLARE
    usuario_id INT;
BEGIN
    INSERT INTO public.usuario(email, senha, nome, id_papel)
    VALUES (NEW.email, NEW.senha, NEW.nome_completo, 4)
    RETURNING id INTO usuario_id;

    UPDATE public.docente
    SET id_usuario = usuario_id
    WHERE id = NEW.id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_docente_insert
    AFTER INSERT ON public.docente
    FOR EACH ROW
EXECUTE FUNCTION create_usuario_for_docente();

INSERT INTO public.docente(nome_completo, genero, data_nascimento, naturalidade, cpf, rg, telefone, email, senha, estado_civil, cep, localidade, uf, logradouro, numero, complemento, bairro, ponto_de_referencia)
VALUES ('Professor 1', 'Masculino', '1980-01-15', 'Belo Horizonte', '12345678901', 'MG-123456', '31987654321', 'professor1@mail.com', 'abc12345', 'Solteiro', '30111-000', 'Belo Horizonte', 'MG', 'Avenida Afonso Pena', '1000', 'Apto 1', 'Centro', 'Próximo ao Mercado Central');
INSERT INTO public.docente_materia(id_docente, id_materia) VALUES ((SELECT id FROM public.docente WHERE email = 'professor1@mail.com'), 1);
INSERT INTO public.docente(nome_completo, genero, data_nascimento, naturalidade, cpf, rg, telefone, email, senha, estado_civil, cep, localidade, uf, logradouro, numero, complemento, bairro, ponto_de_referencia)
VALUES ('Professor 2', 'Feminino', '1985-03-20', 'São Paulo', '12345678902', 'SP-654321', '31912345678', 'professor2@mail.com', 'abc12345', 'Casado', '30122-000', 'São Paulo', 'SP', 'Rua dos Três Irmãos', '200', 'Casa', 'Vila Progredior', 'Perto da padaria');
INSERT INTO public.docente_materia(id_docente, id_materia) VALUES ((SELECT id FROM public.docente WHERE email = 'professor2@mail.com'), 2);
INSERT INTO public.docente_materia(id_docente, id_materia) VALUES ((SELECT id FROM public.docente WHERE email = 'professor2@mail.com'), 3);
INSERT INTO public.docente(nome_completo, genero, data_nascimento, naturalidade, cpf, rg, telefone, email, senha, estado_civil, cep, localidade, uf, logradouro, numero, complemento, bairro, ponto_de_referencia)
VALUES ('Professor 3', 'Masculino', '1975-07-10', 'Rio de Janeiro', '12345678903', 'RJ-789012', '31911223344', 'professor3@mail.com', 'abc12345', 'Solteiro', '30133-000', 'Rio de Janeiro', 'RJ', 'Rua Marquês de São Vicente', '300', 'Apto 3', 'Bairro Flamengo', 'Próximo ao parque');
INSERT INTO public.docente_materia(id_docente, id_materia) VALUES ((SELECT id FROM public.docente WHERE email = 'professor3@mail.com'), 4);
INSERT INTO public.docente(nome_completo, genero, data_nascimento, naturalidade, cpf, rg, telefone, email, senha, estado_civil, cep, localidade, uf, logradouro, numero, complemento, bairro, ponto_de_referencia)
VALUES ('Professor 4', 'Feminino', '1990-09-25', 'Salvador', '12345678904', 'BA-987654', '31933445566', 'professor4@mail.com', 'abc12345', 'Casado', '30144-000', 'Salvador', 'BA', 'Rua das Laranjeiras', '400', 'Casa', 'Laranjeiras', 'Próximo à escola');
INSERT INTO public.docente_materia(id_docente, id_materia) VALUES ((SELECT id FROM public.docente WHERE email = 'professor4@mail.com'), 5);
INSERT INTO public.docente_materia(id_docente, id_materia) VALUES ((SELECT id FROM public.docente WHERE email = 'professor4@mail.com'), 6);
INSERT INTO public.docente(nome_completo, genero, data_nascimento, naturalidade, cpf, rg, telefone, email, senha, estado_civil, cep, localidade, uf, logradouro, numero, complemento, bairro, ponto_de_referencia)
VALUES ('Professor 5', 'Masculino', '1982-11-30', 'Florianópolis', '12345678905', 'SC-321654', '31944556677', 'professor5@mail.com', 'abc12345', 'Solteiro', '30155-000', 'Florianópolis', 'SC', 'Rua Silva Jardim', '500', 'Apto 5', 'Centro', 'Próximo ao shopping');
INSERT INTO public.docente_materia(id_docente, id_materia) VALUES ((SELECT id FROM public.docente WHERE email = 'professor5@mail.com'), 1);
INSERT INTO public.docente(nome_completo, genero, data_nascimento, naturalidade, cpf, rg, telefone, email, senha, estado_civil, cep, localidade, uf, logradouro, numero, complemento, bairro, ponto_de_referencia)
VALUES ('Professor 6', 'Feminino', '1995-06-18', 'Vitória', '12345678906', 'ES-654123', '31955667788', 'professor6@mail.com', 'abc12345', 'Casado', '30166-000', 'Vitória', 'ES', 'Avenida Nossa Senhora da Penha', '600', 'Casa', 'Bairro da Penha', 'Perto da praça');
INSERT INTO public.docente_materia(id_docente, id_materia) VALUES ((SELECT id FROM public.docente WHERE email = 'professor6@mail.com'), 2);
INSERT INTO public.docente_materia(id_docente, id_materia) VALUES ((SELECT id FROM public.docente WHERE email = 'professor6@mail.com'), 3);

UPDATE public.turma
SET id_docente = (SELECT id FROM public.docente WHERE email = 'professor1@mail.com')
WHERE nome_turma = 'Turma Inicializada A';
UPDATE public.turma
SET id_docente = (SELECT id FROM public.docente WHERE email = 'professor2@mail.com')
WHERE nome_turma = 'Turma Inicializada B';
UPDATE public.turma
SET id_docente = (SELECT id FROM public.docente WHERE email = 'professor3@mail.com')
WHERE nome_turma = 'Turma Inicializada C';
UPDATE public.turma
SET id_docente = (SELECT id FROM public.docente WHERE email = 'professor4@mail.com')
WHERE nome_turma = 'Turma Inicializada D';
UPDATE public.turma
SET id_docente = (SELECT id FROM public.docente WHERE email = 'professor5@mail.com')
WHERE nome_turma = 'Turma Inicializada E';
UPDATE public.turma
SET id_docente = (SELECT id FROM public.docente WHERE email = 'professor6@mail.com')
WHERE nome_turma = 'Turma Inicializada F';

CREATE OR REPLACE FUNCTION create_usuario_for_aluno()
    RETURNS TRIGGER AS $$
DECLARE
    usuario_id INT;
BEGIN
    INSERT INTO public.usuario (email, senha, nome, id_papel)
    VALUES (NEW.email, NEW.senha, NEW.nome_completo, 5)
    RETURNING id INTO usuario_id;

    UPDATE public.aluno
    SET id_usuario = usuario_id
    WHERE id = NEW.id;

    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER after_aluno_insert
    AFTER INSERT ON public.aluno
    FOR EACH ROW
EXECUTE FUNCTION create_usuario_for_aluno();

INSERT INTO public.aluno(nome_completo, telefone, genero, id_turma, data_nascimento, email, senha, cpf, rg, naturalidade, cep, logradouro, numero, localidade, bairro, uf, complemento, referencia)
VALUES ('Aluno 1', '31999999999', 'Masculino', 1, '2000-05-01', 'aluno1@mail.com', 'senha123', '12345678901', 'MG-123456', 'Belo Horizonte', '30111-001', 'Rua A', '100', 'Belo Horizonte', 'Centro', 'MG', 'Apto 101', 'Próximo ao Mercado Central');
INSERT INTO public.aluno(nome_completo, telefone, genero, id_turma, data_nascimento, email, senha, cpf, rg, naturalidade, cep, logradouro, numero, localidade, bairro, uf, complemento, referencia)
VALUES ('Aluno 2', '31988888888', 'Feminino', 1, '2001-08-15', 'aluno2@mail.com', 'senha123', '12345678902', 'MG-654321', 'Contagem', '30111-002', 'Rua B', '200', 'Contagem', 'Centro', 'MG', 'Apto 202', 'Próximo ao Shopping');
INSERT INTO public.aluno(nome_completo, telefone, genero, id_turma, data_nascimento, email, senha, cpf, rg, naturalidade, cep, logradouro, numero, localidade, bairro, uf, complemento, referencia)
VALUES ('Aluno 3', '31977777777', 'Masculino', 1, '2002-02-10', 'aluno3@mail.com', 'senha123', '12345678903', 'MG-987654', 'Belo Horizonte', '30111-003', 'Rua C', '300', 'Belo Horizonte', 'Centro', 'MG', 'Apto 303', 'Próximo à praça');
INSERT INTO public.aluno(nome_completo, telefone, genero, id_turma, data_nascimento, email, senha, cpf, rg, naturalidade, cep, logradouro, numero, localidade, bairro, uf, complemento, referencia)
VALUES ('Aluno 4', '31966666666', 'Feminino', 2, '2000-06-18', 'aluno4@mail.com', 'senha123', '12345678904', 'SP-123456', 'São Paulo', '30122-001', 'Rua D', '400', 'São Paulo', 'Vila Mariana', 'SP', 'Casa 1', 'Próximo ao Shopping');
INSERT INTO public.aluno(nome_completo, telefone, genero, id_turma, data_nascimento, email, senha, cpf, rg, naturalidade, cep, logradouro, numero, localidade, bairro, uf, complemento, referencia)
VALUES ('Aluno 5', '31955555555', 'Masculino', 2, '2001-09-12', 'aluno5@mail.com', 'senha123', '12345678905', 'SP-654321', 'Campinas', '30122-002', 'Rua E', '500', 'Campinas', 'Centro', 'SP', 'Casa 2', 'Perto do Parque');
INSERT INTO public.aluno(nome_completo, telefone, genero, id_turma, data_nascimento, email, senha, cpf, rg, naturalidade, cep, logradouro, numero, localidade, bairro, uf, complemento, referencia)
VALUES ('Aluno 6', '31944444444', 'Feminino', 2, '2002-03-25', 'aluno6@mail.com', 'senha123', '12345678906', 'SP-987654', 'São Paulo', '30122-003', 'Rua F', '600', 'São Paulo', 'Vila Madalena', 'SP', 'Casa 3', 'Próximo ao metrô');
INSERT INTO public.aluno(nome_completo, telefone, genero, id_turma, data_nascimento, email, senha, cpf, rg, naturalidade, cep, logradouro, numero, localidade, bairro, uf, complemento, referencia)
VALUES ('Aluno 7', '31933333333', 'Masculino', 3, '2000-07-20', 'aluno7@mail.com', 'senha123', '12345678907', 'RJ-123456', 'Rio de Janeiro', '30133-001', 'Rua G', '700', 'Rio de Janeiro', 'Copacabana', 'RJ', 'Apto 701', 'Perto da praia');
INSERT INTO public.aluno(nome_completo, telefone, genero, id_turma, data_nascimento, email, senha, cpf, rg, naturalidade, cep, logradouro, numero, localidade, bairro, uf, complemento, referencia)
VALUES ('Aluno 8', '31922222222', 'Feminino', 3, '2001-04-17', 'aluno8@mail.com', 'senha123', '12345678908', 'RJ-654321', 'Niterói', '30133-002', 'Rua H', '800', 'Niterói', 'Centro', 'RJ', 'Apto 802', 'Próximo ao teatro');
INSERT INTO public.aluno(nome_completo, telefone, genero, id_turma, data_nascimento, email, senha, cpf, rg, naturalidade, cep, logradouro, numero, localidade, bairro, uf, complemento, referencia)
VALUES ('Aluno 9', '31911111111', 'Masculino', 3, '2002-05-22', 'aluno9@mail.com', 'senha123', '12345678909', 'RJ-987654', 'Rio de Janeiro', '30133-003', 'Rua I', '900', 'Rio de Janeiro', 'Ipanema', 'RJ', 'Apto 903', 'Próximo à praia');

INSERT INTO public.nota(avaliacao, nota, data, id_aluno, id_docente, id_materia)
VALUES
    ('Avaliação 1', 8.5, '2024-11-14', 1, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 1 LIMIT 1), 1),
    ('Avaliação 2', 9.0, '2024-11-14', 1, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 2 LIMIT 1), 2),
    ('Avaliação 3', 7.0, '2024-11-14', 1, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 1 LIMIT 1), 1),
    ('Avaliação 4', 8.0, '2024-11-14', 1, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 2 LIMIT 1), 2);
INSERT INTO public.nota(avaliacao, nota, data, id_aluno, id_docente, id_materia)
VALUES
    ('Avaliação 1', 6.5, '2024-11-14', 2, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 1 LIMIT 1), 1),
    ('Avaliação 2', 7.0, '2024-11-14', 2, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 2 LIMIT 1), 2),
    ('Avaliação 3', 6.0, '2024-11-14', 2, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 1 LIMIT 1), 1),
    ('Avaliação 4', 6.5, '2024-11-14', 2, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 2 LIMIT 1), 2);
INSERT INTO public.nota(avaliacao, nota, data, id_aluno, id_docente, id_materia)
VALUES
    ('Avaliação 1', 9.5, '2024-11-14', 3, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 1 LIMIT 1), 1),
    ('Avaliação 2', 9.0, '2024-11-14', 3, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 2 LIMIT 1), 2),
    ('Avaliação 3', 8.0, '2024-11-14', 3, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 1 LIMIT 1), 1),
    ('Avaliação 4', 9.0, '2024-11-14', 3, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 2 LIMIT 1), 2);
INSERT INTO public.nota(avaliacao, nota, data, id_aluno, id_docente, id_materia)
VALUES
    ('Avaliação 1', 6.0, '2024-11-14', 4, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 3 LIMIT 1), 3),
    ('Avaliação 2', 5.5, '2024-11-14', 4, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 4 LIMIT 1), 4),
    ('Avaliação 3', 5.0, '2024-11-14', 4, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 3 LIMIT 1), 3),
    ('Avaliação 4', 6.0, '2024-11-14', 4, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 4 LIMIT 1), 4);
INSERT INTO public.nota(avaliacao, nota, data, id_aluno, id_docente, id_materia)
VALUES
    ('Avaliação 1', 8.0, '2024-11-14', 5, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 3 LIMIT 1), 3),
    ('Avaliação 2', 7.5, '2024-11-14', 5, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 4 LIMIT 1), 4),
    ('Avaliação 3', 7.0, '2024-11-14', 5, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 3 LIMIT 1), 3),
    ('Avaliação 4', 8.0, '2024-11-14', 5, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 4 LIMIT 1), 4);
INSERT INTO public.nota(avaliacao, nota, data, id_aluno, id_docente, id_materia)
VALUES
    ('Avaliação 1', 7.5, '2024-11-14', 6, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 3 LIMIT 1), 3),
    ('Avaliação 2', 8.0, '2024-11-14', 6, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 4 LIMIT 1), 4),
    ('Avaliação 3', 7.0, '2024-11-14', 6, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 3 LIMIT 1), 3),
    ('Avaliação 4', 8.5, '2024-11-14', 6, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 4 LIMIT 1), 4);
INSERT INTO public.nota(avaliacao, nota, data, id_aluno, id_docente, id_materia)
VALUES
    ('Avaliação 1', 6.5, '2024-11-14', 7, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 5 LIMIT 1), 5),
    ('Avaliação 2', 7.0, '2024-11-14', 7, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 6 LIMIT 1), 6),
    ('Avaliação 3', 6.0, '2024-11-14', 7, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 5 LIMIT 1), 5),
    ('Avaliação 4', 6.5, '2024-11-14', 7, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 6 LIMIT 1), 6);
INSERT INTO public.nota(avaliacao, nota, data, id_aluno, id_docente, id_materia)
VALUES
    ('Avaliação 1', 8.0, '2024-11-14', 8, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 5 LIMIT 1), 5),
    ('Avaliação 2', 9.0, '2024-11-14', 8, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 6 LIMIT 1), 6),
    ('Avaliação 3', 8.5, '2024-11-14', 8, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 5 LIMIT 1), 5),
    ('Avaliação 4', 9.0, '2024-11-14', 8, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 6 LIMIT 1), 6);
INSERT INTO public.nota(avaliacao, nota, data, id_aluno, id_docente, id_materia)
VALUES
    ('Avaliação 1', 9.5, '2024-11-14', 9, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 5 LIMIT 1), 5),
    ('Avaliação 2', 9.5, '2024-11-14', 9, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 6 LIMIT 1), 6),
    ('Avaliação 3', 9.0, '2024-11-14', 9, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 5 LIMIT 1), 5),
    ('Avaliação 4', 9.5, '2024-11-14', 9, (SELECT id_docente FROM public.docente_materia WHERE id_materia = 6 LIMIT 1), 6);


