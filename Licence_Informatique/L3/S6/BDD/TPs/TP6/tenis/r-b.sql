SELECT nom, nationalite
FROM joueur NATURAL JOIN gain G
WHERE tournoi = 'Roland Garros' AND annee = 1992 AND G.nuJoueur 
IN (SELECT NuJoueur FROM Gain WHERE tournoi = 'Wimbledon' AND annee = 1992);