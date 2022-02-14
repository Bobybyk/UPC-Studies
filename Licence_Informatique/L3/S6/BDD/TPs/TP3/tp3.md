# TP3

## Exercice 2

**1).** clé privé/clé publique

---

**Création joueurs/équipes :**

CREATE TABLE joueurs(jid serial primary key, nom text NOT NULL, prenom text, dateDeNaissance date, nationalite text);


CREATE TABLE joueurEquipes(jid integer, eid integer, PRIMARY KEY(jid, eid), FOREIGN KEY (jid) REFERENCES joueurs(jid), FOREIGN KEY (eid) REFERENCES equipes(eid));

---

**Création matchs** 

ALTER TABLE matchs

DROP CONSTRAINT match\_gagnant_fkey

DROP CONSTRAINT match\_perdant_fkey

ADD FOREIGN KEY gagnant REFERENCES equipes(eid) ON DELETE CASCADE

ADD FOREIGN KEY perdant REFERENCES equipes(eid) ON DELETE CASCADE

