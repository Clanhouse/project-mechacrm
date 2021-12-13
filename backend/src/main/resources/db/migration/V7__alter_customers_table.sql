ALTER TABLE customers
    RENAME COLUMN address TO address_id;

ALTER TABLE customers
    ALTER COLUMN address_id TYPE INT8 USING address_id::bigint;

ALTER TABLE customers
    ALTER COLUMN address_id DROP NOT NULL;

ALTER TABLE customers
	ADD CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES addresses (id);
