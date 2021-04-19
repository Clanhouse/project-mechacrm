CREATE TABLE IF NOT EXISTS roles(
    role_id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);
CREATE TABLE IF NOT EXISTS accounts(
    account_id SERIAL PRIMARY KEY,
    login VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255),
    email VARCHAR(100) NOT NULL UNIQUE,
    login_attempts INT,
    registration_date TIMESTAMP,
    last_successful_login TIMESTAMP,
    last_failed_login TIMESTAMP,
    role_id INT,
    CONSTRAINT fk_role
        FOREIGN KEY(role_id)
            REFERENCES roles(role_id)
);
