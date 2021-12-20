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

--Test data for addresses table
INSERT INTO addresses(country, city, postal_code, street_name, street_number, flat_number)
VALUES ('Poland', 'Bełchatów', '00-000', 'Kaliska', '12', null),
       ('Poland', 'Kraków', '00-000', 'Warszawska', '15', null),
       ('Poland', 'Nysa', '', 'Miodowa', '23', null),
       ('Poland', 'Warszawa', '00-000', 'Piłsudskiego', '20A', '15'),
       ('Poland', 'Kraków', '', 'Antracytowa', '56', null),
       ('Poland', 'Siedlce', '00-000', 'Wiśniowa', '10B', null),
       ('Poland', 'Częstochowa', '00-000', 'Dębowa', '10', '68'),
       ('Poland', 'Warszawa', '00-000', 'Wojska Polskiego', '19', null),
       ('Poland', 'Piotrków Tryb.', '', 'Fabryczna', '10', null),
       ('Poland', 'Łodź', '00-000', 'Kolorowa', '79', null),
       ('Poland', 'Wrocław', '', 'Leśna', '60', '10'),
       ('Poland', 'Bełchatów', '00-000', 'Sienkiewicza', '2', null),
       ('Poland', 'Katowice', '00-000', 'Mickiewicza', '50', null),
       ('Poland', 'Janów Lubelski', '00-000', 'Zielona', '33', '12'),
       ('Poland', 'Rzeszów', '00-000', 'Topolowa', '20', null),
       ('Poland', 'Poznań', '00-000', 'Lipińskiego', '45', '10'),
       ('Poland', 'Wejherowo', '', 'Cebulowa', '7', null),
       ('Poland', 'Katowice', '00-000', 'Boczna', '120', null),
       ('Poland', 'Suwałki', '00-000', 'Zamknięta', '1', null),
       ('Poland', 'Legnica', '00-000', 'Złota', '21', null),
       ('Poland', 'Gdynia', '00-000', 'Pomorska', '17', '3'),
       ('Poland', 'Warszawa', '00-000', 'Srebrna', '40', null);

--Test data for customers table
INSERT INTO customers(name, surname, phone, address_id)
VALUES ('Jan', 'Kowalski', '665456987', 1),
       ('Maciej', 'Zalewski', '126434567', 2),
       ('Aneta', 'Korczyńska', '789456123', 3),
       ('Rafał', 'Nowak', '654321987', 4),
       ('Marta', 'Lewicka', '660369852', 5),
       ('Bartosz', 'Lewicki', '721435678', 5),
       ('Kamil', 'Kwiatkowski', '620908901', 6),
       ('Kamila', 'Baran', '567890121', 7),
       ('Magda', 'Kwiatkowska', '500100890', 8),
       ('Jan', 'Kowalski', '882555444', 9),
       ('Jan', 'Nowak', '666555444', 10),
       ('Mariusz', 'Drągowski', '789456000', 11),
       ('Anna', 'Wolska', '802134678', 12),
       ('Aneta', 'Nowak', '620908900', 13),
       ('Adrian', 'Stasiak', '777888999', 14),
       ('Agnieszka', 'Stasiak', '745966365', 15),
       ('Marcin', 'Wolski', '555444333', 16),
       ('Ireneusz', 'Krzemiński', '888777666', 17),
       ('Beata', 'Pawlak', '789454987', 18),
       ('Jerzy', 'Mazur', '521321457', 19),
       ('Kacper', 'Lewandowski', '504264111', 20),
       ('Bartosz', 'Izdebski', '741852963', 21),
       ('Adam', 'Grzanka', '600400900', 22),
       ('Anna', 'Kolanko', '852741963', 22),
       ('Jacek', 'Górnik', '636696000', null);

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
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

--Test data for account table
INSERT INTO accounts(login, password, email, login_attempts, registration_date, last_successful_login,
                     last_failed_login, role_id, is_active) (SELECT 'admin',
                                                         '$2y$12$v97WyUOPeTyHw55h4Pm3WeiRyCRfVne8LnFhq7Vg7bRR6b2uWCUoa',
                                                         'admin@mail.com', 6,
                                                         '2021-05-24 16:00:00',
                                                         '2021-05-26 16:00:00',
                                                         '2021-05-25 16:00:00', id, true
                                                  FROM roles
                                                  WHERE name = 'ROLE_ADMIN');

INSERT INTO accounts(login, password, email, login_attempts, registration_date, last_successful_login,
                     last_failed_login, role_id, is_active) (SELECT 'user',
                                                         '$2y$12$/EGOACr4sihV98Pl6mfjTeFZMI1O6bE1bDpa7UudeKdQIeVFmlQFe',
                                                         'user@mail.com', 5,
                                                         '2021-05-24 14:00:00',
                                                         '2021-05-26 14:00:00',
                                                         '2021-05-25 14:00:00', id, true
                                                  FROM roles
                                                  WHERE name = 'ROLE_USER');

INSERT INTO accounts(id, login, password, email, login_attempts, registration_date, role_id, is_active) (SELECT 91919191, 'user2',
                                                                       '$2a$10$ZW1GDjwa/8nJP.FbhMr7vuh0L4tqU.PMaFAMnCOTVqpkUB9xlcuSu',
                                                                       'user2@mail.com', 0,
                                                                       '2021-05-24 14:00:00',
                                                                        id, false
                                                                FROM roles
                                                                WHERE name = 'ROLE_USER');

INSERT INTO accounts(login, password, email, login_attempts, registration_date, role_id, is_active) (SELECT 'user3',
                                                                                                               '$2a$10$/WyYH3xYwdfYC23Xucxx6O.Dqtw4yqoWQ/9bIWhax.d1wyVYge1oG',
                                                                                                               'user3@mail.com', 0,
                                                                                                               '2021-05-24 14:00:00',
                                                                                                               id, false
                                                                                                        FROM roles
                                                                                                        WHERE name = 'ROLE_USER');

INSERT INTO verification_tokens(token, account_id, expiry_date) values ('af1aca10-e04c-4ea5-a6ed-56a777c22d99', 91919191, '2050-10-10 23:58:59')

