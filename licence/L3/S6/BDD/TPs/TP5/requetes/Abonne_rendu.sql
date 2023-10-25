SELECT id_client FROM Abonne A, Emprunt_term E WHERE 
	A.id_client = E.id_client 
	AND NOT EXISTS ( SELECT * FROM Copie C WHERE C.id_client = A.id_client) 
	AND C.dispo = 0