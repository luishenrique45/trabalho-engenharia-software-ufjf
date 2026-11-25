# 🚗 Gestão de Oficina Mecânica

Sistema desenvolvido para o Trabalho Prático da disciplina **DCC061 – Engenharia de Software** da **Universidade Federal de Juiz de Fora (UFJF)**.
* **Docente:** Prof. Dr. Fabrício Martins Mendonça
* **Temas Centrais Abordados:** Arquitetura de Software & Testes e Qualidade de Software

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem & Framework:** Java 25, Spring Boot 4.1
* **Segurança:** Spring Security (Criptografia BCrypt, Controle de Sessões)
* **Persistência de Dados:** Spring Data JPA, Hibernate, PostgreSQL
* **Front-end:** Thymeleaf, Bootstrap 5, Bootstrap Icons
* **Contêineres:** Docker & Docker Compose
* **Qualidade & Testes:** JUnit 5, Mockito

---

## 🐳 Como Subir o Banco de Dados com Docker

O projeto possui um arquivo `docker-compose.yml` pré-configurado com o PostgreSQL compatível com o `application.yaml`.

Para iniciar o banco de dados em segundo plano, abra o terminal na pasta raiz do projeto e execute:

```bash
docker-compose up -d
```

*(Ou `docker compose up -d`, dependendo da sua versão do Docker).*

Para verificar se o contêiner está rodando:
```bash
docker ps
```

Para parar o banco de dados quando terminar:
```bash
docker-compose down
```

---

## 🚀 Como Executar a Aplicação

1. Com o banco Docker em execução, inicie a aplicação via terminal:
   ```bash
   ./mvnw spring-boot:run
   ```
   *(Ou execute a classe `GestaoDeOficinaMecanicaApplication.java` diretamente pela sua IDE).*

2. Abra o navegador e acesse:
   👉 **[http://localhost:8080](http://localhost:8080)**

3. **Credenciais Iniciais (Criadas automaticamente pelo `DataInitializer`):**
   * **Usuário:** `admin`
   * **Senha:** `admin`

---

## 🧪 Como Executar os Testes Unitários

A aplicação conta com uma bateria de quase 50 testes unitários isolados com Mockito (sem necessidade de banco de dados ativo para rodar os testes).

Execute no terminal:
```bash
./mvnw test
```

---

## 🏛️ Padrões Arquiteturais e Regras de Negócio

* **Arquitetura em Camadas (*Layered Architecture*) + MVC:**
  * `controller`: Camada de apresentação e manipulação de requisições HTTP via Thymeleaf.
  * `service`: Regras de negócio estritas.
  * `database.repository`: Abstração de persistência com `JpaRepository`.
  * `database.model`: Entidades JPA e records DTO.
  * `exception`: Tratamento global de erros com `@ControllerAdvice` (`GlobalExceptionHandler`).

* **Regras de Negócio Validadas:**
  1. **Especialidade do Mecânico:** Um mecânico só pode ser atribuído a uma OS caso sua especialidade seja idêntica ao tipo de serviço da OS. Dispara `EspecialidadeIncompativelException`.
  2. **Controle de Fornecedores/Estoque:** Uma OS só transita para status `"Em Andamento"` se todas as peças vinculadas tiverem estoque maior que zero (`quantidadeEstoque > 0`). Dispara `PecaIndisponivelException`.
