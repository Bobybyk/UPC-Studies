TP nº1 : rappels de L3 : vie et mort d'un processus
==================

**M1 Informatique - Programmation Système Avancée**

#### Exercice 1 : cacophonie

1. Écrire un programme qui crée deux processus : un père qui affiche
   1000 fois `"ouaf!"` (sur la sortie standard), et un fils qui affiche
   1000 fois `"miaou!"`. Interpréter l'affichage. 

2. Modifier ce programme pour le munir d'une variable `char bruit[8]`.
   Le père stocke `"ouaf!"` dans `bruit`, tandis que le fils stocke
   `"miaou!"`. Tous deux affichent ensuite 1000 fois l'adresse et la
   valeur de `bruit` (sur la sortie standard). Interpréter l'affichage.


#### Exercice 2 : généalogie imposée

1. Écrire un programme qui crée la généalogie de processus suivante :

```
  p1
  ├──p2
  ├──p3
  │  ├──p4
  │  │  └──p5
  │  └──p6
  └──p7
```

   Chaque processus doit afficher son pid et celui de son père, puis
   faire un appel à `pause()`. Vérifier (depuis un autre terminal) que
   la généalogie est bien celle attendue à l'aide de `ps` ou `pstree`
   avant de terminer les processus à l'aide de `ctrl-C`.

2. Modifier le programme pour que le processus `p1` initialise une
   variable `profondeur` à 0. Chaque processus doit ensuite utiliser
   `profondeur` pour préciser sa position dans l'arbre en affichant,
   en plus des pids, sa profondeur et celle de son père.

3. Modifier le programme pour que le processus `p3` (et lui seul)
   n'invoque pas `pause()`. Dans quel état `p3` se trouve-t-il alors?
   Qu'est devenue sa descendance? Que se passe-t-il ensuite si `p1` est
   tué (et seulement lui)? 

   Terminer tous les processus restants.


#### Exercice 3 : un peu de hasard

1. Écrire un programme qui crée dix processus fils, qui affichent chacun
   leur `pid` avant de terminer. Vérifier que votre programme n'affiche
   que dix lignes.

2. Modifier le programme pour que chaque fils attende un temps aléatoire
   (à l'aide des fonctions `random()` et `srandom()`) entre 1 et 10
   secondes, avant d'afficher son message et de terminer. Vérifier que
   les fils ne s'arrêtent pas tous en même temps...


#### Exercice 4 : (tous) à table!

1. Écrire un programme qui crée deux processus, un père et son fils.
   Toutes les secondes, le père ~~appelle son fils~~ affiche le message
   `À table!`, tandis que le fils fait obstinément la sourde oreille. Au
   bout d'un certain temps (aléatoire), le fils cède, affiche `Oui,
   voilà, c'est bon quoi!` (et termine). Le père lâche alors un `C'est
   pas trop tôt, tu n'es pas à l'auberge ici!`, avant de terminer à son
   tour.

_indication : il faudra utiliser `wait`._

2. Reprendre l'exercice avec cette fois une plus grande famille : le père
   appelle tous ses enfants à table, et répète son appel aussi longtemps
   que nécessaire, c'est-à-dire tant que tous ses fils n'ont pas terminé.
   Il accueille chaque enfant d'un `Ah, voici PID! Les autres, à table!`
   (avec le PID adapté). Lorsqu'il n'en manque plus qu'un, il change son
   message en `Ah, voici PID1. Bon, PID2, à table maintenant, on n'attend 
   plus que toi!` (avec les PID adaptés).

Exemple d'exécution :
```
poulalho@lulu:~$ a-table 4
5132, 5133, 5134, 5135, à table!
À table!
À table!
À table!
(5133) Voilà, voilà, j'arrive...
Ah, voici 5133! Les autres, à table!
À table!
À table!
(5135) Voilà, voilà, j'arrive...
Ah, voici 5135! Les autres, à table!
À table!
(5132) Voilà, voilà, j'arrive...
Ah, voici 5132! Bon, 5134, à table maintenant, on n'attend plus que toi!
À table!
À table!
(5134) Voilà, voilà, j'arrive...
Ah, enfin! C'est pas trop tôt, tu n'es pas à l'auberge ici!
```

_(cela nécessite donc que le père stocke les identifiants de ses fils pour
cocher les terminaisons au fur et à mesure, et cela se rapproche un peu
de ce que doit réellement faire un shell pour gérer les jobs à l'arrière-plan)_


