*Matthieu Le Franc : 71800858*

*Hugo Jacotot : 71802786*

# TP6

## Exercice 1

**Montrer que le numéro de consensus d’un objet pile est 2**

Le numéro de consensus représente le nombre minimum de processus requis pour parvenir à un accord sur une valeur commune. Dans le cas d'une pile, le numéro de consensus est 2 car il faut au minimum 2 processus pour parvenir à un accord sur une valeur commune. En effet, si un seul processus est présent, il n'y a par essence pas lieu de chercher un consensus. Si deux processus sont présents, il est possible de parvenir à un consensus.

## Exercice 2

**On propose d’implémenter une pile d’objets de capacité M_SIZE avec un tableau partagé Stack de M_SIZE éléments, chaque élément est un couple : valeur, compteur. Le haut de la pile (la variable partagée Top) est un triple index, valeur, compteur. On utilise des compare_ and_ swap (V.C&S(b,c) signifie si la valeur qui est dans V est égale à b alors on affecte c à V et on retourne vrai sinon on retourne faux) Initialement T op est (0, Null, 0) et tous les éléments de Stack sont initialisés à (Null,0) le pseudo code suivant**

![pile](pile.png)

```
push(v) {
    while (true) {
        (index, value, counter) <-- Top
        stackTop <-- Stack[index].value
        Stack[index], C&S( (stackTop, counter-1), (value,counter) )
        if (index=M_SIZE-1 ) then return Full
        aboveTop<-- Stack[index+1].counter
        if (Top.C&S( (index, value, counter), (index+1, v, aboveTop+1))) then return ok
    }
}
pop(){
    while (true){
        (index, value, counter) <-- Top
        stackTop<--Stack[index].value
        Stack[index].C&S( stackTop,counter-1), (value, counter)
        if (index=0) then return Empty
        belowTop<-- Stack[index-1]
        if (Top.C&S( (index, value, counter), (index-1, belowTop.value,belowTop.counter+1))) then return valu
    }
}
```