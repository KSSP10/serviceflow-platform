# ServiceFlow OS Domain Model

## Tenant

A tenant represents a company using the platform.

Example:

ABC HVAC Services

---

## User

A system user.

Roles:

* Admin
* CSR
* Dispatcher
* Technician
* Manager

---

## Customer

A customer receiving services.

A customer can have:

* Multiple Locations
* Multiple Jobs
* Multiple Invoices

---

## Location

Service address.

Examples:

* Home
* Office
* Rental Property

---

## Job

A service request.

Examples:

* AC Repair
* Water Heater Installation
* Electrical Inspection

---

## Appointment

Scheduled time slot for a job.

---

## Employee

Field technician or office employee.

---

## Estimate

Pricing proposal sent to customer.

---

## Invoice

Bill generated after work completion.

---

## Payment

Money received for invoice.

---

## Relationships

Tenant
├── Users
├── Customers
├── Employees
└── Jobs

Customer
├── Locations
├── Jobs
└── Invoices

Job
├── Appointment
├── Estimate
├── Invoice
└── Payment
