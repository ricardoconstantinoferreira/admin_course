# [![Java](https://img.shields.io/badge/Java-%3E%3D11-informational?logo=java&logoColor=white)](https://www.java.com) [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-%3E%3D2.6-informational?logo=spring&logoColor=white)](https://spring.io/projects/spring-boot) [![MySQL](https://img.shields.io/badge/MySQL-%3E%3D5.7-informational?logo=mysql&logoColor=white)](https://www.mysql.com)

# Admin Course (Backend)

Este repositório contém a API backend do administrador do sistema de cursos — uma aplicação Java com Spring Boot que expõe endpoints REST consumidos por um cliente (por exemplo, AngularJS).

## Visão geral
- Função: backend administrativo para gerenciar cursos, matérias (subjects) e professores.
- Estrutura: controllers enxutos, services com a lógica de negócio, repositories Spring Data JPA, DTOs e mappers para conversão entre entidades e camadas de transporte.
- Relacionamentos importantes: Course <-> Subject e Professor <-> Subject são relações many-to-many (tabelas de junção).

## Principais pacotes
- `com.ferreiracurso.admin.controller` — endpoints REST (ex.: `/api/courses`, `/api/subjects`, `/api/professors`)
- `com.ferreiracurso.admin.service` — interfaces de serviço
- `com.ferreiracurso.admin.service.impl` — implementações dos serviços (injeção por construtor)
- `com.ferreiracurso.admin.repository` — repositórios Spring Data JPA
- `com.ferreiracurso.admin.model` — entidades JPA (Course, Subject, Professor)
- `com.ferreiracurso.admin.dto` — DTOs para entrada/saída
- `com.ferreiracurso.admin.mapper` — mappers para converter entre entidades e DTOs

## Como compilar e executar
1. Build (gera o JAR):

    ```bash
    ./mvnw clean package
    ```

2. Executar a aplicação localmente:

    ```bash
    ./mvnw spring-boot:run
    # ou
    java -jar target/admin-0.0.1-SNAPSHOT.jar
    ```

3. Executar os testes:

    ```bash
    ./mvnw test
    ```

## EndPoints REST principais (exemplos)
- Cursos: `POST /api/courses`, `GET /api/courses`, `GET /api/courses/{id}`, `PUT /api/courses/{id}`, `DELETE /api/courses/{id}`
- Matérias: `POST /api/subjects`, `GET /api/subjects`, `GET /api/subjects/{id}`, `PUT /api/subjects/{id}`, `DELETE /api/subjects/{id}`
- Professores: `POST /api/professors`, `GET /api/professors`, `GET /api/professors/{id}`, `PUT /api/professors/{id}`, `DELETE /api/professors/{id}`

## Boas práticas e observações
- Validação de entrada usa JSR-380 (`@NotNull`, `@NotBlank`, `@Size`, etc.).
- Use BigDecimal para valores monetários (ex.: price, salary).
- Injeção de dependência por construtor, campos `private final` nas classes de serviço.
- Relacionamentos many-to-many usam tabelas de junção sem cascade REMOVE por segurança; Subject é mantido simples para evitar alterações indesejadas.

## Configuração
- As configurações estão em `src/main/resources/application.properties` (datasource, portas, propriedades JWT se houver).
- Recomenda-se usar perfis Spring (`dev`, `test`, `prod`) para ambientes diferentes.

## Contribuição
- Faça um fork, crie uma branch feature, implemente e abra um PR. Siga o padrão de pacotes e as convenções de validação e logging existentes.

## Contato
- Mantido por Ricardo Ferreira (projeto de exemplo/curso).
