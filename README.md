# TodoList API

## Visão Geral

O **TodoList** é uma API REST desenvolvida em **Java com Spring Boot**, cujo objetivo é fornecer um backend simples e bem estruturado para o gerenciamento de tarefas (*tasks*). O projeto foi criado com foco em **boas práticas**, **código limpo**, **uso de DTOs imutáveis (`record`)** e **arquitetura em camadas**.

A aplicação utiliza **H2 em modo persistente**, garantindo que os dados não sejam perdidos após o restart da aplicação, e conta com **Swagger/OpenAPI** para documentação e testes dos endpoints.

---

## Arquitetura do Projeto

O projeto segue uma arquitetura em camadas:

* **Controller**: responsável por expor os endpoints REST
* **Service**: camada de regra de negócio
* **Repository**: acesso a dados com Spring Data JPA
* **DTO**: objetos de transferência de dados usando `record`
* **Entity**: mapeamento JPA das tabelas

```
Controller → Service → Repository → Database
```

Essa separação garante:

* Manutenibilidade
* Testabilidade
* Baixo acoplamento

---

## Tecnologias Utilizadas

* Java 17+
* Spring Boot 3.x
* Spring Web
* Spring Data JPA
* H2 Database (persistente)
* Springdoc OpenAPI (Swagger)
* Maven

---

## Modelagem de Dados

### Entity: Task

A entidade representa uma tarefa no sistema.

Principais atributos:

* `id` (Long)
* `name` (String)
* `description` (String)

---

## DTOs

O projeto utiliza **DTOs com `record`**, seguindo boas práticas de imutabilidade.

Exemplo:

```java
public record TaskDTO(String name, String description) {}
```

Benefícios:

* Código mais conciso
* Imutabilidade
* Clareza na API
* Evita exposição direta da entidade

---

## Endpoints da API

### Task Controller

| Método | Endpoint      | Descrição              |
| ------ | ------------- | ---------------------- |
| GET    | `/tasks`      | Lista todas as tarefas |
| GET    | `/tasks/{id}` | Busca tarefa por ID    |
| POST   | `/tasks`      | Cria uma nova tarefa   |
| PUT    | `/tasks/{id}` | Atualiza uma tarefa    |
| DELETE | `/tasks/{id}` | Remove uma tarefa      |

---

## Documentação com Swagger

Após subir a aplicação, a documentação estará disponível em:

```
http://localhost:8080/swagger-ui.html
```

ou

```
http://localhost:8080/swagger-ui/index.html
```

Através do Swagger é possível:

* Visualizar todos os endpoints
* Testar requisições
* Ver schemas de entrada e saída

---

## Banco de Dados

O projeto utiliza **H2 em modo persistente (file)**.

### Console H2

```
http://localhost:8080/h2-console
```

Configurações:

* JDBC URL: `jdbc:h2:file:./data/taskdb`
* Username: `sa`
* Password: *(vazio)*

---

## Configurações Importantes

### application.properties (exemplo)

```
spring.datasource.url=jdbc:h2:file:./data/taskdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

---

## Como Executar o Projeto

### Pré-requisitos

* Java JDK 17 ou superior
* Maven

### Passos

```bash
git clone https://github.com/jeanmts/TodoList.git
cd TodoList
mvn spring-boot:run
```

---

## Exemplos de Requisição

### Criar Task

```http
POST /tasks
Content-Type: application/json

{
  "name": "Estudar Spring",
  "description": "Revisar Service e Repository"
}
```

### Listar Tasks

```http
GET /tasks
```

---

## Boas Práticas Aplicadas

* Injeção de dependência por construtor
* Uso de `record` para DTOs
* Separação de responsabilidades
* Não exposição direta das entidades
* Código compatível com Java moderno

---

## Possíveis Evoluções

* Validação com Bean Validation
* Paginação e ordenação
* Autenticação com Spring Security
* Migração para PostgreSQL
* Testes unitários e de integração

---

## Licença

Este projeto está sob a licença MIT.

---

## Autor

Jean Carlos Santos Matos dos Santos
GitHub: [https://github.com/jeanmts](https://github.com/jeanmts)

