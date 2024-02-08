# TP3

## Exercice 1

**L’histoire ci dessous est-elle linéarisable par rapport à la specification séquentielle d’une file ?**

Pour que l'histoire soit linéarisable il faut que :
- Les opérations de retrait de la file d'attente doivent renvoyer les éléments dans l'ordre dans lequel ils ont été mis en file d'attente.
- Chaque opération de retrait de la file d'attente doive correspondre à une opération de mise en file d'attente précédente.

L'histoire n'est donc pas linéarisable par rapport à la spécification séquentielle d'une file car les opérations de retrait de la file d'attente ne reflètent pas l'ordre FIFO des opérations de mise en file d'attente.

**p2 et p3 aurait-il pu obtenir une autre réponse à leur demande de deq ?**

Pour respecter les spécifications d'une file d'attente FIFO, les réponses aux demandes de deq() de P2 et P3 devraient être :
- La première opération deq() retourne x, car x est le premier élément qui a été enfilé (enq()).
- La seconde opération deq() retourne y, puisque y est le second élément ajouté après x.

Hors, ici P2 reçoit y et P3 reçoit x, ce qui n'est pas conforme à l'ordre FIFO pour que l'histoire soit linéarisable, donc non.

## Exercice 2

**1/. E n vous inspirant de la définition classique d’une pile donnez la définition et la spécification séquentielle de cet objet.**

Une pile est une structure de données linéaire qui obéit au principe LIFO (Last In First Out). C'est-à-dire que le dernier élément ajouté est le premier à être retiré. La pile concurente est une pile qui peut être utilisée par plusieurs threads en même temps

**2/. On considère l’implémentation suivante :**

**Cette implémentation réalise-t-elle une implémentation linéarisable d’une pile ? Si oui indiquer les points de linéarisation.**

**3/. On supprime les synchronized, l’implémentation réalise-t-elle une implémentation linéarisable d’une pile dans les cas suivants: (Si non indiquez quelles propriétés sont perdues, si oui indiquez les points de linéarisation)**

**(a) Il n’y a pas de concurrence entre les appels des threads.**

Implémentation linéarisable d'une pile, synchronized est superflu.

**(b) Quand il y a concurrence entre deux threads, c’est 2 threads qui appellent empiler**

On ne peut pas appeler cela une implantation linéarisable car si deux thread empilent en même temps, l'opération n'est plus atomique.

**(c) Quand il y a concurrence entre deux threads, c’est 2 threads qui appellent dépiler**
**(d) Quand il y a concurrence entre deux threads, c’est une thread qui appelle emplier et l’autre dépiler**