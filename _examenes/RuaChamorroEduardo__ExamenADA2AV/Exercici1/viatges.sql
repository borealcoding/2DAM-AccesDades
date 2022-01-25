create database if not exists viatges;

use viatges;

create table destinacions (
    id int(3) auto_increment not null,
    lloc varchar(100) not null,
    preu int(5) not null,
    preuOferta int(5) not null,
    passaportCOVID varchar(2) not null,
    primary key (id)
);