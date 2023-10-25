CREATE TABLE EMPRUNT_COURS(
	idint PRIMARY KEY,
	id_client int,
	debut_j date,
	debut_h time,
	FOREIGN KEY(id_client) REFERENCES Client(id),
);