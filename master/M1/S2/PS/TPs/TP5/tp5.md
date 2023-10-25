TP nº 5 : `mmap` (bis)
==================

**M1 Informatique - Programmation Système Avancée**


#### Exercice 1 : crible d'Ératosthène

Le crible d'Ératosthène est un algorithme pour trouver tous les
nombres premiers inférieurs à un entier _N_ fixé. Son principe est de
supprimer petit à petit les entiers dont on constate qu'ils ne sont
pas premiers (parce qu'ils sont multiples d'un autre entier). Plus
précisément :

* on considère un tableau de _N+1_ cases, la case _i_ contenant
  l'entier _i_,
* on pose _k=1_
* tant que c'est possible

    * soit _p_ le plus petit entier _>k_ non rayé
    * on raye les multiples de _p_ du tableau
    * _k=p_

Vous pouvez voir une animation explicative sur
[https://fr.wikipedia.org/wiki/Crible_d'Ératosthène](https://fr.wikipedia.org/wiki/Crible_d'Ératosthène).

Pour cet exercice, vous allez créer un tableau d'entiers de `N+1` cases en
mémoire partagée, tel que la case `i` du tableau vaut `0` si l'entier
`i` a été rayé et `1` sinon.

1. Implémenter une fonction `int *initialisation(int N)` qui crée le
   tableau initial en mémoire partagée et renvoie l'adresse en mémoire
   en cas de succès, `MAP_FAILED` en cas d'échec.
2. Implémenter une fonction `void affiche(int *map, int N)` qui
   affiche la liste des indices pour lesquels la case correspondante
   contient `1`.
3. Implémenter une fonction `void raye_multiples(int *map, int N, int
   p)` qui raye du tableau les multiples de `p` (c'est-à-dire met à
   `0` les cases les ayant comme indices).
4. Écrire le programme complet qui crée le tableau en mémoire partagée
   et crée `N-1` fils : chaque fils s'occupe d'un entier entre `2`
   et `N`; si l'entier est déjà rayé (_i.e._ sa case vaut 0), il ne
   fait rien, sinon il raye les multiples de l'entier du tableau.
5. Le programme principal affiche la liste des nombres premiers trouvés une
   fois que tous les fils ont fini leur travail.


#### Exercice 2 : partage dynamique de fonction

On souhaite partager entre plusieurs processus le code d'une fonction,
sans que cette fonction fasse partie de l'exécutable des processus. On va
pour cela reproduire le fonctionnement d'une bibliothèque partagée,
chargée dynamiquement (`man dlopen, dlsym`). Pour cela, on écrira d'abord
un programme qui copie une fonction dans un fichier, puis un autre
programme qui projette ce fichier en mémoire et appelle la fonction :

* Écrire un programme `loadadd.c` définissant une fonction `add(int, int)`
  qui renvoie la somme de ses deux arguments. 
  Le `main()` de `loadadd.c` doit créer (s'il n'existe pas) un fichier
  `libop.so` de 1024 octets, le projeter en mémoire, et y copier (avec
  `memcpy()`) les 1024 octets débutant à (l'adresse de) la fonction `add()`. 
  Exécuter le programme et vérifier que le fichier `libop.so` a
  bien été créé.

* Écrire un programme `use.c` qui crée une zone mémoire partagée,
  synchronisée avec le fichier `libadd.a`, avec le flag `PROT_EXEC`
  pour pouvoir exécuter le code de cette zone mémoire.
  Affecter à un pointeur `f` de type `int (*)(int, int)` l'adresse de
  cette zone mémoire. Afficher le résultat de l'appel `f(32, 10)` et
  admirer la magie... Vérifier que vous pouvez lancer plusieurs processus
  exécutant `use` simultanément (avec `./use & ./use & ...`)

* Écrire, sur le modèle de `loadadd.c`, un programme `loadmult.c` qui
  remplace dans `libop.so` le code de `add()` par celui d'une fonction
  `mult()` qui renvoie le produit de ses deux arguments. 
  Exécuter `loadmult` puis réexécuter `use` (sans le recompiler au
  préalable) et comparer avec la précédente exécution.

* Modifier `use.c` pour qu'il fasse plusieurs appels à `f(32, 10)`, séparés
  par une exécution de `loadadd` ou de `loadmult`, et constater que la
  fonction appelée varie effectivement (ou modifier ce qu'il faut pour
  que ce soit le cas!).

