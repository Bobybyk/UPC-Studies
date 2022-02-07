# TP2

## Rappel notation :

**Algèbre relationnelle :**
 
 * π : Projection
 * σ : Sélection
 * × : produit cartésien
 
## Exemple notation :

**Jointure :**
 
 * Le produit cartésien (FROM Table1, Table2 ou Table1 × Table2) suivi d’une condition de sélection (WHERE \<condition> ou σ \<condition>) est appelé jointure (join)
 * La \<condition> est appelée condition de jointure
 * L’opérateur correspondant en algèbre relationnelle est l’opérateur de théta jointure ⋈ (la tradition veut que le petit nom de la condition soit θ...)

---
---

## 1 : Requêtes simples


**1).** Liste le contenu de la table :

* **Algèbre relationnelle :** π* (table) 
* **SQL :** SELECT * from \<table>


**2).** Liste l'année des coupes du monde ayant eu lieu en Nouvelle-Zélande :


* **Algèbre relationnelle :** π t.année ((σ t.pays) = 'Nouvelle-Zélande'(t) ∧ t.nom = 'Coupe du monde'(t))
* **SQL :** SELECT annee FROM tournois WHERE pays='Nouvelle-Zélande' AND nom='Coupe du monde';

		
**3).** Liste des noms des pays ayant une équipe s'appelant 'XV de France' :
 
* **Algèbre relationnelle :** π e.pays ((σ e.nom) = 'XV de France' (e))
* **SQL :** SELECT pays FROM equipes WHERE nom='XV de France'; 


**4).** liste le numéro des équipes ayant gagné au moins un match, sans répétition :

* **Algèbre relationnelle :** π m.gagnant (m)	
* **SQL :** SELECT DISTINCT gagnant FROM matchs;	

---

## 2 : Requêtes avec jointure 

**1).** Liste le nom des équipes ayant gagné au moins un match : 

* **Algèbre relationnelle :** π e.nom ((σ m.gagnant) = eid(m × e))
* **SQL :** 
	* SELECT DISTINCT nom FROM matchs,equipes WHERE gagnant = eid;
	* SELECT DISTINCT nom FROM equipes WHERE eid IN (select distinct gagnant from matchs);
			
				
**2).** Liste le nom et l'année des tournois dans lesquels l'équipe 2 a perdu un match :

* **Algèbre relationnelle :** π t.nom, t.année ((σ t.tid) = m.tournois ∧ m.perdant = 2 (t × m))
		**<=>** π t.nom, t.année ((σ t.tid) = m.tournois (t × (σ m.perdant) = 2 (m)))
* **SQL :** SELECT DISTINCT nom,annee FROM tournois,matchs WHERE perdant = 2 AND tid = tournois;
		
		
**3).** Liste le numéro des matchs perdus par les Wallabies :

* **Algèbre relationnelle :** π m.id ((σ m.nom) = 'Wallabies' (m × (σ m.perdant) = 'Wallabies' (m) )) [x]
* **SQL :** SELECT mid FROM matchs,equipes WHERE nom='Wallabies' AND perdant = eid; ~


**4).** Liste le numéro des matchs auxquels ont participé les All Blacks (matchs perdus ou gagnés)

* **Algèbre relationnelle :** π m.id ((σ e.nom) = 'All Blacks') [x]
* **SQL :** SELECT mid FROM matchs,equipes WHERE nom='All Blacks' AND (gagnant = eid or perdant = eid); ~


**5).** Liste le numéro des équipes ayant participé à la coupe du monde 1991 :

* **Algèbre relationnelle :** π p.eid ((σ p.tid) = t.id (p × σ t.année = 1991 ∧ t.nom = 'Coupe du Monde' (t)))
* **SQL :** Liste le numéro des équipes ayant participé à la coupe du monde 1991
		
---

## 3 : Requête avec double jointure

**1).** Liste le nom des équipes ayant participé à la coupe du monde 1991 :

* **Algèbre relationnelle :** π e.nom ( (σ t.tid) = p.tid ( (σ t.année) = 1991 ∧ t.nom = 'CdM' (t) × (σ e.eid) = p.eid(e×p) ))
* **SQL :**


**2).** Liste le nom des équipes ayant participé à la coupe du monde 1991 :
 
* **Algèbre relationnelle :** π t.nom, t.année ((σ t.tid) = m.tournois(t × (σ m.perdant) = c.eid ( (σ e.nom) = 'XV de France' (e) × m) ))
* **SQL :** SELECT t.nom,annee FROM tournois t,matchs m,equipes e WHERE m.perdant=e.eid AND e.nom='XV de France' AND t.tid = m.tournois;


**3).** Liste le nom et l’année des tournois dont un match au moins a été perdu par le XV de France :

* **Algèbre relationnelle :** π c.nom ( (σ e.eid) = m.gagnant(e × (σ m1.tournois) = m2.tournois ∧ m1.gagnant = m2.perdant (m1 × m2) ))
* **SQL :** SELECT e.nom FROM  equipes e,tournois t,matchs m WHERE m.tour ='finale' AND m.gagnant=e.eid AND t.tid = m.tournois;

---

**5).**

* **Algèbre relationnelle :**
* **SQL :**

---

## 4 : Condition d'existence et inexistence

**1).** Les équipes dont le pays n’a jamais hébergé une coupe du monde 

* **Algèbre relationnelle :** π p.nom ( (σ ))
* **SQL :** SELECT  FROM pays WHERE


**2).** Le nom des équipes n’ayant jamais participé à une finale

* **Algèbre relationnelle :** 
	
	π e.nom (e) - π e.nom (σ m.tour=finale ∧ (e.id=gagnant ∨ e.id=perdant) (e × m))

* **SQL :** 

**3).** Les tournois pendant lequel le ‘XV de France’ a perdu tous ses matchs 

* **Algèbre relationnelle :** 
	
	π t.nom, t.annee(t) - π t.nom, t.annee ( (σ t.tid=m.tournois) (t × (σ m.gagnant=e.id) (m × (σ e.noms='XV de France') (e) )))
	
* **SQL :**