CREATE TABLE EXEMPLAIRE_LIVRE(
	id int PRIMARY KEY,
	rayon varchar(20),
	disponibilite boolean,
	id_livre int not null,
	FOREIGN KEY(id_livre) REFERENCES Livre(id)
);