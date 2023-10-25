CREATE TABLE COPIE(
	id int PRIMARY KEY,
	etage varchar(20),
	id_livre int not null,
	FOREIGN KEY(id_livre) REFERENCES Livre(id)
);