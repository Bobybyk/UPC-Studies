DROP TABLE IF EXISTS Note;
DROP TABLE IF EXISTS Controle;
DROP TABLE IF EXISTS Inscription;
DROP TABLE IF EXISTS Matiere;
DROP TABLE IF EXISTS Etudiant;

CREATE TABLE Etudiant (
Num_Etu INTEGER PRIMARY KEY,
Nom     VARCHAR NOT NULL,
Prenom  VARCHAR NOT NULL
);

CREATE TABLE Matiere (
Id_Mat INTEGER PRIMARY KEY,
Nom    VARCHAR NOT NULL,
Coeff   INTEGER NOT NULL DEFAULT 3
);

CREATE TABLE Inscription (
Num_Etu INTEGER REFERENCES Etudiant,
ID_Mat  INTEGER REFERENCES Matiere,
PRIMARY KEY (Num_Etu, ID_Mat)
);

CREATE TABLE Controle (
Id_Controle   INTEGER NOT NULL PRIMARY KEY,
Id_Mat        INTEGER NOT NULL REFERENCES Matiere,
Coeff         INTEGER NOT NULL,
Date_Controle DATE
);

CREATE TABLE Note (
Num_Etu     INTEGER REFERENCES Etudiant,
Id_Controle INTEGER REFERENCES Controle,
Note        INTEGER CHECK (Note BETWEEN 0 and 20),
Obs         VARCHAR CHECK (Obs in ('ABI', 'ABJ')),
PRIMARY KEY (Num_Etu, Id_Controle)
);

INSERT INTO Etudiant (Num_Etu, Nom, Prenom) VALUES
(1, 'Abbott', 'William'),
(2, 'Costello', 'Lou'),
(3, 'Laurel', 'Stanley'),
(4, 'Hardy', 'Oliver'),
(5, 'Lloyd','Harold'),
(6, 'Marx', 'Groucho')
;

INSERT INTO Matiere(Id_Mat, Nom, Coeff) VALUES
(1, 'Mathematiques',3),
(2, 'Algorithmique',6),
(3, 'Programmation',3)
;

INSERT INTO Inscription(Num_Etu, ID_Mat) VALUES
(1,1),
(5,2),
(2,1),
(2,2),
(6,3),
(4,3)
;

INSERT INTO Controle(Id_Controle, Id_Mat, Date_Controle, Coeff) VALUES
(11,2, '1927/01/01',20),
(12,2, '1934/06/01',80),
(13,3, '1999/10/20',60)
;


INSERT INTO Note(Num_Etu, Id_Controle, Note) VALUES
(3,11, 18),
(4,11, 0),
(1,11,17),
(2,11,2),
(3,12, 20),
(4,12, 4),
(1,12,20),
(2,12,8),
(4,13,4),
(6,13,16)
;

