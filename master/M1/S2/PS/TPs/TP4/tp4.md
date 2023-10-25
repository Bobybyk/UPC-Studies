TP nº 4 : `mmap`
==================

**M1 Informatique - Programmation Système Avancée**


#### Exercice 0 : de la nécessité de la mémoire partagée... et des problèmes qu'elle pose

Écrire un programme qui initialise une variable entière `var` à 0, puis 
fait un appel à `fork()`. Le processus fils affecte à `var` la valeur
100000 puis termine; le processus père attend la terminaison de son fils
puis affiche la valeur de `var`. 

Quel est le comportement de ce programme?

Utiliser ensuite `mmap()` (en version anonyme ou non) pour permettre aux
deux processus de partager une deuxième variable entière `*pvar`, et
vérifier que cette fois le comportement attendu est obtenu.

Modifier ensuite le programme pour que chacun des deux processus
incrémente `*pvar` 100000 fois avant l'affichage final. Le comportement
est-il satisfaisant? Si c'est le cas, exécuter le programme un grand
nombre de fois pour vérifier si ce comportement est déterministe (par
exemple par une boucle `for i in {1..100}; do ./a.out; done | uniq -c`).
Comment expliquer ce phénomène?

Recommencer en utilisant cette fois une variable de type `atomic_int`...


#### Exercice 1 : `cat` et `cp` sans `read()`

* Écrire un programme qui projette en mémoire (avec `mmap()`)
  un fichier dont le nom lui est passé en paramètre, puis affiche son
  contenu sur sa sortie standard ***sans*** appel à `read()`.

* Écrire un programme qui prend deux paramètres `src` et `dest`, et qui
  copie le fichier de nom `src` vers le fichier de nom `dest` sans
  utiliser de tampons ni les appels système `read()` et `write()`.
  Attention aux tailles relatives des fichiers -- penser à `ftruncate()`.


#### Exercice 2 : chiffrement de César

Écrire un programme qui projette en mémoire un fichier texte supposé
ne contenir que des lettres et décale toutes les lettres de la même
valeur, en suivant le code de César.

On pourra utiliser la fonction suivante :

```C
char cesar(char c, int offset) {
  if(c>='a' && c<='z')
    return (char) ('a' + ( (c-'a'+offset)%26 ));
  if (c>='A' && c<='Z')
    return (char) ('A' + ( (c-'A'+offset)%26 ));
  return c;
}
```

#### Exercice 3 : chiffrement de Vigenère

Écrire un programme qui projette en mémoire un fichier texte supposé
ne contenir que des lettres. Il faut ensuite appliquer un code de
Vigenère de la manière suivante : il y aura autant de fils que la
longueur de la clé, et chaque fils s'occupera de son propre
décalage. Tous les fils travailleront en parallèle sur la projection
mémoire du fichier.
