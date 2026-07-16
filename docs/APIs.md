# ServiceFlow Platform - API Documentation

## Overview

ServiceFlow provides RESTful APIs for managing service business operations.

The API layer handles:

- User authentication
- Customer management
- Employee management
- Job management
- Appointment scheduling
- Estimates
- Invoices
- Payments
- Notifications
- Reporting

All APIs use JSON format and follow REST architecture principles.

---

# API Base URL

Development:

http://localhost:8080/api/v1

Production:

https://api.serviceflow.com/api/v1

---

# Authentication

All secured APIs use JWT authentication.

Request Header:

Authorization: Bearer <JWT_TOKEN>

---

# Authentication APIs

## Login User

POST /auth/login

Description:
Authenticates a user and returns an access token.

Request:

{
 "email": "admin@serviceflow.com",
 "password": "password"
}

Response:

{
 "token": "jwt-token",
 "role": "ADMIN"
}

---

# Customer APIs

## Create Customer

POST /customers

Creates a new customer.

---

## Get Customers

GET /customers

Returns all customers for the business.

---

## Get Customer By ID

GET /customers/{customerId}

Returns customer details.

---

## Update Customer

PUT /customers/{customerId}

Updates customer information.

---

## Delete Customer

DELETE /customers/{customerId}

Deletes customer information.

---

# Employee APIs

## Create Employee

POST /employees

Creates a new employee or technician.

---

## Get Employees

GET /employees

Returns all employees.

---

## Update Employee

PUT /employees/{employeeId}

Updates employee details.

---

# Job APIs

## Create Job

POST /jobs

Creates a new service request.

Example:

{
 "customerId":101,
 "employeeId":10,
 "description":"AC Repair",
 "status":"CREATED"
}

---

## Get Jobs

GET /jobs

Returns all jobs.

---

## Get Job By ID

GET /jobs/{jobId}

Returns job details.

---

## Update Job Status

PUT /jobs/{jobId}/status

Job statuses:

- CREATED
- ASSIGNED
- IN_PROGRESS
- COMPLETED
- CANCELLED

---

# Appointment APIs

## Create Appointment

POST /appointments

Schedules customer service.

---

## Get Appointment

GET /appointments/{appointmentId}

Returns appointment details.

---

# Estimate APIs

## Create Estimate

POST /estimates

Creates service estimate.

---

## Get Estimate

GET /estimates/{estimateId}

Returns estimate information.

---

# Invoice APIs

## Create Invoice

POST /invoices

Creates customer invoice.

---

## Get Invoice

GET /invoices/{invoiceId}

Returns invoice details.

---

# Payment APIs

## Process Payment

POST /payments

Records customer payment.

---

## Payment History

GET /payments/customer/{customerId}

Returns payment history.

---

# Notification APIs

## Send Notification

POST /notifications

Sends:

- Email notifications
- SMS notifications
- Push notifications

---

# Reporting APIs

## Dashboard Reports

GET /reports/dashboard

Provides:

- Revenue reports
- Job statistics
- Customer analytics
- Employee performance

---

# Error Handling

| Code | Description |
|------|-------------|
|200|Successful Request|
|201|Created|
|400|Bad Request|
|401|Unauthorized|
|403|Forbidden|
|404|Not Found|
|500|Server Error|

---

# Security

The API platform uses:

- JWT Authentication
- Role Based Access Control
- Tenant Data Isolation
- HTTPS Encryption
- Input Validation

---

# Future Enhancements

Planned APIs:

- AI Recommendations
- Smart Scheduling
- Advanced Analytics
- Mobile Application APIs