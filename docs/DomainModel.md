# ServiceFlow Platform - Domain Model

## Overview

The ServiceFlow platform follows a multi-tenant SaaS architecture where each business operates independently while sharing the same application infrastructure.

The domain model represents the core business entities and their relationships.

---

# Core Entities

## Tenant

Represents a business organization using ServiceFlow.

Attributes:

- tenant_id
- business_name
- email
- phone
- address
- created_at


Relationships:

Tenant has many:
- Users
- Customers
- Employees
- Jobs


---

# User

Represents system users.

Attributes:

- user_id
- tenant_id
- first_name
- last_name
- email
- password
- role


Relationships:

User belongs to:
- One Tenant


---

# Customer

Represents customers who request services.

Attributes:

- customer_id
- tenant_id
- name
- email
- phone
- status


Relationships:

Customer belongs to:
- One Tenant

Customer has many:
- Locations
- Jobs
- Invoices


---

# Location

Represents customer service locations.

Attributes:

- location_id
- customer_id
- address
- city
- state
- zipcode


Relationships:

Location belongs to:
- One Customer


---

# Employee

Represents service technicians and staff.

Attributes:

- employee_id
- tenant_id
- name
- email
- phone
- role


Relationships:

Employee belongs to:
- One Tenant

Employee can have many:
- Jobs


---

# Job

Represents a service request.

Attributes:

- job_id
- tenant_id
- customer_id
- employee_id
- status
- scheduled_date
- description


Relationships:

Job belongs to:
- One Tenant
- One Customer
- One Employee


Job has:

- Appointment
- Estimate
- Invoice
- Payment


---

# Appointment

Represents scheduled service appointments.

Attributes:

- appointment_id
- job_id
- date
- start_time
- end_time
- status


Relationship:

Appointment belongs to:
- One Job


---

# Estimate

Represents pricing before service completion.

Attributes:

- estimate_id
- job_id
- amount
- status


Relationship:

Estimate belongs to:
- One Job


---

# Invoice

Represents customer billing.

Attributes:

- invoice_id
- customer_id
- job_id
- amount
- payment_status


Relationships:

Invoice belongs to:
- Customer
- Job


---

# Payment

Represents completed financial transactions.

Attributes:

- payment_id
- invoice_id
- amount
- payment_method
- payment_date


Relationship:

Payment belongs to:
- One Invoice


---

# Entity Relationship Summary


Tenant

|
|-- Users

|
|-- Customers

|     |
|     |-- Locations
|     |
|     |-- Jobs
|            |
|            |-- Appointment
|            |-- Estimate
|            |-- Invoice
|                    |
|                    |-- Payment


Tenant

|
|-- Employees

|
|-- Jobs


---

# Design Principles

## Multi-Tenancy

Every business entity contains tenant_id to maintain complete data isolation between businesses.

## Scalability

The model supports future modules:

- Dispatch management
- Memberships
- Notifications
- AI automation
- Reporting


## Security

Users can only access information belonging to their assigned tenant.