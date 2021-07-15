--Test data for car_types table
INSERT INTO car_types(name)
VALUES ('Sedan'),
       ('Hatchback'),
       ('Combi'),
       ('VAN'),
       ('Minivan'),
       ('Coupe'),
       ('SUV'),
       ('Pick-up'),
       ('Truck'),
       ('Other');

--Test data for cars table
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'JT3Z123KBW1589043', 'KR12PR', 'Toyota', 'Avensis', 2014,
                                  123456, 'Some description', id
                           FROM car_types
                           WHERE name = 'Combi');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'WVWZ321WBK1589044', 'EBEWM10', 'Volkswagen', 'Golf', 2013,
                                  115000, 'Some description', id
                           FROM car_types
                           WHERE name = 'Hatchback');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'JHWZ4H795FGU84012', 'WR34OP', 'Honda', 'Accord', 2012,
                                  168546, 'Some description', id
                           FROM car_types
                           WHERE name = 'Sedan');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'WBA86H90021859321', 'ELAS23', 'BMW', 'M5', 2020, 22500, '', id
                           FROM car_types
                           WHERE name = 'Sedan');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'WAU34891024573567', 'KR78L0', 'Audi', 'A4', 2018, 50123,
                                  'Some description', id
                           FROM car_types
                           WHERE name = 'Sedan');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'TMB1234FGH5678910', 'SK123F', 'Skoda', 'Octavia', 2010,
                                  260000, NULL, id
                           FROM car_types
                           WHERE name = 'Sedan');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'JTR58904GH9480230', 'GWE002H', 'Toyota', 'Corolla', 2005,
                                  282690, 'Some description', id
                           FROM car_types
                           WHERE name = 'Sedan');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'JTW2345O900039001', 'DW349W', 'Toyota', 'Avensis', 2007,
                                  150990, 'Some description', id
                           FROM car_types
                           WHERE name = 'Combi');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'VF7Z5670999910000', 'LJA346J', 'Citroen', 'C3', 2011,
                                  122000, 'Some description', id
                           FROM car_types
                           WHERE name = 'Hatchback');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'VF1F9H9HF3H989890', 'BS45PR', 'Renault', 'Kangoo', 2008,
                                  261459, 'Some description', id
                           FROM car_types
                           WHERE name = 'VAN');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'WVWFH749388504385', 'DLE21PL', 'Volkswagen', 'Passat', 1998,
                                  358600, 'Some description', id
                           FROM car_types
                           WHERE name = 'Combi');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'VF1F740000GK99303', 'EPA11XD', 'Renault', 'Traffic', 2020,
                                  15000, 'Some description', id
                           FROM car_types
                           WHERE name = 'VAN');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT '5YJ3456JKFD900028', 'GD230P', 'Tesla', 'Model 3', 2019,
                                  36900, NULL, id
                           FROM car_types
                           WHERE name = 'Sedan');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'W0LA2345670000123', 'ONY33PL', 'Opel', 'Astra', 2015, 88123,
                                  'Some description', id
                           FROM car_types
                           WHERE name = 'Sedan');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'W0L0435993R034999', 'WW11JU', 'Opel', 'Zafira', 2014, 63500,
                                  'Some description', id
                           FROM car_types
                           WHERE name = 'Minivan');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'TMBO0390000012345', 'SH35PR', 'Skoda', 'Superb', 2014,
                                  45200, 'Some description', id
                           FROM car_types
                           WHERE name = 'Sedan');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'KNKOP569002920930', 'SK21RP', 'Kia', 'Ceed', 2012, 123123,
                                  NULL, id
                           FROM car_types
                           WHERE name = 'Combi');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'TMB67890123456452', 'SC33FT', 'Skoda', 'Fabia', 2008,
                                  231321, 'Some description', id
                           FROM car_types
                           WHERE name = 'Hatchback');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'WAU47959HDH900045', 'LJA21BL', 'Audi', 'A3', 2002, 290900,
                                  'Some description', id
                           FROM car_types
                           WHERE name = 'Sedan');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT '1FBZ121200GHJK123', 'EBE14CC', 'Ford', 'Focus', 2004,
                                  245630, 'Some description', id
                           FROM car_types
                           WHERE name = 'Hatchback');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'VSSZ12GD4830J9994', 'WL003R', 'Seat', 'Leon', 2005, 190637,
                                  '', id
                           FROM car_types
                           WHERE name = 'Hatchback');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'SAL009123FGHHJ002', 'WW89EK', 'Land Rover', 'Range Rover',
                                  2014, 100000, 'Some description', id
                           FROM car_types
                           WHERE name = 'SUV');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'JNW12300KL8945029', 'WWGH00', 'Nissan', 'Navara', 2010,
                                  155000, 'Some description', id
                           FROM car_types
                           WHERE name = 'Pick-up');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'VF1Z123KRH408UH33', 'WS90WQ', 'Renault', 'Scenic', 2009,
                                  210000, 'Some description', id
                           FROM car_types
                           WHERE name = 'Minivan');
INSERT INTO cars(vin, registration_number, brand, model, production_year, mileage, description,
                 type_id) (SELECT 'WP0122347650FH44F', 'KR22RT', 'Porsche', 'Carrera', 2006,
                                  269350, 'Some description', id
                           FROM car_types
                           WHERE name = 'Coupe');

--Test data for customers table
INSERT INTO customers(name, surname, phone, address)
VALUES ('Jan', 'Kowalski', '665456987', 'Kaliska 12, Bełchatów'),
       ('Maciej', 'Zalewski', '126434567', 'Warszawska 15, Kraków'),
       ('Aneta', 'Korczyńska', '789456123', 'Miodowa 23, Nysa'),
       ('Rafał', 'Nowak', '654321987', 'Piłsudskiego 20, Warszawa'),
       ('Marta', 'Lewicka', '660369852', 'Antracytowa 56, Kraków'),
       ('Bartosz', 'Lewicki', '721435678', 'Antracytowa 56, Kraków'),
       ('Kamil', 'Kwiatkowski', '620908901', 'Wiśniowa 10, Siedlce'),
       ('Kamila', 'Baran', '567890121', 'Dębowa 10, Częstochowa'),
       ('Magda', 'Kwiatkowska', '500100890', 'Wojska Polskiego 19, Warszawa'),
       ('Jan', 'Kowalski', '882555444', 'Fabryczna 10, Piotrków Trybunalski'),
       ('Jan', 'Nowak', '666555444', 'Kolorowa 79, Łódź'),
       ('Mariusz', 'Drągowski', '789456000', 'Leśna 60, Wrocław'),
       ('Anna', 'Wolska', '802134678', 'Sienkiewicza 2, Bełchatów'),
       ('Aneta', 'Nowak', '620908900', 'Mickiewicza 50, Katowice'),
       ('Adrian', 'Stasiak', '777888999', 'Zielona 33, Janów Lubelski'),
       ('Agnieszka', 'Stasiak', '745966365', 'Topolowa 20, Rzeszów'),
       ('Marcin', 'Wolski', '555444333', 'Lipińskiego 45, Poznań'),
       ('Ireneusz', 'Krzemiński', '888777666', 'Cebulowa 7, Wejherowo'),
       ('Beata', 'Pawlak', '789454987', 'Boczna 120, Katowice'),
       ('Jerzy', 'Mazur', '521321457', 'Zamknięta 1, Suwałki'),
       ('Kacper', 'Lewandowski', '504264111', 'Złota 21, Legnica'),
       ('Bartosz', 'Izdebski', '741852963', 'Pomorska 17, Gdynia'),
       ('Adam', 'Grzanka', '600400900', 'Srebrna 40, Warszawa'),
       ('Anna', 'Kolanko', '852741963', 'Srebrna 40, Warszawa'),
       ('Jacek', 'Górnik', '636696000', 'Obozowa 25, Wrocław');

--Test data for customers_cars table
INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'WVWZ321WBK1589044')
                                                  FROM customers
                                                  WHERE phone = '665456987');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'JT3Z123KBW1589043')
                                                  FROM customers
                                                  WHERE phone = '126434567');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'W0LA2345670000123')
                                                  FROM customers
                                                  WHERE phone = '789456123');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'JHWZ4H795FGU84012')
                                                  FROM customers
                                                  WHERE phone = '654321987');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'W0L0435993R034999')
                                                  FROM customers
                                                  WHERE phone = '654321987');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'WAU34891024573567')
                                                  FROM customers
                                                  WHERE phone = '660369852');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'WAU34891024573567')
                                                  FROM customers
                                                  WHERE phone = '721435678');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'SAL009123FGHHJ002')
                                                  FROM customers
                                                  WHERE phone = '620908901');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'JNW12300KL8945029')
                                                  FROM customers
                                                  WHERE phone = '620908901');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'VF1Z123KRH408UH33')
                                                  FROM customers
                                                  WHERE phone = '620908901');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'TMB67890123456452')
                                                  FROM customers
                                                  WHERE phone = '567890121');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'VSSZ12GD4830J9994')
                                                  FROM customers
                                                  WHERE phone = '500100890');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'VF1F740000GK99303')
                                                  FROM customers
                                                  WHERE phone = '882555444');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'WBA86H90021859321')
                                                  FROM customers
                                                  WHERE phone = '666555444');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'JTW2345O900039001')
                                                  FROM customers
                                                  WHERE phone = '789456000');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'WP0122347650FH44F')
                                                  FROM customers
                                                  WHERE phone = '802134678');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'KNKOP569002920930')
                                                  FROM customers
                                                  WHERE phone = '620908900');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'TMB1234FGH5678910')
                                                  FROM customers
                                                  WHERE phone = '620908900');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'VF7Z5670999910000')
                                                  FROM customers
                                                  WHERE phone = '777888999');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'WAU47959HDH900045')
                                                  FROM customers
                                                  WHERE phone = '777888999');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'VF7Z5670999910000')
                                                  FROM customers
                                                  WHERE phone = '745966365');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = '1FBZ121200GHJK123')
                                                  FROM customers
                                                  WHERE phone = '555444333');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'JTR58904GH9480230')
                                                  FROM customers
                                                  WHERE phone = '888777666');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'TMBO0390000012345')
                                                  FROM customers
                                                  WHERE phone = '789454987');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'VF1F9H9HF3H989890')
                                                  FROM customers
                                                  WHERE phone = '521321457');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'WVWFH749388504385')
                                                  FROM customers
                                                  WHERE phone = '504264111');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = '5YJ3456JKFD900028')
                                                  FROM customers
                                                  WHERE phone = '741852963');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'WP0122347650FH44F')
                                                  FROM customers
                                                  WHERE phone = '600400900');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'WP0122347650FH44F')
                                                  FROM customers
                                                  WHERE phone = '852741963');

INSERT INTO customers_cars(customers_id, car_id) (SELECT id, (SELECT id FROM cars WHERE vin = 'JTW2345O900039001')
                                                  FROM customers
                                                  WHERE phone = '636696000');

--Test data for roles table
INSERT INTO roles(name)
VALUES ('admin'),
       ('user');

--Test data for account table
INSERT INTO accounts(login, password, email, login_attempts, registration_date, last_successful_login,
                     last_failed_login, role_id) (SELECT 'admin',
                                                         '$2y$12$v97WyUOPeTyHw55h4Pm3WeiRyCRfVne8LnFhq7Vg7bRR6b2uWCUoa',
                                                         'admin@mail.com', 6,
                                                         '2021-05-24 16:00:00',
                                                         '2021-05-26 16:00:00',
                                                         '2021-05-25 16:00:00', id
                                                  FROM roles
                                                  WHERE name = 'admin');

INSERT INTO accounts(login, password, email, login_attempts, registration_date, last_successful_login,
                     last_failed_login, role_id) (SELECT 'user',
                                                         '$2y$12$/EGOACr4sihV98Pl6mfjTeFZMI1O6bE1bDpa7UudeKdQIeVFmlQFe',
                                                         'user@mail.com', 5,
                                                         '2021-05-24 14:00:00',
                                                         '2021-05-26 14:00:00',
                                                         '2021-05-25 14:00:00', id
                                                  FROM roles
                                                  WHERE name = 'user');

