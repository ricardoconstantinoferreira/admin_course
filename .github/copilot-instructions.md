Este é a api de backend do Admin dos cursos baseado em Java (Spring Boot) que recebe requisições de um cliente AngularJs. 
Ele é o principal responsável por conter todas as entidades dos cursos, além de conter as regras de negócios para a aplicacao:

## Desenvolvimento Spring Boot

### Instruções gerais

- Faça apenas sugestões de alta confiança ao revisar alterações de código
- Escreva código com boas práticas de manutenção, incluindo comentários sobre o porquê de certas decisões de 
design terem sido tomadas.
- Trate casos de borda (edge cases) e escreva um tratamento de exceções claro.
- Para bibliotecas ou dependências externas, mencione seu uso e finalidade nos comentários.

## Instruções ao Spring Boot

### Injeção de dependência

- Use injeção de dependência via construtor para todas as dependências necessárias
- Declare todos os campos de dependência como `private final`

### Configuração

- Perfis de Ambiente: Use perfis do Spring (profiles) para diferentes ambientes (dev, test, prod).
- Propriedades de Configuração: Use `@ConfigurationProperties` para vinculação de configuração com segurança de tipos (type-safe).
- Gerenciamento de Segredos: Externalize segredos usando variáveis de ambiente ou sistemas de gerenciamento de segredos.

### Organização do Código

- Estrutura de Pacotes: Organize por funcionalidade/domínio em vez de camadas.
- Separação de Preocupações: Mantenha os controllers enxutos, services focados e repository simples.
- Classes Utilitárias: Defina classes utilitárias como `final` com construtores privados.
- Utilizar sempre classes Mapper para traduzir Classes DTO em Classes Entidades

### Camada de Serviço

- Coloque a lógica de negócio em classes anotadas com `@Service`.
- Os serviços devem ser sem estado (stateless) e testáveis.
- Injete repositórios via construtor.
- As assinaturas dos métodos de serviço devem usar IDs de domínio ou DTOs, não expondo entidades de repositório diretamente, a menos que seja necessário.

### Logging

- Use SLF4J para todos os logs (`private static final Logger logger = LoggerFactory.getLogger(MinhaClasse.class);`).
- Não use implementações concretas (Logback, Log4j2) ou `System.out.println()` diretamente.
- Use logging parametrizado: `logger.info("Usuário {} logado", userId);`.

### Segurança e Manipulação de Entradas

- Use consultas parametrizadas | Sempre use Spring Data JPA ou `NamedParameterJdbcTemplate` para prevenir injeção de SQL.
- Valide corpos de requisição e parâmetros usando anotações JSR-380 (`@NotNull`, `@Size`, etc.) e `BindingResult`.

## Compilação e Verificação

- Após adicionar ou modificar código, verifique se o projeto continua a compilar com sucesso.
- Se o projeto usa Maven, execute `mvn clean package`.
- Se o projeto usa Gradle, execute `./gradlew build` (ou `gradlew.bat build` no Windows).
- Garanta que todos os testes passem como parte do build.

## Comandos Úteis


| Comando Gradle            | Comando Maven                     | Descrição                                     |
|:--------------------------|:----------------------------------|:----------------------------------------------|
| `./gradlew bootRun`       | `./mvnw spring-boot:run`           | Executa a aplicação.                          |
| `./gradlew build`         | `./mvnw package`                   | Compila a aplicação.                          |
| `./gradlew test`          | `./mvnw test`                      | Executa os testes.                            |
| `./gradlew bootJar`       | `./mvnw spring-boot:repackage`     | Empacota a aplicação como um JAR.             |
| `./gradlew bootBuildImage`| `./mvnw spring-boot:build-image`   | Empacota a aplicação como uma imagem container.|
