CREATE TABLE app_user
(
    id int NOT NULL AUTO_INCREMENT,
    email VARCHAR(200) NOT NULL,
    password VARCHAR(200) NOT NULL,
    firstname VARCHAR(200) NOT NULL,
    lastname VARCHAR(200) NOT NULL,
    street VARCHAR(200),
    housenumber int,
    zipcode VARCHAR(50),
    country VARCHAR(50),
    city VARCHAR(50),
    phonenumber VARCHAR(50),
    role VARCHAR(20),

    PRIMARY KEY (id),
    UNIQUE (email)
);