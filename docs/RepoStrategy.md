# Repo Strategy

Recommendation: use a monorepo for initial development to ease coordination between frontend, backend, and realtime services.

Structure (top-level folders):
- `backend/` — Spring Boot microservices and DB migrations
- `frontend/` — React app(s)
- `realtime/` — Node.js WebSocket/SSE gateway or adapter
- `infrastructure/` — IaC, deploy scripts, Helm/CloudFormation
- `docs/` — design docs, backlog, runbooks

Why monorepo:
- Simplified local development and single `docker-compose.yml` for local stack.
- Easier cross-service refactors.

When to split: if services need independent release cycles or separate teams, consider splitting into multiple repos and using submodules or a package registry.
