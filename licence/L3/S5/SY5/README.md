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

* cours nº4 : retour sur `stat`; les répertoires et leur consultation
  (`opendir`, `readdir`, `closedir`); les [slides](Cours/cours_4.pdf)

* cours nº5 : le SGF, fin : précisions sur les références, sur les 
  droits, sur les modifications de répertoires; les [slides](Cours/cours_5.pdf) + le [programme](Cours/code_cours5) présenté en cours

* cours nº6 : les processus : états, implémentation, création, recouvrement;
  [slides](Cours/cours_6.pdf) (y compris les deux derniers non vus en cours
  faute de temps) + [programmes](Cours/code_cours6) présentés en cours

* cours nº7 : les processus, suite : zombie, synchronisation avec `wait`, 
  recouvrement, duplication de descripteurs; 
  [slides](Cours/cours_7.pdf) + [programmes](Cours/code_cours7) présentés en cours

# TP

**Remarque :** à l'exception de la première, ces feuilles de TD en *markdown* sont faites pour être lues directement sur l'interface web du gitlab de l'UFR. On profite ainsi de la coloration syntaxique, des liens hypertextes, etc. Mais la syntaxe markdown étant assez légère, ces feuilles de TD peuvent être ouvertes dans votre éditeur de texte favori.

* [énoncé de TP nº1](TP/TP1/tp1.pdf) : `ssh`, rappels de bash et C 
* [énoncé de TP nº2](TP/TP2/tp2.md) : entrées/sorties de bas niveau
* [énoncé de TP nº3](TP/TP3/tp3.md) : manipulation de fichiers `tar`
* [énoncé de TP nº4](TP/TP4/tp4.md) : parcours de répertoire
* [énoncé de TP nº5](TP/TP5/tp5.md) : parcours d'arborescence
* [énoncé de TP nº6](TP/TP6/tp6.md) : duplication de processus
* [énoncé de TP nº7](TP/TP7/tp7.md) : synchronisation père-fils avec `wait`
* [énoncé de TP nº8](TP/TP8/tp8.md) : redirections et micro-shell

# Projet

L'énoncé du projet est sur un [dépôt
dédié](https://gaufre.informatique.univ-paris-diderot.fr/poulalho/sy5-projet-2021-2022), destiné à être "forké".

Voici la [liste des équipes](equipes.md) en date du 16 novembre -- équipes
dûment déclarées par mail comme demandé, ainsi que celles "devinées" en
examinant les dépôts sur gaufre. **Merci de me signaler toute erreur ou
omission éventuelle, et de corriger les problèmes signalés** (en
particulier les dépôts publics doivent absolument être passés en privé).

