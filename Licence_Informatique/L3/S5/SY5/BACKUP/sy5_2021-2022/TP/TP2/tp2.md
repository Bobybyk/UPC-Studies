TP nº2 : filtres UNIX
=====================

**L3 Informatique - Système**


Filtres
-------

On rappelle qu'un **filtre** est un programme permettant d'effectuer des transformations sur les
flux de données. Dans le contexte de systèmes d'exploitation de type *UNIX*, les filtres sont
typiquement des programmes qui utilisent princi(* Toutes les fonction sont ici définies par des "let rec". Il est
   possible que dans vos solutions certaines fonctions ne soient pas
   récursives, car elles utilisent une fonction recursive auxiliaire
   définie localement. À vous d'enlever le mot clef "rec" dans ce
   cas. *)palement l'entrée standard (`stdin`) comme source de
données en entrée et la sortie standard (`stdout`) comme destination des données en sortie.

Exemples de filtres UNIX classiques :

  - `cat` : appelé sans argument, copie `stdin` sur `stdout`
  - `dd` : appelé sans argument, copie `stdin` sur `stdout` par blocs de 512 octets
  - `wc` : affiche sur `stdout` le nombre de caractères, mots et lignes lus dans `stdin`
  - `grep PATTERN` : `cat` sélectif, *i.e.* seules les lignes de `stdin` qui correspondent au motif
    `PATTERN` (une expression régulière) sont copiées sur `stdout`

L'objectif de ce TP est d'implémenter (des versions simplifiées de) ces
filtres. Vous êtes invités à systématiquement consulter leurs pages de
manuel pour vérifier leur sémantique, et à les tester dans un shell.

L'implémentation des filtres doit être faite en utilisant comme fonctionnalités d'entrée/sortie les
appels systèmes `open`, `read`, `write`, etc. Il est interdit d'utiliser les fonctions de plus haut
niveau de la bibliothèque standard (`fopen`, `fread`, `fwrite`, etc).


Structure du TP
---------------
 
**TODO :** pour commencer, mettez à jour le dépôt `git` du cours sur votre
machine (en lançant la commande `git pull` depuis le dépôt cloné).

**TODO :** déplacez-vous dans le dossier [TP/TP2/](TP/TP2/) du dépôt git, et lancez la commande `make init`.
Ceci créera un dossier [TP/TP2/work/](javascript:;) : c'est dans ce dossier que vous devrez travailler
(ne touchez pas au dossier templates/ ou la compilation échouera).

Pour chaque exercice, vous trouverez dans le dossier [TP/TP2/work/](javascript:;) un répertoire de
la forme `ex-N-blabla` qui contient déjà une partie du code, et que vous
devrez compléter. Ce répertoire contient également un Makefile qui vous permettra :

 - de compiler votre code en tapant `make`,
 - de tester votre code en tapant `make test`.

À la fin de chaque exercice, avant de passer au suivant, assurez-vous que votre programme passe *tous*
les tests : lorsque vous exécutez `make test`, toutes les lignes doivent dire `OK` !



Exercice 1 : copycat
--------------------

Le répertoire correspondant est [TP/TP2/work/ex-1-my-cat](javascript:;).
Le seul fichier à modifier est `my-cat.c`.

#### Écriture du programme `my-cat`

**TODO :** complétez `my-cat.c` pour obtenir un programme qui lit en
boucle depuis l'entrée standard et recopie ce qu'il a lu sur la sortie
standard. Une fois la lecture terminée (fin de fichier détectée par
`read`), le programme doit terminer. En cas d'erreur, le programme doit
terminer avec le code d'erreur 1.

Faites attention à :

 - faire les lectures et écritures par blocs de taille `BUFFER_SIZE` (ni caractère par caractère, ni
   tout le fichier d'un coup)
 - après chaque appel système (`read()`, `write()`, etc.), vérifier s'il y a eu une erreur et agir en
   conséquence.

#### Impact de la taille des blocs de lecture

Votre programme lit sur l'entrée standard par blocs de `BUFFER_SIZE` octets :

**TODO :** essayez de réduire la taille des blocs (100 octets, puis 10, puis 1) en testant à chaque
fois le résultat avec `make test` : que constatez-vous ?


	
Exercice 2 : `my-dd`
-------------------

Le répertoire correspondant est [TP/TP2/work/ex-2-my-dd](javascript:;). 
Le seul fichier à modifier est`my-dd.c`.

L'objectif de cet exercice est d'écrire un programme `my-dd` qui reproduise une partie des
fonctionnalités de l'utilitaire `dd` (cf. `man dd`). Plus précisément `./my-dd -i <INFILE>
-o <OUTFILE> -b <BLOCKSIZE> -c <COUNT>` doit avoir un comportement équivalent à `dd
if=<INFILE> of=<OUTFILE> bs=<BLOCKSIZE> count=<COUNT> status=none`. Les arguments `-i`,
`-o`, `-b` et `-c` sont optionnels, avec le même comportement par défaut que pour `dd`. En
cas de succès, le programme doit terminer avec le code 0, et en cas d'erreur, le code 1.


**TODO :** complétez les portions de `my-dd.c` marquées avec `TODO [A] :`. Vous obtiendrez
ainsi une première version de `my-dd` qui ignore les arguments `-i` et `-o`.

Vérifiez bien que si vous exécutez `make test`, le premier test est réussi.

**TODO :** complétez le reste du code de de `my-dd.c` (c'est-à-dire les portions marquées
avec `TODO [B] :`).

Vérifiez que cette fois-ci, lorsque vous exécutez `make test`, tous les tests sont réussis.



Exercice 3 : `my-wc`
--------------------

Le répertoire correspondant est [TP/TP2/work/ex-3-my-wc](javascript:;). Le seul fichier à modifier est
`my-wc.c`.

L'objectif est d'écrire un programme `my-wc` qui satisfasse la spécification suivante (adaptée de `man wc`) :

```
SYNOPSIS
    ./my-wc [-cl] [fichier]
  
DESCRIPTION
    ./my-wc compte le nombre de retours à la ligne ('\n') et d'octets dans le fichier indiqué.

    Si aucun fichier n'est fourni, ou si le nom de fichier est "-", la lecture se fait depuis
    l'entrée standard.

    Chaque statistique occupe une colonne. Les colonnes sont séparées par une tabulation ('\t')
    et sont affichées dans l'ordre suivant : nombre de retours à la ligne, puis nombre d'octets.
    
    Si un nom de fichier est fourni, ce nom est affiché après les statistiques, dans une nouvelle
    colonne.

    Par défaut wc affiche les deux valeurs. Les options permettent de n'afficher que l'une
    d'entre elles. Par exemple, ./my-wc -c affiche le nombre d'octets.

    En cas de succès, ./my-wc doit terminer avec le code 0, et en cas d'échec, avec le code 1.
    
OPTIONS
    -c
        Afficher uniquement le nombre d'octets.
    -l
        Afficher uniquement le nombre de retours à la ligne.
```

Pour convertir un entier en chaîne de caractères, vous pourrez inclure `<stdio.h>` et utiliser la
fonction `snprintf()`. En revanche, les entrées et sorties doivent toujours se faire avec `read()`
et `write()`.

#### Comptage des lignes et des octets

**TODO :** complétez le code de `my-wc.c`, et testez votre programme avec `make test`.


#### Comptage des mots *(facultatif)*

**TODO :** ajoutez une option `-w` qui compte le nombre de *mots*; un mot est défini comme
un groupe contigu non vide de caractères autres que ' ' (espace), '\t' et '\n'.

Testez votre programme avec `make test-plus`. Notez que si vous exécutez `make test` à la place,
le test sans argument (`./my-wc my-wc.c`) devrait désormais échouer.

