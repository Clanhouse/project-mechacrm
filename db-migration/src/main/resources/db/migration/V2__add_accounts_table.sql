CREATE TABLE roles
(
    role_id BIGSERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL
);

CREATE TABLE accounts
(
    account_id BIGSERIAL PRIMARY KEY,
    login TEXT NOT NULL UNIQUE ,
    password TEXT,
    email TEXT NOT NULL UNIQUE,
    login_attempts INT,
    registration_date TIMESTAMP ,
    last_succesful_login TIMESTAMP ,
    last_failed_login TIMESTAMP ,
    role_id INT,

    CONSTRAINT fk_role
        FOREIGN KEY(role_id)
            REFERENCES roles(role_id)
);




