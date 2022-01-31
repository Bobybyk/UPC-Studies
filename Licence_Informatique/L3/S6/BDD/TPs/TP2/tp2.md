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
* **SQL :** SELECT année FROM tournois 

		
**3).** Liste des noms des pays ayant une équipe s'appelant 'XV de France' :
 
* **Algèbre relationnelle :** π e.pays ((σ e.nom) = 'XV de France' (e))


**4).** liste le numéro des équipes ayant gagné au moins un match, sans répétition :

* **Algèbre relationnelle :** π m.gagnant (m)		

---

## 2 : Requêtes avec jointure 

**1).** Liste le nom des équipes ayant gagné au moins un match : 

* **Algèbre relationnelle :** π e.nom ((σ m.gagnant) = eid(m × e))
			
				
**2).** Liste le nom et l'année des tournois dans lesquels l'équipe 2 a perdu un match :

* **Algèbre relationnelle :** π t.nom, t.année ((σ t.tid) = m.tournois ∧ m.perdant = 2 (t × m))
		**<=>** π t.nom, t.année ((σ t.tid) = m.tournois (t × σ m.perdant = 2 (m)))
		
		
**5).** Liste le numéro des équipes ayant participé à la coupe du monde 1991 :

* **Algèbre relationnelle :** π p.eid ((σ p.tid) = t.id (p × σ t.année = 1991 ∧ t.nom = 'Coupe du Monde' (t)))
		
---

## 3 : Requête avec double jointure

**1).** Liste le nom des équipes ayant participé à la coupe du monde 1991 :

* **Algèbre relationnelle :** π e.nom ( (σ t.tid) = p.tid ( (σ t.année) = 1991 ∧ t.nom = 'CdM' (t) × (σ e.eid) = p.eid(e×p) ))


**2).** Liste le nom des équipes ayant participé à la coupe du monde 1991 :
 
* **Algèbre relationnelle :** π t.nom, t.année ((σ t.tid) = m.tournois(t × (σ m.perdant) = c.eid ( (σ e.nom) = 'XV de France' (e) × m) ))


**3).** Liste le nom et l’année des tournois dont un match au moins a été perdu par le XV de
France :

* **Algèbre relationnelle :** π c.nom ( (σ e.eid) = m.gagnant(e × (σ m1.tournois) = m2.tournois ∧ m1.gagnant = m2.perdant (m1 × m2) ))