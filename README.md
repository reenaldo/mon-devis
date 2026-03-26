# MonDevis API

A production-quality REST API for managing quotes (devis) built with Spring Boot. Designed for small businesses to manage their clients, quotes, and line items.

## Tech Stack

- **Java 17** + **Spring Boot 3.2**
- **Spring Web** -- REST controllers
- **Spring Data JPA** -- Hibernate ORM
- **PostgreSQL** -- relational database
- **Lombok** -- boilerplate reduction
- **Bean Validation** -- input validation
- **Maven** -- build tool

## Architecture

```
com.mondevis.api
├── controller/      REST endpoints
├── service/         Business logic interfaces
│   └── impl/        Service implementations
├── repository/      Spring Data JPA repositories
├── entity/          JPA entities
├── dto/             Request/Response DTOs
│   └── mapper/      Entity ↔ DTO mappers
├── exception/       Global error handling
└── MonDevisApplication.java
```

## Data Model

```
Client (1) ──── (N) Quote (1) ──── (N) QuoteItem
```

- **Client**: id, name, email
- **Quote**: id, date, status (DRAFT/SENT/ACCEPTED/REJECTED), client
- **QuoteItem**: id, description, quantity, unitPrice, quote

Total price is computed dynamically: `sum(quantity × unitPrice)`.

## API Endpoints

### Clients

| Method | URL              | Description          |
|--------|------------------|----------------------|
| POST   | `/clients`       | Create a client      |
| GET    | `/clients`       | List all clients     |
| GET    | `/clients/{id}`  | Get client by ID     |

### Quotes

| Method | URL                       | Description                  |
|--------|---------------------------|------------------------------|
| POST   | `/quotes`                 | Create a quote (DRAFT)       |
| GET    | `/quotes/{id}`            | Get quote by ID with items   |
| GET    | `/quotes/client/{clientId}` | List quotes for a client   |

### Quote Items

| Method | URL                          | Description              |
|--------|------------------------------|--------------------------|
| POST   | `/quotes/{quoteId}/items`    | Add item to a quote      |
| GET    | `/quotes/{quoteId}/items`    | List items of a quote    |

## Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL 14+

## Getting Started

### 1. Create the database

```sql
CREATE DATABASE mondevis;
```

### 2. Configure the connection

Edit `src/main/resources/application.yml` and adjust:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/mondevis
    username: postgres
    password: postgres
```

### 3. Build and run

```bash
mvn clean install
mvn spring-boot:run
```

The API starts on `http://localhost:8080`.

## Example Requests

### Create a client

```bash
curl -X POST http://localhost:8080/clients \
  -H "Content-Type: application/json" \
  -d '{"name": "Acme Corp", "email": "contact@acme.com"}'
```

### Create a quote

```bash
curl -X POST http://localhost:8080/quotes \
  -H "Content-Type: application/json" \
  -d '{"date": "2026-03-26", "clientId": 1}'
```

### Add an item to a quote

```bash
curl -X POST http://localhost:8080/quotes/1/items \
  -H "Content-Type: application/json" \
  -d '{"description": "Web Development", "quantity": 5, "unitPrice": 500.00}'
```

### Get a quote with items and total

```bash
curl http://localhost:8080/quotes/1
```

## Error Handling

All errors return a structured JSON response:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Client not found with id: 99",
  "timestamp": "2026-03-26T10:30:00"
}
```

Validation errors include field-level details:

```json
{
  "status": 400,
  "error": "Validation Failed",
  "message": "One or more fields are invalid",
  "timestamp": "2026-03-26T10:30:00",
  "validationErrors": {
    "email": "Email must be valid",
    "name": "Name is required"
  }
}
```
