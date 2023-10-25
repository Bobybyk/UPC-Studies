SELECT nuJoueur FROM Gain
GROUP BY nuJoueur
HAVING Count(Dinstinct Sponsor) > 1;