create table customers_cars

(
    id INT PRIMARY KEY UNIQUE GENERATED ALWAYS AS IDENTITY,
    customers_id bigint not null
        references customers,

    car_id integer not null
        references cars

);