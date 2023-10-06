# BDD TP2

## Exercice 1


Donne la moyenne des entrées dans la colonne `note` :
```sql
SELECT AVG(note) FROM note
```

Donne la moyenne des entrées dans la colonne `note` supérieures à 10 :
```sql
SELECT AVG(note) 
    FROM note 
    WHERE note > 10
```

Donne la moyenne des entrées dans la colonne `note` supérieures à 10, groupées par `id_controle` (c'est à dire la moyenne des notes supérieures à 10 pour chaque contrôle) :
```sql
SELECT id_controle, AVG(note) 
    FROM note 
    WHERE note > 10 
    GROUP BY id_controle
```

Donne la moyenne des entrées dans la colonne `note` supérieures à 10, groupées par `id_controle` et ayant une moyenne supérieure à 18 (c'est à dire la moyenne des notes supérieures à 10 pour chaque contrôle ayant une moyenne supérieure à 18) :
```sql
SELECT id_controle, AVG(note) 
    FROM note 
    WHERE note > 10 
    GROUP BY id_controle 
    HAVING AVG(note) > 18
```

## Exercice 2

1. Donne le nombre d'étudiants :
```sql
SELECT COUNT(num_etu) FROM etudiant
```

2. Donne le nombre de matières qui ont au moins un contrôle :
```sql
SELECT COUNT(id_mat) 
    FROM matiere 
    WHERE id_mat IN (SELECT id_mat FROM controle)
```

3. Donne le nombre de contrôles par matière (y compris les matières pour lesquelles il n'y a pas de contrôle) en affichant le nom de la matière :
```sql
SELECT matiere.nom, COUNT(controle.id_mat) 
    FROM matiere 
    LEFT JOIN controle 
    ON matiere.id_mat = controle.id_mat
    GROUP BY matiere.nom
```

4. Donne la moyenne par contrôle. Pour chaque contrôle on affiche `id_controle`, `date_controle`, le `nom` de la matière et la moyenne.
```sql
SELECT controle.id_controle, controle.date_controle, matiere.nom, AVG(note.note) 
    FROM controle 
    JOIN matiere 
    ON controle.id_mat = matiere.id_mat 
    JOIN note 
    ON controle.id_controle = note.id_controle 
    GROUP BY controle.id_controle, matiere.nom,
```

5. Donne la moyenne par matière. Pour chaque matière on affiche `id_mat`, le `nom` de la matière et la moyenne.
```sql
SELECT matiere.id_mat, matiere.nom, AVG(note.note) 
    FROM matiere 
    JOIN controle 
    ON matiere.id_mat = controle.id_mat 
    JOIN note 
    ON controle.id_controle = note.id_controle 
    GROUP BY matiere.id_mat, matiere.nom
```

6. Donne pour chaque matière, le nombre d'étudiants qui ont passé au moins un contrôle dans cette matière. Sans oublier les matières pour lesquelles il n'y a pas de contrôle. On affiche `id_mat`, le `nom` de la matière et le nombre d'étudiants. Le résultat est trié par le `nom` de la matière.
```sql
SELECT matiere.id_mat, matiere.nom, COUNT(DISTINCT note.num_etu) 
    FROM matiere 
    LEFT JOIN controle 
    ON matiere.id_mat = controle.id_mat 
    LEFT JOIN note 
    ON controle.id_controle = note.id_controle 
    GROUP BY matiere.id_mat, matiere.nom 
    ORDER BY matiere.nom
```

7. Donne la moyenne de chaque étudiant, par matière présentée, par ordre de `id_mat` croissant. Afficher le `nom` et le `prénom` de l'étudiant, le `nom` de la matière et la moyenne.
```sql
SELECT etudiant.nom, etudiant.prenom, matiere.nom, AVG(note.note) 
    FROM etudiant 
    JOIN note 
    ON etudiant.num_etu = note.num_etu 
    JOIN controle 
    ON note.id_controle = controle.id_controle 
    JOIN matiere 
    ON controle.id_mat = matiere.id_mat 
    GROUP BY etudiant.nom, etudiant.prenom, matiere.nom, matiere.id_mat
    ORDER BY matiere.id_mat
```

8. Donne la moyenne générale de chaque étudiant, ne prenant pas en compte les contrôles dont la note est inférieure à 5. Les étudiants pour lesquels on n'a pas de notes, ou dont les notes sont toutes inférieures à 5, apparaissent avec NULL comme moyenne. Le résultat est présenté par ordre croissant de moyenne.
```sql
SELECT etudiant.nom, etudiant.prenom, AVG(note.note) 
    FROM etudiant 
    LEFT JOIN note 
    ON etudiant.num_etu = note.num_etu 
    WHERE note.note >= 5
    GROUP BY etudiant.nom, etudiant.prenom 
    ORDER BY AVG(note.note)
```

9. Donne la moyenne de chaque étudiant (sans compter les contrôles dont la note est inférieure à 5) lorsque celle-ci est supérieure à 10.
```sql
SELECT etudiant.nom, etudiant.prenom, AVG(note.note) 
    FROM etudiant 
    JOIN note 
    ON etudiant.num_etu = note.num_etu 
    WHERE note.note >= 5 
    GROUP BY etudiant.nom, etudiant.prenom 
    HAVING AVG(note.note) > 10
```

## Exercice 3

Les requêtes suivantes sont à faire avec des opérations ensemblistes UNION, INTERSECT, EXCEPT...

10. Donne la liste des étudiants qui sont inscrits ou qui ont passé un contrôle dans la matière `Algorithmique` (la requête doit encore marcher si la valeur de `Id_mat` dans la table matière change). Rappel : on fait attention à l'attribut `nom` présent à la fois dans les tables etudiant et matiere.
```sql
SELECT etudiant.nom, etudiant.prenom 
    FROM etudiant 
    WHERE etudiant.num_etu IN (SELECT num_etu FROM inscription WHERE id_mat = 2) 
    UNION 
    SELECT etudiant.nom, etudiant.prenom 
    FROM etudiant 
    WHERE etudiant.num_etu IN (SELECT num_etu FROM note WHERE id_controle IN (SELECT id_controle FROM controle WHERE id_mat = 2))
```

11. Donne la liste des étudiants qui ont passé un contrôle d'algorithmique mais qui ne sont pas inscrits dans cette matière.
```sql
SELECT etudiant.nom, etudiant.prenom 
    FROM etudiant 
    WHERE etudiant.num_etu IN (SELECT num_etu FROM note WHERE id_controle IN (SELECT id_controle FROM controle WHERE id_mat = 2)) 
    EXCEPT 
    SELECT etudiant.nom, etudiant.prenom 
    FROM etudiant 
    WHERE etudiant.num_etu IN (SELECT num_etu FROM inscription WHERE id_mat = 2)
```

12. Donne la liste des étudiants qui sont inscrits en algorithmique et qui ont passé au moins un contrôle dans cette matière.
```sql
SELECT etudiant.nom, etudiant.prenom 
    FROM etudiant 
    WHERE etudiant.num_etu IN (SELECT num_etu FROM inscription WHERE id_mat = 2) 
    INTERSECT 
    SELECT etudiant.nom, etudiant.prenom 
    FROM etudiant 
    WHERE etudiant.num_etu IN (SELECT num_etu FROM note WHERE id_controle IN (SELECT id_controle FROM controle WHERE id_mat = 2))
```

## Exercice 4   

Les requêtes suivantes peuvent nécessiter l'emploi de sous-requêtes. Les coefficients interviennent.

13. Donne le nombre d'étudiants ayant une moyenne générale supérieure à 10 en prenant en compte le coefficient de chaque matière. Dans chaque entrée de la table Matiere, la colonne `Coeff` donne le coefficient de la matière et doit entrer en compte dans le calcul de la moyenne générale.