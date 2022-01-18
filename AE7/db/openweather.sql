CREATE DATABASE IF NOT EXISTS openweather;

USE openweather;

CREATE TABLE pronosticsDiaris (
      idPronostic int not null auto_increment,
      dataPronostic datetime not null,
      descripcio varchar(50) not null,
      poblacio varchar(50) not null,
      pais varchar(50) not null,
      temp char(10) not null,
      tempMax char(10) not null,
      tempMin char(10) not null,
      humitat char(10) not null,
      sensTerm char(10) not null,
      primary key (idPronostic)
);

SELECT * FROM pronosticsDiaris;

