
1 - SELECT * FROM logement WHERE numlogement NOT IN (SELECT numlogement FROM hotel UNION SELECT numlogement FROM appartement );

2 - SELECT nom, CASE
                    WHEN numlogement IN (SELECT numlogement FROM hotel) THEN true
                    WHEN numlogement IN (SELECT numlogement FROM appartement) THEN false
                    ELSE NULL
                END as est_hotel
        FROM logement;

3 - SELECT *, CAST(np/3 + ns/3 + nse/3 AS DEC(4,2)) as moyenne FROM 
        (SELECT numlogement, 
        AVG(CAST(COALESCE(noteproprete, 0) AS DEC(4,2))) AS np, 
        AVG(CAST(COALESCE(notesituation, 0)AS DEC(4,2))) AS ns, 
        AVG(CAST(COALESCE(noteservice, 0)AS DEC(4,2))) AS nse 
        FROM logement NATURAL LEFT JOIN avis
        GROUP BY numlogement 
        ORDER BY numlogement) AS q;

4- SELECT nom, COUNT(*) FROM logement NATURAL JOIN reserve NATURAL JOIN hotel
WHERE datedebut <= '2020-02-25' AND datefin >= '2020-02-25'
GROUP BY numlogement, nom, chambres HAVING COUNT(*) > chambres;
