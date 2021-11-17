CREATE DATABASE IF NOT EXISTS destinacions;
USE destinacions;

CREATE TABLE preus (
    id INT(3) AUTO_INCREMENT NOT NULL,
    nom VARCHAR(100),
    preu VARCHAR(10),
    PRIMARY KEY (id)
);

INSERT INTO preus (nom, preu)
    VALUES ('Londres', 150),
           ('Paris',135),
           ('Roma',105),
           ('Nova York',725),
           ('Tokio',1200);

SELECT * FROM preus;