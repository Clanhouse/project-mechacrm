ALTER TABLE addresses
	ALTER COLUMN id DROP IDENTITY;

CREATE SEQUENCE addresses_seq;

ALTER TABLE addresses
	ALTER COLUMN id SET DEFAULT nextval('addresses_seq');