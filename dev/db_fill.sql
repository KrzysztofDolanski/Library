CREATE TABLE IF NOT EXISTS books (id UUID NOT NULL PRIMARY KEY, title varchar(250) NOT NULL, author varchar(200));
CREATE TABLE IF NOT EXISTS readers(id UUID NOT NULL PRIMARY KEY, name varchar(200) NOT NULL, surname varchar(200), email varchar(200));