# Domain Model v1

This document lists primary entities and core fields to start Day 1 DB work.

- Tenant: id (uuid), name, created_at
- User: id (uuid), tenant_id (fk), email, password_hash, role, created_at
- Employee: id (uuid), user_id (fk), name, skills, territory
- Customer: id (uuid), tenant_id (fk), name, primary_phone, email, created_at
- Location: id (uuid), customer_id (fk), address_line1, city, state, postal_code
- Job: id (uuid), tenant_id (fk), customer_id (fk), location_id (fk), status, created_at
- Appointment: id (uuid), job_id (fk), start_time (timestamptz), end_time (timestamptz), arrival_window
- PricebookItem: id (uuid), tenant_id (fk), code, description, price_cents
- Estimate: id (uuid), job_id (fk), total_cents, created_at
- Invoice: id (uuid), job_id (fk), total_cents, status, due_date
- Payment: id (uuid), invoice_id (fk), amount_cents, method, status, paid_at

Relationships:
- Tenant 1..* Users, Customers, Jobs, PricebookItems
- Customer 1..* Locations
- Job 1..1 Appointment (for MVP) or 1..* for multi-visit
