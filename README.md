# URL Shortener

Small Spring Boot service that creates and manages shortened URLs.

URL: https://roadmap.sh/projects/url-shortening-service

Overview
- Java 21, Spring Boot 4
- PostgreSQL for persistence
- OpenAPI/Swagger UI for API exploration
- Basic endpoints to create, retrieve, update, delete shortened URLs and to get hit count

Prerequisites
- Java 25~~~~ (JDK)
- Maven 3.6+
- PostgreSQL (or compatible JDBC URL)

Configuration
- Application properties are in `src/main/resources/application.yml`.
- The app reads DB connection from environment variables (defaults shown):
  - DB_URL (default: jdbc:postgresql://localhost:5432/urlshortener)
  - DB_USERNAME (default: postgres)
  - DB_PASSWORD (default: password)

Example (Linux / macOS)
export DB_URL=jdbc:postgresql://localhost:5432/urlshortener
export DB_USERNAME=postgres
export DB_PASSWORD=password

Example (Windows PowerShell)
$env:DB_URL="jdbc:postgresql://localhost:5432/urlshortener"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="password"

Build & Run
- Build:
  mvn clean package
- Run (using Spring Boot plugin):
  mvn spring-boot:run
- Or run the jar:
  java -jar target/url-shortener-0.0.1-SNAPSHOT.jar

API (base: http://localhost:8080/api/v1/urls)
- POST /shorten
  - Create a shortened URL.
  - Request body: { "url": "https://example.com" }
  - Response: 201 Created, body: { "url": "...", "shortCode": "abc123" }

- GET /shorten/{shortCode}
  - Retrieve the original URL for a short code.
  - Response: 200 OK, body: { "url": "...", "shortCode": "..." }

- PUT /shorten/{shortCode}
  - Update existing short code mapping.
  - Request body: { "url": "https://new.example.com" }
  - Response: 200 OK, updated mapping

- DELETE /shorten/{shortCode}
  - Delete a short code mapping.
  - Response: 204 No Content

- GET /shorten/count/{shortCode}
  - Get hit count for a short code.
  - Response: 200 OK, body: number

Quick curl examples
- Create:
  curl -X POST -H "Content-Type: application/json" -d '{"url":"https://example.com"}' http://localhost:8080/api/v1/urls/shorten

- Get:
  curl http://localhost:8080/api/v1/urls/shorten/{shortCode}

- Update:
  curl -X PUT -H "Content-Type: application/json" -d '{"url":"https://new.example.com"}' http://localhost:8080/api/v1/urls/shorten/{shortCode}

- Delete:
  curl -X DELETE http://localhost:8080/api/v1/urls/shorten/{shortCode}

API docs / Swagger
- OpenAPI JSON: http://localhost:8080/v3/api-docs
- Swagger UI: http://localhost:8080/swagger-ui.html or http://localhost:8080/swagger-ui/index.html

Actuator
- If actuator is enabled, endpoints are available under /actuator (configurable).

Notes
- The application uses a simple random short-code generator (7 chars, alphanumeric). Consider collision handling and analytics for production.
- Database schema is created/updated automatically by Hibernate (see `spring.jpa.hibernate.ddl-auto` in application.yml).

