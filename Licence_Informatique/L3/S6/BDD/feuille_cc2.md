# Rappel notation :

**Algèbre relationnelle :**
 
 * π : Projection
 * σ : Sélection
 * × : produit cartésien

# Exemple 1 (TP6) :

* **Joueurtennis**(NuJoueur, Nom, Prenom, AnNaiss, Nationalite)
* **Rencontre**(NuGagnant, NuPerdant, LieuTournoi, Annee)
* **Gain**(NuJoueur, LieuTournoi, Annee, Prime, Sponsor)


(a) Nom et année de la naissance des joueurs ayant participée à Roland Garros en 1994.

* **SQL :** SELECT name, annais FROM Joueur NATURAL JOIN join WHERE lieutournoi = 'Roland Garros' AND annee = 1994   

(b) Nom et nationalit´e des joueurs ayant participée à la fois au tournoi de Roland Garros et à celui de Wimbledon, en 1992.

* **SQL :** SELECT nom, nationalite FROM joueur NATURAL JOIN gain G WHERE tournoi = 'Roland Garros' AND annee = 1992 AND G.nuJoueur IN (SELECT NuJoueur FROM Gain WHERE tournoi = 'Wimbledon' AND annee = 1992);

(c) Nom et nationalitée des joueurs ayant été sponsorisés par Peugeot et ayant gagné à Roland Garros au moins un match (avec un sponsor quelconque).

* **SQL :** SELECT DISTINCT nom, nationalite FROM Joueur NATURAL JOIN Gain G, Renconte R WHERE Sponsor = 'peugeot' AND R.lieutournois = 'Roland Garros' AND R.nugagnant = G.nuJoueur;

(d) Numéros des joueurs qui ont toujours perdu à Wimbledon et toujours gagné à Roland Garros.

* **SQL :** SELECT nuPerdant FROM Rencontre WHERE lieuTournoi = 'Wimbledon'

	*(liste de ceux qui ont toujours perdu à Wimbledon)*

	AND NOT nuPerdant IN (SELECT nuGagnant FROM Rencontre WHERE lieuTournoi = 'Wimbledon')

	*(liste de ceux qui ont gagné au moins une fois à R.G)*

	AND nuPerdant IN (SELECT nuGagnant FROM Rencontre WHERE lieuTournoi = 'Roland Garros')

	*(liste de ceux qui n'ont jamais perdu à R.G)*

	AND NOT nuPerdant IN (SELECT nuPerdant FROM Rencontre WHERE lieuTournoi = 'Roland Garros')

(e) Liste des vainqueurs de tournoi, mentionnant le nom du joueur avec le lieu et l’année du tournoi qu’il a gagné.

* **SQL :** SELECT DISTINCT nom, lieuTournoi, annee, FROM JoueurTennis, Rencontre R1 WHERE nuJoueur = nuGagnant AND nuJoueur NOT IN ( SELECT nuPerdant FROM Rencontre R2 WHERE R1.lieuTournoi = R2.lieuTournoi AND R1.annee = R2.annee);

(g) Nombre de rencontres en total.

* **SQL :** SELECT count(\*) FROM Rencontre;

(h) Liste des tournois et années avec le nombre de joueurs participants.

* **SQL :** SELECT lieutournoi, annee, count(\*) FROM Gain GROUP BY lieutournoi, annee;

(i) Numéros des joueurs ayant eu au moins deux sponsors. Donnez deux solutions (une avec count et une sans).

* **SQL :** SELECT nuJoueur FROM Gain GROUP BY nuJoueur HAVING Count(Dinstinct Sponsor) > 1;

(j) Numéros des joueurs ayant eu exactement deux sponsors. Donnez deux solutions (une avec count et une sans).

* **SQL :** SELECT nuJoueur FROM Gain GROUP BY nuJoueur HAVING Count(Distinct Sponsor) = 2;

# Exemple 2 (TP5) :

1). Les identifiants et positions de toutes le copies disponibles de “Foundations of Data-bases”.

* **SQL :** SELECT id_copie, rayon FROM Livre, Copie WHERE Livre.id = Copie.id_livre AND Livre.Titre = 'Foundations of Databases'

2). Les durées (en jours) des emprunts terminés du livre “Foundations of Databases”.

* **SQL :** SELECT fin_j - debut_j FROM Emprunt_term, Copie C, Livre L WHERE titre = 'Foundations of Databases', AND E.id_copie = C.idFoundations of Databases

3). Les clients abonnés qui ont rendu tous leurs livres (exclure les abonnés qui n’ont jamais fait d’emprunt).

* **SQL :** SELECT id_client FROM Abonne A, Emprunt_term E WHERE A.id_client = E.id_client AND NOT EXISTS ( SELECT * FROM Copie C WHERE C.id_client = A.id_client) AND C.dispo = 0

8). Une requête booléenne qui renvoie 1 si chaque copie de livre à chaque instant est concernée par au plus un emprunt (passé ou courant). Sinon, la requête renvoie 0.

* **SQL :** SELECT NOT EXISTS ( SELECT E1.id_copie FROM EMPRUNT_TERM E1, EMPRUNT_TERM E2 WHERE E1.id_copie = E2.id_copie E2.debut > E1.debut E2.debut < E1.fin => UNION SELECT E.id_copie FROM EMPRUNT_TERM E, Copie C WHERE E.id_copie = C.id_copie AND E.fin > C.debut) as resultat;


# 1 : Requêtes simples

**1).** Liste le contenu de la table :

* **Algèbre relationnelle :** π* (table) 
* **SQL :** SELECT * from (table)


**2).** Liste l'année des coupes du monde ayant eu lieu en Nouvelle-Zélande :

* **Algèbre relationnelle :** π t.année ((σ t.pays) = 'Nouvelle-Zélande'(t) ∧ t.nom = 'Coupe du monde'(t))
* **SQL :** SELECT annee FROM tournois WHERE pays='Nouvelle-Zélande' AND nom='Coupe du monde';

# 2 : Requêtes avec jointure 

**1).** Liste le nom des équipes ayant gagné au moins un match : 

* **Algèbre relationnelle :** π e.nom ((σ m.gagnant) = eid(m × e))

* **SQL :** 

	* SELECT DISTINCT nom FROM matchs,equipes WHERE gagnant = eid;
	* SELECT DISTINCT nom FROM equipes WHERE eid IN (select distinct gagnant from matchs);
			
				
**2).** Liste le nom et l'année des tournois dans lesquels l'équipe 2 a perdu un match :

* **Algèbre relationnelle :** 

	π t.nom, t.année ((σ t.tid) = m.tournois ∧ m.perdant = 2 (t × m))
		
	**<=>** 
	
	π t.nom, t.année ((σ t.tid) = m.tournois (t × (σ m.perdant) = 2 (m)))

**3).** Liste le numéro des matchs perdus par les Wallabies :

* **Algèbre relationnelle :** π m.id ((σ e.eid) = m.perdant (m × (σ e.nom) = 'Wallabies' (e) ))

* **SQL :** SELECT mid FROM matchs,equipes WHERE nom='Wallabies' AND perdant = eid; ~


**4).** Liste le numéro des matchs auxquels ont participé les All Blacks (matchs perdus ou gagnés)

* **Algèbre relationnelle :** π m.mid ((σ m.gagnant) = e.eid (m × σ e.nom = 'AllBlacks' (e) )

* **SQL :** SELECT mid FROM matchs,equipes WHERE nom='All Blacks' AND (gagnant = eid or perdant = eid); ~

# 3 : Requête avec double jointure

**1).** Liste le nom des équipes ayant participé à la coupe du monde 1991 :

* **Algèbre relationnelle :** π e.nom ( (σ t.tid) = p.tid ( (σ t.année) = 1991 ∧ t.nom = 'CdM' (t) × (σ e.eid) = p.eid(e×p) ))


**2).** Liste le nom des équipes ayant participé à la coupe du monde 1991 :
 
* **Algèbre relationnelle :** π t.nom, t.année ((σ t.tid) = m.tournois(t × (σ m.perdant) = c.eid ( (σ e.nom) = 'XV de France' (e) × m) ))

* **SQL :** SELECT t.nom,annee FROM tournois t,matchs m,equipes e WHERE m.perdant=e.eid AND e.nom='XV de France' AND t.tid = m.tournois;

**3).** Liste le nom et l’année des tournois dont un match au moins a été perdu par le XV de France :

* **Algèbre relationnelle :** π c.nom ( (σ e.eid) = m.gagnant(e × (σ m1.tournois) = m2.tournois ∧ m1.gagnant = m2.perdant (m1 × m2) ))

* **SQL :** SELECT e.nom FROM  equipes e,tournois t,matchs m WHERE m.tour ='finale' AND m.gagnant=e.eid AND t.tid = m.tournois;

# 4 : Condition d'existence et inexistence

**1).** Les équipes dont le pays n’a jamais hébergé une coupe du monde :

* **Algèbre relationnelle :** π p.nom ( (σ ))
* **SQL :** SELECT  FROM pays WHERE


**2).** Le nom des équipes n’ayant jamais participé à une finale :

* **Algèbre relationnelle :** π e.nom (e) - π e.nom (σ m.tour=finale ∧ (e.id=gagnant ∨ e.id=perdant) (e × m))

**3).** Les tournois pendant lequel le ‘XV de France’ a perdu tous ses matchs :

* **Algèbre relationnelle :** π t.nom, t.annee(t) - π t.nom, t.annee ( (σ t.tid=m.tournois) (t × (σ m.gagnant=e.id) (m × (σ e.noms='XV de France') (e) )))

**4).** Le nom des équipes ayant toujours gagné leurs matchs :

* **Algèbre relationnelle :** π e.nom (e) - π e.nom (σ m.perdant = e.eid (m × e))