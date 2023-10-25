SELECT nuPerdant
FROM Rencontre
WHERE lieuTournoi = 'Wimbledon'
# liste de ceux qui ont toujours perdu à Wimbledon
AND NOT nuPerdant 
IN (SELECT nuGagnant FROM Rencontre WHERE lieuTournoi = 'Wimbledon')
# liste de ceux qui ont gagné au moins une fois à R.G
AND nuPerdant 
IN (SELECT nuGagnant FROM Rencontre WHERE lieuTournoi = 'Roland Garros')
# liste de ceux qui n'ont jamais perdu à R.G
AND NOT nuPerdant
IN (SELECT nuPerdant FROM Rencontre WHERE lieuTournoi = 'Roland Garros')