# TP1

## Exercice 1

**Comment expliquez vous les différences de comportement ?**

Dans un cas, on récupère l'id de la thread créatrice (start) et dans le deuxième on récupère l'id de la thread créée (run) et on travaille avec les données de la thread créée.

Avec la première version de ``MyThread2`` on obtient, lors de l'affichage de la thread lancée, l'ID du thread courant (avec tID.get()) et l'id stocké dans la variable myid.
```
la thread 10 0
la thread 5 0
la thread 3 0
la thread 6 0
la thread 9 0
la thread 2 0
la thread 7 0
la thread 1 0
la thread 8 0
la thread 4 0
```

l'id courant avec l'appel à get() donne effectivement l'id de la thread courante, mais l'id stocké dans la variable myid est toujours 0. Cela est dû au fait que la variable myid est définie dans le constructeur avec un appel à get() et à ce moment là, l'id retenue advitam eternam celui de la thread créatrice d'id 0.

Avec la deuxième version de ``MyThread2``, on obtient :
```
la thread 7 7
la thread 8 8
la thread 9 9
la thread 1 1
la thread 5 5
la thread 4 4
la thread 2 2
la thread 6 6
la thread 3 3
la thread 0 0
```
Cette fois-ci, l'id stocké dans la variable myid est l'id de la thread créée. Cela est dû au fait que la variable myid est définie avec un appel à get() à l'intérieur du run()

## Exercice 2

**Après avoir exécuté le Main2.main quelles sont les valeurs affichées ?**

```
la thread 1 a pour last 5000
la thread 0 a pour last 1000
value5996.0, valuebis5998.0 et  last 0
```

last = 0 car c'est le main qui exécute et donc la valeur de last n'est pas modifiée

**Comment assurer qu’à la fin du Main2.main on ait o.value=o.valuebis= nombre totale d’écritures ?**

Pour cela il faut ajouter un synchronized pour assurer l'atomisation (exclusion mutuelle) et que cela prenne aussi en compte l'impression de la valeur avec le System.out.print car autrement, la première valeur sera prête à être imprimée mais pas la deuxième qui pourra continuer à être incrémentée.

**Quelle est la difference entre les variables value et last ?**

La variable value est une variable partagée entre les threads et last est une variable locale à chaque thread.

## Exercice 4

Avec :

```java
public static volatile int check=0;
```
On obtient :
```
check = 1 cur = 0
check = 2 cur = 1
check = 3 cur = 2
check = 4 cur = 3
check = 5 cur = 4
check = 6 cur = 5
check = 7 cur = 6
check = 8 cur = 7
check = 9 cur = 8
check = 10 cur = 9
coucou
received 11 stop
```

Avec :
```java
public static int check=0;
```
On obtient :
```
received 11 stop
```

L'utilisation de volatile permet de garantir qu'on récupère une valeur à jour en allant regarder la valeur directement en mémoire partagée et non pas dans un registre local.

Donc, dans le cas où ce mot clef est utilisé, on obtient la valeur de check à jour, supérieure à cur, et on peut donc sortir de la boucle. Sans l'utilisation de volatile, on ne va pas chercher la bonne valeur de check pour effectuer la comparaison.