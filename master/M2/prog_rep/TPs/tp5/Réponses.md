# TP5

## Exercice 1

**On notera scani le résultat du scan effectué par la thread i. Parmi les propriétés suivantes, lesquelles sont vraies ? (démonstration ou contre-exemple)**
- **a). Pour tout i: scani[i] = i**, vrai car chaque thread exécute d'abord update(i), ce qui signifie qu'elle écrit sa propre valeur i à l'indice i dans le tableau partagé. Ensuite, lors du scan, chaque thread récupère la dernière valeur écrite à son indice, qui est précisément i dans ce cas.
- **b). Pour j != i, scanj [i] = i ou scanj [i] = −1**, vrai car pour toute thread j différente de la thread i, scanj[i] sera i (si la thread i a écrit à cet indice) ou -1 (si la thread i n'a pas encore écrit à cet indice).
- **c). Pour j != i, si scanj [i] = i alors scani[j] = j**, faux car si scanj[i] = i, pour une thread j ≠ i, alors scani[j] = i. Cela découle du fait que si une thread j voit une valeur i à l'indice i lors de son scan, alors la thread i doit avoir écrit cette valeur i à cet indice, et par conséquent, la valeur à l'indice j lors du scan de la thread i est également i.
- **d). Pour j != i, si scanj [i] = i alors scani[j] = −1**, faux car si :
    - la thread i exécute update(i), écrivant i à l'indice i,
    - la thread j exécute update(j), écrivant j à l'indice j,
    - la thread j effectue scan et obtient scanj[i] = i,
    - la thread i effectue scan

    alors il est possible que la thread j n'ait pas encore terminé son update, donc scani[j] pourrait ne pas être -1, mais plutôt la valeur que j a écrite à l'indice j.

- **Pour j != i, scanj [i] = i ou scani[j] = j** vrai :
    - Si scanj[i] = i, cela signifie que la thread j a lu la valeur i à l'indice i lors de son scan. Cela ne peut se produire que si la thread i a effectivement écrit à l'indice i. Ainsi, la dernière valeur écrite à l'indice i est i. Donc, lorsque la thread i effectue son scan, scani[j] devrait également être égal à j, car la dernière valeur écrite à l'indice j est j.
    - pour scani[j] = j, le raisonnement est le même pour scanj[i] = i et donc même conclusion.

    Donc scanj[i] = i ou scani[j] = j car si une thread j observe la valeur i à l'indice i, alors la thread i observera la valeur j à l'indice j.
 
- **Pour j != i, scanj ⊆ scani ou scani ⊆ scanj ( la relation ⊆ est A ⊆ B si et seulement si "pour tout i, si A[i]̸ = −1 alors A[i] = B[i]")**, vrai car l'opération est atomique donc si la thread j scan avant la thread i, alors scanj ⊆ scani, et si la thread i scan avant la thread j, alors scani ⊆ scanj.

## Exercice 2

**1/. On réalise une première implémentation de scan et de update et on utilise dans le programme suivant des threads qui ne font qu’écrire et une thread qui lit**

```java
public class SimpleSnap<T> implements Snapshot<T> {
    private T[] a_table;
    
    public SimpleSnap(int capacity, T init){
        a_table= (T[]) new Object[capacity];
        for (int i=0;i<capacity;i++) {
            a_table[i]=init;
        }
    }

    public void update(T v) {
        int me=ThreadID.get();
        a_table[me]=v;
        //ne fait pas partie de l’implémentation
        try { 
            MyThread.sleep(1);
        } catch(InterruptedException e){};
        
        MyThread.yield();
    }
    private T[] collect() {
        T[] copy= (T[]) new Object[a_table.length];
        
        for(int j=0;j<a_table.length;j++){ copy[j]=a_table[j];
            //ne fait pas partie de l’implémentation
            try { 
                MyThread.sleep(3);
            } catch(InterruptedException e){};
        MyThread.yield();
        }

        return copy;
    }

    public T[] scan(){
        T[] result;
        result=collect();
        return result;
    }
}

public class MyThread extends Thread{
    public SimpleSnap<Integer> partage;
    public int nb;

    public MyThread( SimpleSnap<Integer> partage, int nb) {
        this.partage=partage;
        this.nb=nb;
    }

    public void run(){
        if (ThreadID.get()!=0){
            partage.update(new Integer(1));
            partage.update(new Integer(2));
            partage.update(new Integer(3));
        } else {
            Object [] O=new Object[nb];
            O=partage.scan();
            System.out.print("scan de "+ThreadID.get() + ": ");
            
            for(int i=0;i<nb;i++) {
                System.out.print((Integer)O[i]+" ");
            } 
            
            System.out.println();
        }
    }
}
public class Main {

    public static void main(String[] args) {
        int nb=15;
        SimpleSnap<Integer> partage= new SimpleSnap<Integer>(nb,new Integer(0));
        MyThread R[]=new MyThread[nb];
        
        for (int i=0;i<nb;i++) {
            R[i]= new MyThread(partage,nb);
        }
        try {
            for (int i=0;i<nb;i++){ 
                R[i].start(); 
                if (i!=0) {
                    R[i].join();
                }
            }
        } catch(InterruptedException e){};
    }
}
```

**a). Toutes les exécutions de ce programme donnent-elles les mêmes affichages ?**

Non, les affichages ne sont pas les mêmes à chaque exécution car ils dépendent de l'ordonnancement des threads. Par exemple, si la thread 0 scan avant que les autres threads n'aient effectué leur update, alors le scan de la thread 0 affichera des valeurs 0. Si la thread 0 scan après que les autres threads aient effectué leur update, alors le scan de la thread 0 affichera des valeurs 1, 2, 3. Les mises à jour ne sont pas synchronisées entre les threads, ce qui signifie qu'elles pourraient être intercalées avec les opérations de la thread principale.

**b). En supposant que toutes les valeurs écrites dans chaque entrée du tableau sont différentes, l’implémentation réalise-t-elle l’atomicité des opérations update et scan? Si oui justifiez, si non donnez un exemple.**

Avec update on réalise l'atomicité car même sans gestion de la concurrence, chaque thread modifie sa case du tableau à son id. Par contre, pour scan, pas de gestion de la concurrence et on accède à toutes les cases du tableau, ce qui peut être problématique si d'autres threads font par exemple un update pendant qu'on scan.

Exemple :
    - La première thread commence une opération scan
    - La deuxième thread commence une opération update et écrit une valeur à son indice
    - La première thread copie le tableau partagé dans sa copie locale alors que T2 modifie une valeur
    - La copie retournée par la première thread contient une valeur incorrecte à son indice

**c). Que se passe t-il si une thread écrit 2 fois la même valeur (ex partage.update(new Integer(1)); partage.update(new Integer(2)); partage.update(new Integer(1));) ?**

Si une thread écrit deux fois la même valeur, alors la dernière valeur écrite à l'indice de la thread sera la valeur 1. Lorsque la thread principale effectue son scan, elle obtiendra la valeur 1 à l'indice de la thread qui a écrit deux fois la valeur 1.

**2/. Afin de réaliser une implémentation atomique, on associe une estampille à chaque écriture. On utilise la classe AtomicStampedReference<T> qui contient la référence d’un objet et un entier (l’estampille) qui sont mis à jour de façon atomique. Le scan réalise des lectures de la mémoire tant que deux lectures successives sont différentes. Quand elles sont identiques le résultat est la dernière lecture faite.**

- **a). Dans quel cas une exécution ne termine pas? Quelle condition de progression assure cette implémentation (obstruction-free? non locking? wait-free?)**
    - Une exécution ne termine pas si deux threads effectuent un scan en même temps et que les valeurs lues par les deux threads sont différentes. Dans ce cas, les threads continueront à lire la mémoire jusqu'à ce que les valeurs lues soient identiques.