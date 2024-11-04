INSERT INTO public.papel
(id, nome)
VALUES
    (1, 'ADM'),
    (2, 'PEDAGOGICO'),
    (3, 'RECRUITER'),
    (4, 'PROFESSOR'),
    (5, 'ALUNO')
on conflict (nome) do nothing;

INSERT INTO public.usuario
(email, nome, senha, id_papel)
VALUES
    ('admin@mail.com', 'admin', '$2a$10$yLsrjeOc5hUYJKFxgO13XejuUi3AWR26MmFSF9JH57EFwMb/i7OVW', 1),
    ('professor1@mail.com', 'Professor 1', '$2a$10$gBjgjqevKesSjxT7f9f81.1yf0xACkN..XlYH.ZKURlaIkqjIQSme', 4),
    ('professor2@mail.com', 'Professor 2', '$2a$10$gBjgjqevKesSjxT7f9f81.1yf0xACkN..XlYH.ZKURlaIkqjIQSme', 4),
    ('professor3@mail.com', 'Professor 3', '$2a$10$gBjgjqevKesSjxT7f9f81.1yf0xACkN..XlYH.ZKURlaIkqjIQSme', 4),
    ('professor4@mail.com', 'Professor 4', '$2a$10$gBjgjqevKesSjxT7f9f81.1yf0xACkN..XlYH.ZKURlaIkqjIQSme', 4),
    ('aluno1@mail.com', 'Aluno 1 Sobrenome', '$2a$10$gBjgjqevKesSjxT7f9f81.1yf0xACkN..XlYH.ZKURlaIkqjIQSme', 5),
    ('aluno2@mail.com', 'Aluno 2 Sobrenome', '$2a$10$gBjgjqevKesSjxT7f9f81.1yf0xACkN..XlYH.ZKURlaIkqjIQSme', 5),
    ('aluno3@mail.com', 'Aluno 3 Sobrenome', '$2a$10$gBjgjqevKesSjxT7f9f81.1yf0xACkN..XlYH.ZKURlaIkqjIQSme', 5),
    ('aluno4@mail.com', 'Aluno 4 Sobrenome', '$2a$10$gBjgjqevKesSjxT7f9f81.1yf0xACkN..XlYH.ZKURlaIkqjIQSme', 5),
    ('aluno5@mail.com', 'Aluno 5 Sobrenome', '$2a$10$gBjgjqevKesSjxT7f9f81.1yf0xACkN..XlYH.ZKURlaIkqjIQSme', 5)
on conflict (email) do nothing;

INSERT INTO public.materia
(nome)
VALUES
    ('Matéria 1'),
    ('Matéria 2'),
    ('Matéria 3'),
    ('Matéria 4')
on conflict (nome) do nothing;

INSERT INTO public.curso
(nome)
VALUES
    ('Curso Teste 1'),
    ('Curso Teste 2')
on conflict (nome) do nothing;

INSERT INTO public.docente (nome_completo, genero, data_nascimento, naturalidade, cpf, rg, telefone, email, senha, estado_civil, cep, localidade, uf, logradouro, numero, complemento, bairro, ponto_de_referencia)
VALUES
    ('Professor 1', 'Masculino', '1993-05-12', 'Florianópolis-SC', '99999999991', '9999991-SSP/SC', '99999999991', 'professor1@mail.com', 'abc12345', 'Casado', '88888888', 'Florianópolis', 'Santa Catarina', 'Rua dos Manezinhos', '100', 'Não há', 'Centro', 'Não há'),
    ('Professor 2', 'Feminino', '1991-07-25', 'Florianópolis-SC', '99999999992', '9999992-SSP/SC', '99999999992', 'professor2@mail.com', 'abc12345', 'Solteiro', '88888888', 'Florianópolis', 'Santa Catarina', 'Rua das Gaivotas', '101', 'Apto 202', 'Trindade', 'Próximo ao parque'),
    ('Professor 3', 'Masculino', '1988-03-10', 'São José-SC', '99999999993', '9999993-SSP/SC', '99999999993', 'professor3@mail.com', 'abc12345', 'Casado', '88888888', 'São José', 'Santa Catarina', 'Avenida Central', '500', 'Não há', 'Barreiros', 'Em frente ao shopping'),
    ('Professor 4', 'Feminino', '1995-11-20', 'Florianópolis-SC', '99999999994', '9999994-SSP/SC', '99999999994', 'professor4@mail.com', 'abc12345', 'Divorciado', '88888888', 'Florianópolis', 'Santa Catarina', 'Rua das Pedras', '303', 'Casa 2', 'Campeche', 'Próximo ao posto')
on conflict (email) do nothing;

INSERT INTO public.turma
(nome_turma, data_inicio, data_termino, horario, id_docente, id_curso)
VALUES
    ('Turma Teste 1', '2024-03-12', '2024-12-21', '19h00', 1, 1),
    ('Turma Teste 2', '2024-03-12', '2024-12-21', '19h00', 2, 1),
    ('Turma Teste 3', '2024-03-12', '2024-12-21', '19h00', 3, 2)
on conflict (nome_turma) do nothing;

INSERT INTO public.aluno
(nome_completo, telefone, genero, id_turma, data_nascimento, email, senha, cpf, rg, naturalidade, cep, logradouro, numero, localidade, bairro, referencia, uf, complemento)
VALUES
    ('Aluno 1 Sobrenome', '99999999999', 'Masculino', 1, '1995-03-03', 'aluno1@mail.com', 'abc12345', '99999999999', '9999999-SSP/SC', 'São Paulo - SP', '99999999', 'Rua das Tainhas', '50', 'São José', 'Centro', 'Não há', 'Santa Catarina', 'não há'),
    ('Aluno 2 Sobrenome', '99999999999', 'Feminino', 2, '1996-04-04', 'aluno2@mail.com', 'abc12345', '99999999998', '9999998-SSP/SC', 'Rio de Janeiro - RJ', '99999998', 'Rua das Ostras', '51', 'Florianópolis', 'Centro', 'Não há', 'Santa Catarina', 'não há'),
    ('Aluno 3 Sobrenome', '99999999999', 'Masculino', 1, '1997-05-05', 'aluno3@mail.com', 'abc12345', '99999999997', '9999997-SSP/SC', 'Curitiba - PR', '99999997', 'Rua das Anchovas', '52', 'São José', 'Centro', 'Não há', 'Santa Catarina', 'não há'),
    ('Aluno 4 Sobrenome', '99999999999', 'Feminino', 2, '1998-06-06', 'aluno4@mail.com', 'abc12345', '99999999996', '9999996-SSP/SC', 'Porto Alegre - RS', '99999996', 'Rua das Garoupas', '53', 'São José', 'Centro', 'Não há', 'Santa Catarina', 'não há'),
    ('Aluno 5 Sobrenome', '99999999999', 'Masculino', 1, '1999-07-07', 'aluno5@mail.com', 'abc12345', '99999999995', '9999995-SSP/SC', 'Belo Horizonte - MG', '99999995', 'Rua das Sardinhas', '54', 'Florianópolis', 'Centro', 'Não há', 'Santa Catarina', 'não há')
on conflict (email) do nothing;
