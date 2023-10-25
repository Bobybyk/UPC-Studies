CREATE TABLE EMPRUNT_TERM(
	idint PRIMARY KEY,
	id_client int,
	commentaire text,
	debut_j date,
	debut_h time,
	fin_j date,
	fin_h time,
	FOREIGN KEY(id_client) REFERENCES Client(id),
);