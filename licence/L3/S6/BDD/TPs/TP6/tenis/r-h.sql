SELECT lieutournoi, annee, count(*)
FROM Gain
GROUP BY lieutournoi, annee;