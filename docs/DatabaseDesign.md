# ServiceFlow OS Database Design

## Multi-Tenant Architecture

Every business-related table contains:

tenant_id

This ensures data isolation between customers.

---

## Table: tenants

| Column     | Type         |
| ---------- | ------------ |
| id         | UUID         |
| name       | VARCHAR(255) |
| status     | VARCHAR(50)  |
| created_at | TIMESTAMP    |

---

## Table: users

| Column        | Type         |
| ------------- | ------------ |
| id            | UUID         |
| tenant_id     | UUID         |
| first_name    | VARCHAR(100) |
| last_name     | VARCHAR(100) |
| email         | VARCHAR(255) |
| password_hash | VARCHAR(500) |
| role_id       | UUID         |
| active        | BOOLEAN      |
| created_at    | TIMESTAMP    |

---

## Table: customers

| Column     | Type         |
| ---------- | ------------ |
| id         | UUID         |
| tenant_id  | UUID         |
| first_name | VARCHAR(100) |
| last_name  | VARCHAR(100) |
| email      | VARCHAR(255) |
| phone      | VARCHAR(20)  |
| created_at | TIMESTAMP    |

---

## Table: locations

| Column        | Type         |
| ------------- | ------------ |
| id            | UUID         |
| customer_id   | UUID         |
| address_line1 | VARCHAR(255) |
| city          | VARCHAR(100) |
| state         | VARCHAR(50)  |
| zip_code      | VARCHAR(20)  |

---

## Table: employees

| Column     | Type         |
| ---------- | ------------ |
| id         | UUID         |
| tenant_id  | UUID         |
| first_name | VARCHAR(100) |
| last_name  | VARCHAR(100) |
| role       | VARCHAR(50)  |
| territory  | VARCHAR(100) |

---

## Table: jobs

| Column               | Type        |
| -------------------- | ----------- |
| id                   | UUID        |
| tenant_id            | UUID        |
| customer_id          | UUID        |
| location_id          | UUID        |
| assigned_employee_id | UUID        |
| description          | TEXT        |
| priority             | VARCHAR(50) |
| status               | VARCHAR(50) |

---

## Table: appointments

| Column         | Type        |
| -------------- | ----------- |
| id             | UUID        |
| job_id         | UUID        |
| start_time     | TIMESTAMP   |
| end_time       | TIMESTAMP   |
| arrival_window | VARCHAR(50) |

---

## Table: estimates

| Column       | Type          |
| ------------ | ------------- |
| id           | UUID          |
| job_id       | UUID          |
| total_amount | DECIMAL(10,2) |
| status       | VARCHAR(50)   |

---

## Table: invoices

| Column       | Type          |
| ------------ | ------------- |
| id           | UUID          |
| job_id       | UUID          |
| total_amount | DECIMAL(10,2) |
| status       | VARCHAR(50)   |

---

## Table: payments

| Column         | Type          |
| -------------- | ------------- |
| id             | UUID          |
| invoice_id     | UUID          |
| amount         | DECIMAL(10,2) |
| payment_method | VARCHAR(50)   |
| payment_date   | TIMESTAMP     |
