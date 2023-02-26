-- il y a un fichier .sql pour le créer en mode admin dans un schéma commun (mais il faut un compte admin)

DROP VIEW IF EXISTS Mesreservations;
DROP TABLE IF EXISTS Avis ;
DROP TABLE IF EXISTS Reserve ;
DROP TABLE IF EXISTS Client ;
DROP TABLE IF EXISTS Appartement ;
DROP TABLE IF EXISTS Hotel ;
DROP TABLE IF EXISTS Logement ;



CREATE TABLE Logement (
  numlogement SERIAL PRIMARY KEY,
  nom VARCHAR NOT NULL UNIQUE,
  adresse VARCHAR,
  ville VARCHAR,
  codepostal INTEGER CHECK (codepostal between 10000 AND 99999) 
);

CREATE TABLE Hotel (
  numlogement INTEGER PRIMARY KEY REFERENCES Logement,
  piscine BOOLEAN,
  restaurant BOOLEAN,
  Etoiles INTEGER CHECK (Etoiles BETWEEN 1 and 5),
  Chambres INTEGER CHECK (Chambres >= 0)
);

CREATE TABLE Appartement (
  numlogement INTEGER PRIMARY KEY REFERENCES Logement,
  NbChambres INTEGER CHECK (NbChambres >= 0),
  NbLits INTEGER CHECK (NbLits >= 0),
  Cuisine BOOLEAN
);

CREATE TABLE Client(
  login VARCHAR PRIMARY KEY,
  nom VARCHAR,
  prenom VARCHAR
);


CREATE TABLE Reserve(
  login VARCHAR REFERENCES Client,
  numLogement INTEGER REFERENCES Logement,
  dateDebut DATE NOT NULL,
  dateFin DATE NOT NULL,
  dateReservation DATE NOT NULL DEFAULT CURRENT_DATE,
  PRIMARY KEY (login, numLogement, DateDebut),
  CHECK ((dateFin >= dateDebut) AND (dateDebut >= dateReservation))
);

CREATE TABLE Avis(
  login VARCHAR REFERENCES Client,
  date DATE DEFAULT CURRENT_DATE,
  numLogement INTEGER REFERENCES Logement,
  noteProprete INTEGER CHECK (noteProprete BETWEEN 1 and 5),
  noteService INTEGER CHECK (noteService BETWEEN 1 and 5),
  noteSituation INTEGER CHECK (noteSituation BETWEEN 1 and 5),
  PRIMARY KEY (login, numlogement)
);


CREATE VIEW MesReservations AS
SELECT * 
  FROM Reserve
 WHERE login = CURRENT_USER;

INSERT INTO Logement (nom, adresse, ville, codepostal) VALUES 
('Hotel des trois Lilas', '12 rue des Lilas', 'Paris', 75013),
('Hotel des Encyclopedistes', '12 rue Diderot', 'Paris', 75013),
('Hotel des trois Philosophes', '12 rue D''Alembert', 'Paris', 75013),
('Appartement des Ecoles', '12 rue des Ecoles', 'Paris', 75005),
('Studio Erasme', '12 rue des Ecoles', 'Paris', 75005),
('Appartement du Pantheon', '12 rue des Ecoles', 'Paris', 75005),
('B&B Quartier Latin', '10 rue d''Assas', 'Paris',75005),
('Le Grand Hotel', '12 Place du Pantheon', 'Paris', 75005)
;

INSERT INTO Hotel(numlogement, piscine, restaurant, etoiles, chambres) VALUES
(1, FALSE, TRUE, NULL,10),
(2, TRUE, NULL, 5,5),
(3, NULL, FALSE, 3, NULL),
(8, TRUE, TRUE, 5,1000)
;

INSERT INTO Appartement(numLogement, nbChambres, nbLits, Cuisine)
VALUES
(4, NULL, 3, TRUE),
(5, 1 , 3, FALSE),
(6, 1 , NULL, NULL)
;

INSERT INTO Client (login, nom, prenom)
VALUES
('diderot', 'Diderot', 'Denis'),
('rousseau', 'Rousseau', 'Jean-Jacques'),
('guest', 'guest', 'guest'),
('y', 'Doe', 'John'),
('x', 'Doe', 'Jane'),
('pjh', 'Harvey', 'PJ'),
('vianu', 'Vianu', 'Victor'),
('milo', 'Milo', 'Tova'),
('suciu', 'Suciu', 'Dan')
;

INSERT INTO Reserve (login, numlogement, datedebut, datefin, datereservation)
VALUES
('diderot', 1, '2020-02-25', '2020-03-15', '2020-01-11'),
('rousseau', 2, '2020-02-25', '2020-03-17', '2020-01-15'),
('rousseau', 1, '2020-03-25', '2020-03-27', '2020-02-20'),
('y', 2, '2020-02-25', '2020-03-16', '2020-02-05'),
('x', 2, '2020-02-25', '2020-03-16', '2020-02-05'),
('pjh', 2, '2020-02-26', '2020-03-16', '2020-01-09'),
('rousseau', 1, '2020-01-25', '2020-02-19', '2020-01-03'),
('vianu', 2, '2020-02-25', '2020-03-19', '2020-02-02'),
('milo', 2, '2020-02-25', '2020-03-19', '2020-02-01'),
('suciu', 2, '2020-02-25', '2020-02-29', '2020-02-13')
;

Insert into avis(login, numlogement, noteproprete, noteservice, notesituation)
VALUES
('diderot' , 1, 3,5,3),
('rousseau', 1, 3,NULL,3),
('guest'   , 1, 3,3,3),
('diderot' , 2, NULL,1,3),
('rousseau', 2, NULL,3,3),
('guest'   , 2, NULL,5,3),
('diderot' , 6, 2,3,3),
('rousseau', 6, 2,3,2),
('guest'   , 6, 3,3,4)
;




