# [![Java](https://img.shields.io/badge/Java-%3E%3D11-informational?logo=java&logoColor=white)](https://www.java.com) [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-%3E%3D2.6-informational?logo=spring&logoColor=white)](https://spring.io/projects/spring-boot) [![MySQL](https://img.shields.io/badge/MySQL-%3E%3D5.7-informational?logo=mysql&logoColor=white)](https://www.mysql.com)

# Admin Course (Backend)

This repository contains the backend API (admin) for the course management system — a Java Spring Boot application exposing REST endpoints consumed by a client (for example, AngularJS).

## What this project is
- Purpose: Administrative backend used to manage courses, subjects and professors, and related domain objects like curricula (grade curricular).
- This module is the administrator API for the courses project and is intended to be used by an admin UI or automated scripts.
- Architecture: thin controllers, services with business logic, Spring Data JPA repositories, DTOs and mappers for conversion between entities and transport layers.
- Important relationships: Course <-> Subject and Professor <-> Subject are many-to-many. Curriculum <-> Course is one-to-one.

## Main packages
- `com.ferreiracurso.admin.controller` — REST endpoints (e.g. `/api/courses`, `/api/subjects`, `/api/professors`, `/api/curricula`)
- `com.ferreiracurso.admin.service` — service interfaces
- `com.ferreiracurso.admin.service.impl` — service implementations (constructor injection)
- `com.ferreiracurso.admin.repository` — Spring Data JPA repositories
- `com.ferreiracurso.admin.model` — JPA entities (Course, Subject, Professor, Curriculum)
- `com.ferreiracurso.admin.dto` — DTOs for input/output
- `com.ferreiracurso.admin.mapper` — mappers to convert between entities and DTOs

## How to build and run
1. Build (produces the JAR):

    ```bash
    ./mvnw clean package
    ```

2. Run the application locally:

    ```bash
    ./mvnw spring-boot:run
    # or
    java -jar target/admin-0.0.1-SNAPSHOT.jar
    ```

3. Run the tests:

    ```bash
    ./mvnw test
    ```

## Main REST endpoints (examples)
- Courses: `POST /api/courses`, `GET /api/courses`, `GET /api/courses/{id}`, `PUT /api/courses/{id}`, `DELETE /api/courses/{id}`
- Subjects: `POST /api/subjects`, `GET /api/subjects`, `GET /api/subjects/{id}`, `PUT /api/subjects/{id}`, `DELETE /api/subjects/{id}`
- Professors: `POST /api/professors`, `GET /api/professors`, `GET /api/professors/{id}`, `PUT /api/professors/{id}`, `DELETE /api/professors/{id}`
- Curricula: `POST /api/curricula`, `GET /api/curricula`, `GET /api/curricula/{id}`, `PUT /api/curricula/{id}`, `DELETE /api/curricula/{id}`

## Best practices and notes
- Input validation uses JSR-380 (`@NotNull`, `@NotBlank`, `@Size`, etc.).
- Use BigDecimal for monetary values (e.g., price, salary).
- Constructor injection and `private final` fields are used for services and components.
- Many-to-many relationships use join tables without cascade REMOVE by default to avoid accidental deletes.

## Configuration
- Configuration is in `src/main/resources/application.properties` (datasource, ports, JWT properties if applicable).
- Recommended to use Spring profiles (`dev`, `test`, `prod`) for different environments.

## Contributing
- Fork the repo, create a feature branch, implement and open a PR. Follow package and validation/logging conventions.

## Contact
- Maintained by Ricardo Ferreira (example/course project).
