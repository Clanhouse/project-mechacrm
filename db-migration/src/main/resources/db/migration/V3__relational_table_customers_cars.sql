CREATE TABLE IF NOT EXISTS customers_cars(
  id INT8 PRIMARY KEY UNIQUE GENERATED ALWAYS AS IDENTITY,
  customers_id INT8 not null references customers(id),
  car_id INT8 not null references cars(id)
);
