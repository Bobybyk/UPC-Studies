SELECT DISTINCT nom, nationalite
FROM Joueur NATURAL JOIN Gain G, Renconte R
WHERE Sponsor = 'peugeot'
AND R.lieutournois = 'Roland Garros' AND R.nugagnant = G.nuJoueur;