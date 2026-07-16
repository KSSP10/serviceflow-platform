# ServiceFlow Platform - Development Setup Guide

## Overview

This document explains how to set up the ServiceFlow platform development environment.

The platform consists of:

- React frontend application
- Java Spring Boot backend services
- PostgreSQL database
- MongoDB database
- Apache Kafka messaging system
- Docker-based deployment environment

---

# Prerequisites

Before starting development, install the following tools:

## Required Software

### Java Development Kit

Version:


Java 17+


Used for:

- Spring Boot backend development
- Microservices execution

---

### Node.js

Version:


Node.js 20+


Used for:

- React frontend development
- Package management

---

### Package Manager

Install:


npm


or


yarn


---

### Database Tools

Required:

- PostgreSQL
- MongoDB

---

### Development Tools

Recommended:

- Visual Studio Code
- IntelliJ IDEA
- Git
- Docker Desktop
- Postman

---

# Clone Repository

Clone the project:


git clone <repository-url>


Navigate:


cd serviceflow-platform


---

# Frontend Setup

## Navigate to Frontend


cd frontend


---

## Install Dependencies


npm install


---

## Configure Environment Variables

Create:


.env


Example:


REACT_APP_API_URL=http://localhost:8080/api/v1


---

## Start React Application

Run:


npm start


Frontend will run on:


http://localhost:3000


---

# Backend Setup

## Navigate to Backend


cd backend


---

## Configure Application Properties

Update:


application.properties


Example:


server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/serviceflow

spring.datasource.username=postgres

spring.datasource.password=password


---

## Build Backend

Using Maven:


mvn clean install


---

## Run Spring Boot Application


mvn spring-boot:run


Backend will run on:


http://localhost:8080


---

# Database Setup

## PostgreSQL Setup

Create database:


serviceflow


Run database migrations before starting the application.

---

## MongoDB Setup

Create database:


serviceflow_logs


Used for:

- Logs
- Analytics
- Event data

---

# Kafka Setup

Kafka is used for asynchronous communication.

Start Kafka services:


Zookeeper
Kafka Broker


Kafka topics:


job-events
appointment-events
invoice-events
payment-events
notification-events


---

# Docker Setup

Build containers:


docker-compose build


Start services:


docker-compose up


Stop services:


docker-compose down


---

# Development Workflow

Developer workflow:


Developer

|

Git Branch

|

Code Changes

|

Pull Request

|

Code Review

|

Merge

|

CI/CD Deployment


---

# Testing

Backend testing:


mvn test


Frontend testing:


npm test


---

# Environment Configuration

Different environments:

## Development

Used by developers.

## Testing

Used for QA validation.

## Production

Used by customers.

---

# Troubleshooting

Common issues:

## Database Connection Failure

Check:

- Database running
- Credentials
- Connection URL

---

## Frontend API Error

Check:

- Backend service status
- API URL configuration

---

## Docker Issues

Restart Docker services.

---

# Future Improvements

Planned:

- Automated local environment setup
- Kubernetes deployment
- Infrastructure automation
- Improved developer tooling