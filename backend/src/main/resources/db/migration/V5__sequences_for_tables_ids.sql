--
--  CUSTOMERS TABLE
--

ALTER TABLE customers
	ALTER COLUMN id DROP IDENTITY;

CREATE SEQUENCE customers_seq;

ALTER TABLE customers
	ALTER COLUMN id SET DEFAULT nextval('customers_seq');

--
--  CARS TABLE
--

ALTER TABLE cars
	ALTER COLUMN id DROP IDENTITY;

CREATE SEQUENCE cars_seq;

ALTER TABLE cars
	ALTER COLUMN id SET DEFAULT nextval('cars_seq');

--
--  CUSTOMERS_CARS TABLE
--

ALTER TABLE customers_cars
	ALTER COLUMN id DROP IDENTITY;

CREATE SEQUENCE customers_cars_seq;

ALTER TABLE customers_cars
	ALTER COLUMN id SET DEFAULT nextval('customers_cars_seq');

--
--  CAR_TYPES TABLE
--

ALTER TABLE car_types
	ALTER COLUMN id DROP IDENTITY;

CREATE SEQUENCE car_types_seq;

ALTER TABLE car_types
	ALTER COLUMN id SET DEFAULT nextval('car_types_seq');

--
--  ACCOUNTS TABLE
--

ALTER TABLE accounts
	ALTER COLUMN id DROP IDENTITY;

CREATE SEQUENCE accounts_seq;

ALTER TABLE accounts
	ALTER COLUMN id SET DEFAULT nextval('accounts_seq');

--
--  ROLES TABLE
--

ALTER TABLE roles
	ALTER COLUMN id DROP IDENTITY;

CREATE SEQUENCE roles_seq;

ALTER TABLE roles
	ALTER COLUMN id SET DEFAULT nextval('roles_seq');