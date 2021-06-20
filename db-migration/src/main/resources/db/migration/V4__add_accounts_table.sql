CREATE TABLE IF NOT EXISTS roles
(
    id   INT8 PRIMARY KEY   NOT NULL UNIQUE GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts
(
    id                    INT8 PRIMARY KEY NOT NULL UNIQUE GENERATED ALWAYS AS IDENTITY,
    login                 VARCHAR(100)     NOT NULL UNIQUE,
    password              VARCHAR(255),
    email                 VARCHAR(100)     NOT NULL UNIQUE,
    login_attempts        INT,
    registration_date     TIMESTAMP,
    last_successful_login TIMESTAMP,
    last_failed_login     TIMESTAMP,
    role_id               INT8,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles (id)
);
