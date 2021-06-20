CREATE TABLE IF NOT EXISTS customers_cars (
	id           INT8 PRIMARY KEY UNIQUE GENERATED ALWAYS AS IDENTITY,
	customers_id INT8 NOT NULL REFERENCES customers (id),
	car_id       INT8 NOT NULL REFERENCES cars (id));
