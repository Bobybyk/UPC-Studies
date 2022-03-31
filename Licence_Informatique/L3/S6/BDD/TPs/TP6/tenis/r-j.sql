SELECT nuJoueur
FROM Gain
GROUP BY nuJoueur
HAVING Count(Distinct Sponsor) = 2;