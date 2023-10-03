TP nº6 : synchronisation par mutexes et conditions
==================

**M1 Informatique - Programmation Système Avancée**


#### Exercice 1 : auto-blocage

Il est écrit dans le cours qu'un processus qui détient un verrou sur
un mutex ne doit jamais réexécuter `pthread_mutex_lock()`. En faisant
tourner le programme [betise1.c](betise1.c), constatez ce qui
se passe si on ne suit pas cette consigne. N'hésitez pas à utiliser
`strace` ou `gdb` pour plus de détail.


#### Exercice 2 : la pile n'est pas partagée

En faisant tourner [betise2.c](betise2.c), constatez que
les mutex doivent absolument être en mémoire partagée et qu'on ne peut
pas se contenter d'une variable héritée. 

(pour suivre le fils, utiliser l'option `-f` de `strace` avec `gdb`, ou configurer
le `follow-fork-mode` de `gdb`)


#### Exercice 3 : incrémentations, décrémentations et mots de Dyck

Écrire un programme qui crée une variable `var` de type `int` partagée (via un
`mmap()` anonyme ou l'utilisation d'un segment de mémoire partagée) et
l'initialise à 0 avant de créer un processus fils.

1. Chaque processus incrémente `var` 100000 fois, puis le processus père
   affiche la valeur finale. Faire en sorte que celle-ci vaille bien
   200000 (grâce à un `mutex`, sans utiliser le type `sigatomic_t`).

2. Pour vous assurer que *seule* la section critique est protégée, et
   que les deux processus s'exécutent bien en alternance, ajouter des
   affichages intermédiaires -- par exemple, chaque fois qu'un des
   processus fait passer `var` à un multiple de mille, il affiche son `pid`
   et la valeur de `var`.

3. Modifier le programme pour que le fils *décrémente* `var` au lieu de
   l'incrémenter, mais *sans jamais la rendre négative* : si `var` est
   nulle, il rend la main à son père par un petit appel à `nanosleep()`,
   puis essaie à nouveau de décrémenter la variable. Vérifier que la
   valeur finale est bien 0, et ajouter quelques affichages intermédiaires
   pour visualiser l'alternance -- par exemple, afficher tous les records
   (valeur maximale atteinte depuis le début du processus), et/ou les
   retours à 0.
   
4. Améliorer le point précédent en introduisant une variable de condition
   (ce qui rend les `nanosleep()` inutiles), et comparer les temps
   d'exécution des deux versions.


#### Exercice 4 : limitation du nombre de processus en parallèle

Écrire un programme initialisant un tableau d'un million d'entiers (ou
plutôt `1 << 20`) aléatoires entre 0 et 100, et affichant ensuite le
nombre de cases contenant l'entier 42.

Pour cela, le processus initial, un peu flemmard, initialise un compteur
partagé `cpt42` à 0 puis se duplique pour ne se charger d'effectuer la
recherche que dans la première moitié du tableau tandis que son fils se
charge de la deuxième. Chacun se duplique à son tour, et ainsi de suite
tant que la longueur du tableau à traiter est trop longue à leur goût --
mettons, tant qu'il contient plusieurs cases.  Lorsqu'un processus trouve
un 42, il incrémente le compteur `cpt42`.

Pour ne pas lancer simultanément un nombre déraisonnable de processus, le
programme initialise à 0 une deuxième variable `nbprocs` (partagée aussi) 
destinée à indiquer en temps réel le nombre de processus en cours
d'exécution; elle doit donc être incrémentée à chaque `fork()` (réussi),
et décrémenté à chaque fois qu'un processus termine. Cette variable ne
doit jamais dépasser une certaine valeur `NBMAX` nettement inférieure à
la taille du tableau -- mettons 64, par exemple. 

Une fois tout terminé, le processus initial affichera la valeur de
`cpt42` (et par acquis de conscience, recomptera lui-même tous les 42 du
tableau, à moins qu'il ait délègué cette vérification à un autre fils
s'exécutant en parallèle).


