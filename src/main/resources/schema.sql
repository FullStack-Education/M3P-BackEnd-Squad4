drop table if exists curso_materia;

drop table if exists docente_materia;

drop table if exists nota;

drop table if exists materia;

drop table if exists aluno;

drop table if exists turma;

drop table if exists curso;

drop table if exists docente;

drop table if exists usuario;

drop table if exists papel;

create table curso
(
    id   bigserial
        primary key,
    nome varchar(255)
        constraint uk_bdhliwglt8i7q1v80fb95vea9
            unique
);

alter table curso
    owner to postgres;

create table materia
(
    id   bigserial
        primary key,
    nome varchar(255)
        constraint uk_327l6c9d2i5c00g1gexca04dx
            unique
);

alter table materia
    owner to postgres;

create table curso_materia
(
    id_materia bigint not null
        constraint fktb2idpg3qvv8jfna43xa6dm9m
            references materia,
    id_curso   bigint not null
        constraint fke92qkr6grt5s6ucvbqjbtpo9x
            references curso
);

alter table curso_materia
    owner to postgres;

create table papel
(
    id   bigserial
        primary key,
    nome varchar(255)
        constraint uk_r0myf68c69dgjedxpdnbpdrhb
            unique
);

alter table papel
    owner to postgres;

create table usuario
(
    id       bigserial
        primary key,
    email    varchar(255)
        constraint uk_5171l57faosmj8myawaucatdw
            unique,
    nome     varchar(255),
    senha    varchar(255),
    id_papel bigint
        constraint fk3o6mx475cqi8lc71b11awqdh6
            references papel
);

alter table usuario
    owner to postgres;

create table docente
(
    id                  bigserial
        primary key,
    bairro              varchar(255),
    cep                 varchar(255),
    complemento         varchar(255),
    cpf                 varchar(255),
    email               varchar(255)
        constraint uk_cjaekp5lnot6c6dokjntmexhj
            unique,
    estado_civil        varchar(255),
    genero              varchar(255),
    localidade          varchar(255),
    logradouro          varchar(255),
    data_nascimento     date,
    naturalidade        varchar(255),
    nome_completo       varchar(255),
    numero              varchar(255),
    ponto_de_referencia varchar(255),
    rg                  varchar(255),
    senha               varchar(255),
    telefone            varchar(255),
    uf                  varchar(255),
    id_usuario          bigint
        constraint uk_g59bvniliwyev7wy00glxe17a
            unique
        constraint fk86smr8ieg6n2thvd52c336w9n
            references usuario
);

alter table docente
    owner to postgres;

create table docente_materia
(
    id_docente bigint not null
        constraint fksg88xfbrs4ej74ie7n3dn2tpm
            references docente,
    id_materia bigint not null
        constraint fksqdu2u31yut76wsisy4s0b9xa
            references materia
);

alter table docente_materia
    owner to postgres;

create table turma
(
    id           bigserial
        primary key,
    data_inicio  date,
    data_termino date,
    horario      varchar(255),
    nome_turma   varchar(255)
        constraint uk_3wf66lgehe9ptvdkjdoy6285a
            unique,
    id_curso     bigint
        constraint fkqksqho9nu4sweas62bmdjibqd
            references curso,
    id_docente   bigint
        constraint fkg2y61kljorw9ia7o6jag00rv7
            references docente
);

alter table turma
    owner to postgres;

create table aluno
(
    id              bigserial
        primary key,
    bairro          varchar(255),
    cep             varchar(255),
    complemento     varchar(255),
    cpf             varchar(255),
    email           varchar(255)
        constraint uk_3wpes15e0anbfaa4do0pey97k
            unique,
    genero          varchar(255),
    localidade      varchar(255),
    logradouro      varchar(255),
    data_nascimento date,
    naturalidade    varchar(255),
    nome_completo   varchar(255) not null,
    numero          varchar(255),
    referencia      varchar(255),
    rg              varchar(255),
    senha           varchar(255),
    telefone        varchar(255),
    uf              varchar(255),
    id_turma        bigint
        constraint fk6u9nh0rofks23793tl5j0f7v3
            references turma,
    id_usuario      bigint
        constraint uk_m0orp81uob48mwwen1b8kxac0
            unique
        constraint fk52hmywkcaj1tbxgiofx92clf2
            references usuario
);

alter table aluno
    owner to postgres;

create table nota
(
    id         bigserial
        primary key,
    avaliacao  varchar(255),
    data       date,
    nota       numeric(38, 2),
    id_aluno   bigint
        constraint fks87psgjtyjk8qp63swp4br17x
            references aluno,
    id_docente bigint
        constraint fk5xnldnt13xgkj1ran7ecypd7m
            references docente,
    id_materia bigint
        constraint fkm49h6eg3gce7qjcj1xldk5d2i
            references materia
);

alter table nota
    owner to postgres;

