CREATE SEQUENCE verification_tokens_seq;

CREATE TABLE IF NOT EXISTS verification_tokens (
    id                  INT8 PRIMARY KEY UNIQUE DEFAULT nextval('verification_tokens_seq'),
    token               VARCHAR(100) UNIQUE NOT NULL,
    account_id          INT8 NOT NULL,
    expiry_date         TIMESTAMP        NOT NULL,
    CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES accounts (id));
