CREATE TABLE IF NOT EXISTS addresses (
	id              INT8 PRIMARY KEY NOT NULL UNIQUE GENERATED ALWAYS AS IDENTITY,
	country         VARCHAR(100)     NOT NULL,
	city            VARCHAR(100)     NOT NULL,
	postal_code     VARCHAR(30)      NOT NULL,
	street_name     VARCHAR(100)     NOT NULL,
	street_number   VARCHAR(30)      NOT NULL,
	flat_number     VARCHAR(30));