SELECT DISTINCT nom, lieuTournoi, annee,
FROM JoueurTennis, Rencontre R1
WHERE nuJoueur = nuGagnant
AND nuJoueur NOT IN (
	SELECT nuPerdant 
	FROM Rencontre R2
	WHERE R1.lieuTournoi = R2.lieuTournoi
	AND R1.annee = R2.annee);

# [BIS]
# AND NOT EXISTS (
# SELECT * FROM Rencontre R2
# WHERE R2.nuperdant = nuJoueur
# AND R1.lieu = R2.lieu
# AND R1.annee = R2.annee);