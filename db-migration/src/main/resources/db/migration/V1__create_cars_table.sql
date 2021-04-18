CREATE TABLE IF NOT EXISTS car_types (
    car_id INT PRIMARY KEY UNIQUE GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS cars (
    car_id INT PRIMARY KEY UNIQUE GENERATED ALWAYS AS IDENTITY,
    vin VARCHAR(17) UNIQUE NOT NULL,
    registration_number VARCHAR(10),
    brand VARCHAR(30) NOT NULL,
    model VARCHAR(30) NOT NULL,
    production_year INT NOT NULL,
    mileage INT NOT NULL,
    description VARCHAR(250),
    CONSTRAINT fk_car_type
        FOREIGN KEY(car_id)
            REFERENCES car_types(car_id)
);