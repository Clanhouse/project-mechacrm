CREATE TABLE IF NOT EXISTS customers(
  id int8 primary key not null unique generated always as identity,
  name varchar(100) not null,
  surname varchar(100) not null,
  phone varchar(30) not null unique,
  address varchar(255) not null
);
