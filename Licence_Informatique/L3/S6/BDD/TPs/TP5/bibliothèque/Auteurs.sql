CREATE TABLE AUTEURS(
	id_livre int,
	id_auteur int,
	PRIMARY KEY (id_livre, id_auteur),
	FOREIGN KEY(id_livre) REFERENCES Livre(id),
	FOREIGN KEY(id_auteur) REFERENCES Auteur(id)
);