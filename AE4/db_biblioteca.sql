# creació de la bbdd i de l'única tabla amb la que treballarem
CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

CREATE TABLE llibres (
  idLlibre INT AUTO_INCREMENT NOT NULL,
  titol VARCHAR(50) NOT NULL,
  autor VARCHAR(50) NOT NULL,
  anyNaixement CHAR(4),
  anyPublicacio CHAR(4) NOT NULL,
  editorial VARCHAR(50) NOT NULL,
  numPagines CHAR(30) NOT NULL,
  PRIMARY KEY (idLlibre)
);

# visualitzar la tabla llibres
USE biblioteca;
SHOW TABLES;
SELECT * FROM llibres;

# sentències per a resetejar. podem elegir entre esborrar la tabla, o directament la bbdd
DROP TABLE llibres;
DROP DATABASE IF EXISTS biblioteca;

# --- CONSULTES DE L'ACTIVITAT

#Llibres (títol, autor i any de publicació) dels autors nascuts abans de 1950.
SELECT titol,autor,anyNaixement,anyPublicacio FROM llibres WHERE anyNaixement < 1950;
#Editorials que hagen publicat almenys un llibre en el segle XXI.
SELECT COUNT(editorial) AS nLlibresPublicats, editorial FROM llibres WHERE anyPublicacio > 2000;