TP nº3 : fichiers : entrées/sorties, duplication de descripteurs, verrous
==================

**M1 Informatique - Programmation Système Avancée**


#### Exercice 1 : `diff`

Écrire un programme qui prend en arguments deux chaines de caractères
et teste s'il s'agit des références de deux fichiers ordinaires ayant 
le même contenu.

Pour cela, écrire deux fonctions distinctes :

* l'une, prenant en paramètre une chaîne de caractères, testera s'il
  s'agit de la référence valide d'un fichier ordinaire,

_(indication : utiliser `stat()` ou une fonction de la même famille)_

* l'autre, prenant en paramètre deux références valides de fichiers
  ordinaires, testera si leur contenu est identique; pour cela, elle
  lira en boucle leur contenu à l'aide de buffers de taille `BUFSIZE`.

_(on demande d'utiliser les accès de bas-niveau : ouvrir les fichiers 
avec `open()`, lire avec `read()`)_

Effectuez ensuite des tests pour différentes valeurs de `BUFSIZE` (1,
1<<10, 1<<20) sur de très gros fichiers -- par exemple un gros fichier `csv` 
tel qu'on peut en trouver
[ici](https://www.data.gouv.fr/fr/datasets/parcoursup-2021-voeux-de-poursuite-detudes-et-de-reorientation-dans-lenseignement-superieur-et-reponses-des-etablissements/) : comparer un fichier à lui-même, puis faire une copie comportant 
une erreur assez loin dans le fichier.

Modifier la deuxième fonction pour qu'elle s'arrête dès la première
différence trouvée, en affichant *la ligne concernée* (numéro et contenu
dans chaque fichier). 


#### Exercice 2 : `my-cp`

Écrire un programme `my-cp` tel que `my-cp fic1 fic2` copie `fic1` dans
`fic2` à l'aide d'une fonction `my-cat()` (sans paramètre) qui recopie 
le contenu de l'entrée standard sur la sortie standard; pour cela, votre
programme devra rediriger son entrée et sa sortie standard avant de faire
appel à `my-cat()`.

_(naturellement, `my-cat()` doit lire et écrire avec `read()` et `write()` 
en utilisant un tampon)_


#### Exercice 3 : introduction aux verrous

* Écrire un programme qui ouvre un fichier `compteur` et y écrit l'entier
  0 (représenté sous format textuel, en base 10). Il doit ensuite, 100000
  fois d'affilée, lire l'entier `n` contenu dans le fichier puis le
  remplacer par l'entier `n + 1`
* Modifier le programme pour qu'il crée *deux* processus, chacun
  incrémentant le compteur. Que constatez-vous?
* Modifier votre programme pour qu'il corrige le problème à l'aide de
  l'appel système `flock`, en réfléchissant au type de *lock* qu'il
  faudra utiliser, et à sa portée.


#### Exercice 4 : exclusion mutuelle

* Écrire une commande `lonely` qui affiche `je suis seul`
  toutes les cinq secondes sur sa sortie standard.  Pour garantir
  qu'au maximum une instance de `lonely` s'exécute à tout instant,
  le programme devra créer (de façon exclusive, `O_EXCL`) un fichier
  `/tmp/lonely.lock`, dans lequel il écrira son *pid*. Si une
  autre instance est déjà en cours d'exécution, le programme retourne 127
  immédiatement.  Au bout de `MAX` affichages, le programme termine,
  sans oublier de supprimer le fichier `/tmp/lonely.lock`.
* Lancer, tuer, puis relancer votre programme. Quel est le problème?
* Modifier votre programme pour qu'il utilise un verrou exclusif de type BSD,
  en faisant toujours en sorte qu'il quitte immédiatement si une autre
  instance est en cours d'exécution.


#### Exercice 5 : réservation de trains

Le but de cet exercice est d'implémenter un système de réservation de
places dans un train.  Le train sera représenté par un fichier de 1032
octets, où l'octet d'indice `n` vaut 1 si la place `n` est occupée, et
0 sinon.

* Créer un fichier `train.data` de 1032 octets nuls (par
  exemple par `dd if=/dev/zero bs=1032 count=1 of=train.data`).
* Écrire un programme qui prend un *lock* exclusif sur le fichier,
  recherche une place libre, la marque comme occupée, relâche le *lock*,
  puis affiche le numéro de la place réservée.  Si toutes les places
  sont occupées, votre programme affichera un message d'erreur
  compréhensible.
* Modifier le programme pour qu'il utilise un *lock* partagé lors de
  la recherche. S'il trouve une place libre, il devra relâcher le *lock*
  et prendre un *lock* exclusif pour la réservation. Pourquoi faut-il à
  nouveau vérifier la disponibilité de la place après avoir pris le
  *lock* exclusif? Quel est l'avantage de cette approche?
* Serait-il possible de promouvoir le *lock* au lieu de le relâcher?


#### Exercice 6 : verrou brisé

Tester les comportements respectifs des verrous **POSIX** et **BSD**, après
chaque étape, dans les contextes suivants (sous linux, vous pouvez
par exemple introduire des pauses de 5 secondes et consulter `/proc/locks`):

* Écrire un programme qui pose un verrou sur un fichier `test_verrou`,
duplique le descripteur, puis ferme le second descripteur. 

* Écrire un programme qui pose un verrou sur un fichier `test_verrou`,
ouvre une seconde fois `test_verrou`, puis ferme la seconde ouverture. 

* Écrire un programme qui pose un verrou sur un fichier `test_verrou`,
crée un processus fils, puis (dans le père) ferme le
descripteur. 

Vérifier à chaque fois si le verrou est toujours là...

Vous pouvez également recommencer en utilisant des verrous
  [OFD](https://www.gnu.org/software/libc/manual/html_node/Open-File-Description-Locks.html).


#### Exercice 7 : recodage des entrées/sorties haut niveau

L'exécution d'un appel système est particulièrement gourmande en temps
(environ 10000 fois plus lente que l'appel à une fonction qui ne contient
pas elle-même un appel système). Il est donc préférable de limiter les
appels systèmes. C'est dans ce but que les fonctions d'entrées/sortie
haut niveau (`fopen`, `fread`, `fwrite`, `fclose`) ont été écrites. On se
propose de vous les faire réécrire dans cet exercice.

Ce qui différencie `fwrite` et `fread` de `write` et `read`, c'est
l'utilisation d'un tampon (_buffer_) associé à chaque flot (caché dans le
type opaque `FILE`), et destiné à limiter le nombre de lectures et
écritures effectives, en particulier pour les fichiers sur disque :

- le buffer permet à `fread` de prendre de l'avance sur les lectures
  futures : même si l'utilisateur demande à lire moins de caractères que
  la taille du buffer, `fread` remplit ce buffer; à la lecture suivante,
  au lieu d'accéder à nouveau au fichier, il récupère les données dans
  son buffer.

- `fwrite` stocke les données à écrire dans le buffer tant que c'est
  possible; les données ne sont donc a priori pas écrites au moment de
  l'appel à `fwrite`, mais en général seulement quand le buffer est
  plein, ou dans certains cas particuliers : on peut forcer leur écriture
  avec `fflush`, et certains flots ont des politiques de bufferisation
  différentes (ligne à ligne sur les terminaux, ou à chaque caractère sur
  `stderr`).

Nous vous proposons de définir un type `MYFILE` de la manière suivante :

```C
#define BUFSIZE

enum mode {r, w, a};

typedef struct myfile {
   int fd;
   enum mode m;
   int begin, end;
   char buffer[BUFSIZE];
} MYFILE;
```

et d'écrire les fonctions

```C
MYFILE *myfopen(char *pathname, enum mode m);
void myfclose(MYFILE *stream);
int myfread(void *ptr, int size, int nmemb, MYFILE *stream);
int myfwrite(void *ptr, int size, int nmemb, MYFILE *stream);
int myfflush(MYFILE *stream);
```
dont les comportements doivent être équivalents respectivement à `fopen`,
`fclose`, `fread`, `fwrite` et `fflush`.

Par exemple, `myfread` peut appliquer l'algorithme suivant :

* si le buffer contient suffisamment de données non encore traitées,
  les lire directement dans le buffer (sans appel à `read`, donc);
* sinon,
  * commencer par traiter les données éventuellement disponibles dans le buffer;
  * tant que toutes les données demandées n'ont pas été lues et qu'il reste des
    données à lire dans le fichier,
  * remplir le buffer (soit en entier, soit avec les données
    disponibles)
  * utiliser les nouvelles données dans le buffer pour continuer la lecture.

