SELECT NOT EXISTS ( 
	SELECT E1.id_copie
	FROM EMPRUNT_TERM E1, EMPRUNT_TERM E2
	WHERE E1.id_copie = E2.id_copie
	E2.debut > E1.debut
	E2.debut < E1.fin
	=> UNION
	SELECT E.id_copie
	FROM EMPRUNT_TERM E, Copie C
	WHERE E.id_copie = C.id_copie
	AND E.fin > C.debut) as resultat;