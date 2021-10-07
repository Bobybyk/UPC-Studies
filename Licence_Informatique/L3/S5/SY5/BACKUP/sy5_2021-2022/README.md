Systèmes L3 2021-2022
=================

**Conseil :** cloner ce dépôt et travailler directement dans le répertoire cloné.

```bash
git clone git@gaufre.informatique.univ-paris-diderot.fr:poulalho/sy5_2021-2022.git
...
git pull
```
(ce mode de clonage nécessite une authentification par le protocole `ssh`
et non `http`, cf TP nº1)

# Équipe pédagogique

| | | |
|---:|---|---|
| amphi + TP groupes 3 et 5 | Dominique Poulalhon | dominique.poulalhon@irif.fr |
| TP groupe 1 | Colin Gonzalez | gonzalez@irif.fr |
| TP groupes 2 et 4 | Guillaume Geoffroy |  guillaume.geoffroy@irif.fr |
| TP groupes 5 et MI1 | Anne Micheli | anne.micheli@irif.fr |
| TP groupe MI2 | Patrick Lambein-Monette | lambein@irif.fr |

# Suggestions pour réviser le C

* _Modern C_, de Jens Gustedt, 2020 (Manning Publications), [disponible en
  ligne](https://modernc.gforge.inria.fr/)
* [un tutoriel qui a une bonne
  tête](https://zestedesavoir.com/tutoriels/755/le-langage-c-1/); voir la
  section II en particulier.

# Suggestions de ressources externes 

Attention, ces ressources traitent beaucoup plus que le contenu du cours,
il est facile de s'y perdre...

* _UNIX, Programmation et communication_, J.-M. Rifflet et J.-B. Yunès, Dunod
* _Modern Operating Systems_, A. Tanenbaum, Pearson; les 2e et 3e éditions ont été traduites en français (_Systèmes d'exploitation_)
* [le manuel de POSIX](https://pubs.opengroup.org/onlinepubs/9699919799/)


# Cours

* [cours nº1](Cours/cours_1.pdf) : introduction, rôle d'un système
  d'exploitation, notions de processus, de fichier, d'appel système

* cours nº2 : `open`, `close`, `read`, `write`; les quelques
  [slides](Cours/cours_2.pdf) + certains des
  [programmes](Cours/code_cours2) présentés en cours

* cours nº3 : retour sur `read` et `write`; `lseek`;
  stratégies pour stocker les fichiers, en particulier FAT et table
  d'inoeuds; `stat`, `chmod`, ...; [slides](Cours/cours_3.pdf) +
  [programmes](Cours/code_cours3) présentés en cours

# TP


**Remarque :** à l'exception de la première, ces feuilles de TD en *markdown* sont faites pour être lues directement sur l'interface web du gitlab de l'UFR. On profite ainsi de la coloration syntaxique, des liens hypertextes, etc. Mais la syntaxe markdown étant assez légère, ces feuilles de TD peuvent être ouvertes dans votre éditeur de texte favori.

* [énoncé de TP nº1](TP/TP1/tp1.pdf) : `ssh`, rappels de bash et C 
* [énoncé de TP nº2](TP/TP2/tp2.md) : entrées/sorties de bas niveau
* [énoncé de TP nº3](TP/TP3/tp3.md) : manipulation de fichiers `tar`

# Projet

