# LabPCP: API Rest para Gestão Educacional

## Introdução

Este repositório contém o backend do Projeto PCP, desenvolvido como parte do módulo final do curso Fullstack, turma Education, do programa Floripa Mais Tec.

O sistema fornece uma API robusta e segura para gerenciar dados relacionados a docentes, alunos, cursos, matérias, turmas e notas.
Utilizando tecnologias como Spring Boot, JWT para autenticação e JPA para persistência, a API está integrada ao frontend desenvolvido para o projeto.

A arquitetura foi desenhada para ser modular e escalável, garantindo facilidade na manutenção e expansão do sistema.

O GitHub foi utilizado como versionador de código no modelo Git Flow.
O Trello foi utilizado para organização e gerenciamento das tarefas do projeto.
O Discord foi utilizado como canal de comunicação.

## Tecnologias e Ferramentas Utilizadas

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- PostgreSQL
- Docker

## Ferramentas Complementares
- Postman para testes de API
- PgAdmin para gerenciar o banco de dados
- Trello
- GitHub

## Pré-Requisitos
- JDK 17 ou superior 
- IntelliJ IDEA 
- Git 
- Docker 
- Docker Compose

## Dependências

- Spring Boot Starter for Spring Data JPA
- Spring Boot Starter for Spring Web
- Spring Boot DevTools
- PostgreSQL Driver
- Spring Boot Configuration Processor
- Project Lombok
- MapStruct
- MapStruct Processor
- Spring Boot Starter for Testing
- Hibernate Validator
- Spring Boot Starter for Security
- Java JWT API
- Java JWT Implementation
- Java JWT with Jackson
- Jackson Databind
- SpringDoc OpenAPI Starter for WebMVC and UI

## Estrutura do Projeto

### Arquitetura
O projeto segue a arquitetura Controller-Service-Repository, promovendo a separação de responsabilidades:
- Controller: Gerencia as requisições HTTP.
- Service: Contém a lógica de negócios.
- Repository: Interage com o banco de dados via JPA.

### Segurança
- Spring Security: Configurado para autenticação e autorização.
- JWT: Utilizado para proteger as rotas e garantir acesso seguro.

### Banco de Dados
- Utiliza PostgreSQL para armazenamento de dados.
- Configuração rápida com Docker. Veja os passos em Configuração do Banco de Dados.

![Modelo de Classes](/doc/modelo.png)

## Como Começar

### Clonando o Repositório
```
git clone https://github.com/FullStack-Education/M3P-BackEnd-Squad4.git
```
### Instalando o Projeto

Rodar o docker

Rodar o docker compose

Criar o BD

Importar o data.sql


Se desejar usar o PostgreSQL via Docker, execute os comandos abaixo:

```sh
docker volume create pgdb
docker network create pgnet
docker run -d --name postgres -p 5432:5432 --network pgnet -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=projeto-pcp -v pgdb:/var/lib/postgresql/data postgres:15.6-alpine
docker run -d --name pgadmin -p 8000:80 --network pgnet -e PGADMIN_DEFAULT_EMAIL=postgres -e PGADMIN_DEFAULT_PASSWORD=123456 -d dpage/pgadmin4
```
Após iniciar o PgAdmin em http://localhost:8000, importe o arquivo data.sql, localizado na pasta sql.

A senha padrão do usuário admin do sistema é: `senhaSegura123`.

### Execução
Abra o projeto no IntelliJ IDEA e execute-o usando o atalho de execução da IDE.

A aplicação estará disponível em `http://localhost:8080`.

## Documentação da API
A documentação da API está disponível pelo swagger que pode ser acessado em:
![Swagger](http://localhost:8080/swagger-ui/index.html#/)

Todos os endpoints, com exceção do endpoint de Login, recebem um Token JWT que controla o acesso aos dados do mesmo.
Mais detalhes também podem ser encontrados no arquivo do Postman em anexo ou no diretório doc.

## Anexos:
- [Documentação do Projeto](FullStack%20%5BEducation%5D%20-%20Módulo%203%20-%20Projeto%20Avaliativo.pdf)
- [Postman Collection](projeto-pcp.postman_collection.json)
- [Quadro de Atividades do Trello](https://trello.com/invite/b/67099647c7849f4b1cfa0b50/ATTIea929e55956831ad8f9777005e12c02f4019421E/m3p-backend-squad-4)
- [Repositório de Frontend](https://github.com/FullStack-Education/M3P-FrontEnd-Squad4.git)