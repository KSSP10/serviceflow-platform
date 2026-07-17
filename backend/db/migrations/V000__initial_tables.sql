-- Initial migration stubs for Day 1
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS tenant (
  id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
  name text NOT NULL,
  created_at timestamptz DEFAULT now()
);

CREATE TABLE IF NOT EXISTS app_user (
  id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
  tenant_id uuid REFERENCES tenant(id) ON DELETE CASCADE,
  email text UNIQUE NOT NULL,
  password_hash text,
  role text,
  created_at timestamptz DEFAULT now()
);

CREATE TABLE IF NOT EXISTS customer (
  id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
  tenant_id uuid REFERENCES tenant(id) ON DELETE CASCADE,
  name text NOT NULL,
  primary_phone text,
  email text,
  created_at timestamptz DEFAULT now()
);

CREATE TABLE IF NOT EXISTS location (
  id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
  customer_id uuid REFERENCES customer(id) ON DELETE CASCADE,
  address_line1 text,
  city text,
  state text,
  postal_code text
);

CREATE TABLE IF NOT EXISTS job (
  id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
  tenant_id uuid REFERENCES tenant(id) ON DELETE CASCADE,
  customer_id uuid REFERENCES customer(id),
  location_id uuid REFERENCES location(id),
  status text,
  created_at timestamptz DEFAULT now()
);

CREATE TABLE IF NOT EXISTS appointment (
  id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
  job_id uuid REFERENCES job(id) ON DELETE CASCADE,
  start_time timestamptz,
  end_time timestamptz,
  arrival_window text
);
