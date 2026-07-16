# ServiceFlow OS Architecture

## Technology Stack

### Frontend

* React 19
* TypeScript
* Material UI
* React Router
* React Query

### Backend

* Java 21
* Spring Boot 3.x
* Spring Security
* JWT Authentication

### Realtime

* Node.js
* Socket.IO

### Database

* PostgreSQL

### Cloud

* AWS ECS
* AWS RDS
* S3
* CloudFront

---

## High-Level Architecture

React Frontend

↓

Spring Boot APIs

↓

PostgreSQL Database

↓

Node.js Realtime Service

↓

WebSocket Updates

---

## Core Services

### Auth Service

* Login
* JWT
* RBAC

### CRM Service

* Customer Management
* Location Management

### Job Service

* Job Lifecycle
* Scheduling
* Dispatching

### Billing Service

* Estimates
* Invoices
* Payments

### Notification Service

* Email Notifications

### AI Service

* Intake Summary
* Scheduling Suggestions
* Technician Recommendations
