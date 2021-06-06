-- Should be removed when post endpoint will be available

INSERT INTO car_types(name)
VALUES ('sedan');


INSERT INTO cars (vin, registration_number, brand, model, production_year, mileage, description, type_id)
VALUES ('testVin', 'EL00000', 'Mercedes-Benz', 'CLA', 2021, 10000, 'TestData', 1),
       ('testVin2', 'EL00001', 'Mercedes-Benz', 'CLA', 2021, 10000, 'TestData2', 1);
