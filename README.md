
---
````markdown
# 💰 Desafio NG Billing

Este projeto é um desafio técnico que simula um sistema de cobrança (billing), utilizando Quarkus, PostgreSQL e Docker.

---

## ⚙️ Tecnologias utilizadas

- 🔥 [Quarkus](https://quarkus.io/) — Framework Java para microservices e APIs modernas
- 🐘 PostgreSQL — Banco de dados relacional
- 🧩 Hibernate ORM — Mapeamento objeto-relacional (JPA)
- ✅ Jakarta Bean Validation — Validação de dados
- 🐳 Docker & Docker Compose — Contêinerização do banco de dados

---

## 🚀 Como rodar o projeto

### 🔹 1. Acesse a pasta `docker`

```bash
cd src/main/docker
````

### 🔹 2. Suba os containers com Docker Compose

```bash
docker compose up -d
```

Esse comando iniciará o container do PostgreSQL na porta `5432`, com as configurações definidas no `docker-compose.yml`.

### 🔹 3. Crie o schema no banco de dados

Com o container em execução, execute o comando abaixo para garantir que o schema `ngbilling` exista no banco:

```bash
docker exec -it postgresNgBilling psql -U postgres -d ngbilling -c "CREATE SCHEMA IF NOT EXISTS ngbilling;"
```

> ⚠️ Este passo é importante para que o Quarkus consiga gerar as tabelas corretamente.

### 🔹 4. Execute a aplicação

Agora que o banco de dados está pronto, você pode iniciar a aplicação em modo de desenvolvimento:

```bash
./mvnw quarkus:dev
```

Ou, se preferir:

```bash
mvn compile quarkus:dev
```

A API estará disponível em:
📍 [`http://localhost:8080`](http://localhost:8080) ou em
[`swagger`](http://localhost:8080/q/swagger-ui/#/)
---

## 🗃️ Estrutura esperada do projeto

```
ng-billing/
├── docker/
│   └── docker-compose.yml
├── src/
│   └── main/
│       ├── java/
│       └── resources/
│           └── import.sql
├── README.md
└── pom.xml
```


