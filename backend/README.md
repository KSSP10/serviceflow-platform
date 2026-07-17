# Backend (Spring Boot) - Quickstart

Run locally (requires Java 17 and Maven):

```bash
cd backend
./mvnw -DskipTests spring-boot:run
```

Config: the development profile reads DB settings from environment variables (see `application-dev.yml`).

Health endpoint: `GET /health` returns `OK`.
