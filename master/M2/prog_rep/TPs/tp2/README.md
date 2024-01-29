# TP2

## Exercice 1

```java
public class Exo {
    public static boolean fait = false;
    public static int n;
    
    //1
    public static class Lecteur extends Thread {
        public void run() {
            while (!fait);
            System.out.println(n);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //2
        new Lecteur().start();
        Thread.sleep(100);
        n = 150;
        fait = true;
        System.out.println("fait");
    }
}
```

**1/. Est ce que vous observez que la thread Lecteur ne termine pas ?**

Effectivement, la thread lecteur ne termine pas car le thread lecteur et modifie thread lecteur qui ne va jamais relire pendant ce temps.

**2/. On ajoute en //1 ```public static Integer I=3;``` et on remplace ```while (!fait);``` par ```while (!fait) {synchronized(I){} ;}``` Est ce que la thread Lecteur termine toujours ?**

Oui car on lit la valeur en mémoire partagée

**3/. On ajoute en //1 ```public static Integer I=3;``` et on remplace ```fait = true;``` par ```synchronized(I){fait = true;}``` Est ce que la thread Lecteur termine toujours ?**

La thread lecteur ne termine pas car elle ne relit pas donc ça ne change rien de synchronize sur la thread qui écrit, il faut le faire sur la thread de lecture.

**4/. On remplace ```public static boolean fait = false;``` par ```public static volatile boolean fait = false;``` Est ce que la thread Lecteur termine toujours ?**

Oui car volatile permet de lire la valeur en mémoire partagée, la dernière valeur écrite. Donc quand la valeur est mise à jour, le thread peut la lire.

**5/. On ajoute en //1 ```public static boolean [] t = new boolean[10];``` et en //2 ```t[0]=false;``` et on remplace ```while (!fait);``` par ```while (!t[0]);``` et ```fait=true;``` par ```t[0] = true;``` Est ce que la thread Lecteur termine toujours ?**

Ca ne termine pas car on ne relit pas la valeur de t[0] donc on ne voit pas la modification.

**6/. On ajoute en //1 ```public static volatile boolean [] t = new boolean[10];``` et en //2 ```t[0]=false;``` et on remplace ```while (!fait);``` par ```while (!t[0]);``` et ```fait=true;``` par ```t[0] = true;``` Est ce que la thread Lecteur termine toujours ?**

Oui car volatile permet de lire la valeur en mémoire partagée, la dernière valeur écrite. Donc quand la valeur est mise à jour, le thread peut la lire.

**7/. Est ce que la thread Lecteur termine toujours ?**

```java
public class Exo2 {
    public static int n;
    public static volatile boolean [][] t = new boolean[10][20];
    
    public static class Lecteur extends Thread {
        public void run() {
            //1
            while (!t[0][0]);
            System.out.println(n);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t[0][0]=false;
        new Lecteur().start();
        Thread.sleep(100);
        n = 150;
        t[0][0] = true;
        System.out.println("fait");
    }
}
```

Oui car volatile permet de lire la valeur en mémoire partagée, la dernière valeur écrite. Donc quand la valeur est mise à jour, le thread peut la lire.

**8/. On ajoute à Exo2 en //1 ```boolean [] lt=t[0];``` et on remplace ```while (!t[0][0]);``` par ```while (!lt[0]);``` Est ce que la thread Lecteur termine toujours ?**

**9/. Est ce que la thread Lecteur termine toujours ?**

**10/. On ajoute en //1 Essai ```x=fait.d;``` et on remplace ```while (! fait.d.a);``` par ```while (! x.a);``` Est ce que la thread Lecteur termine toujours ?**

```java
public class Exo {
    public static int n;
    public static class Essai{boolean a; int b;}
    public static class Essai2 {int c; Essai d;}
    public static volatile Essai2 fait=new Essai2();
    
    public static class Lecteur extends Thread {

        public void run() {
            //1
            while (! fait.d.a);
            System.out.println(n);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        fait.d= new Essai();
        fait.d.a=false;
        new Lecteur().start();
        Thread.sleep(100);
        n = 150;
        fait.d.a = true;
        System.out.println("fait");
    }
}
```

## Exercice 2

*Dans le package ```java.util.concurrent.locks``` se trouve l’interface Lock.*

```java
public interface Lock{
    public void lock();
    public void unlock();
}
```

**1/. Ecrire le code d’une thread qui rentre régulièrement en section critique en utilisant un verrou (une implementation de l’interface Lock). La section critique et la section non critique pourront être simulées par une mise en sommeil de la thread et l’impression d’un message indiquant le nombre de fois où cette thread est rentrée en section critique. Apres 20 entrées en section critique la thread s’arrête. Ecrire le code d’un programme qui lance un nombre variable de ces threads.**

```java
public class ThreadCrit {
    public static class Critique implements Runnable {
        private Lock lock;
        private int nb;
        private int id;

        public Critique(Lock lock, int id) {
            this.lock = lock;
            this.nb = 0;
            this.id = id;
        }

        public void run() {
            while (nb < 20) {
                
                lock.lock();

                // Section critique
                try {
                    System.out.println("la thread " + id + " entre en section critique pour la " + nb + " fois");
                    nb++;
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

            // Simulation en dehors de la section critique
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            new Thread(new Critique(lock, i)).start();
        }
    }
}
```

**2/. Utiliser l’implémentation de Java java.util.concurrent.locks.ReentrantLock de l’interface Lock pour exécuter votre programme (avec 10 threads).**

Exécuter code pour voir

**3/. Quelles sont les differences lors de l’execution sans demande d’équité ou avec demande de verrou équitable ?**

Avec demande d'équité :
- Les threads sont exécutées dans l'ordre d'arrivée (approche FIFO)
- Coût de performances car gestion plus complexe de la file d'attente (changements de contexte plus fréquents)
- Tous les threads finiront par obtenir le verrou (pas de starvation)
- Permet d'avoir un comportement plus prévisible 

Sans demande d'équité :
- Le thread accèdant au verrou est le plus prêt donc pas forcément celui qui attend depuis le plus longtemps (approche LIFO)
- Coût de performances réduit car gestion plus simple de la file d'attente (moins de changements de contexte)
- Possibilité de starvation (un thread peut attendre indéfiniment)

**4/. Pour implémenter le verrou on propose d’utiliser l’algorithme de la boulangerie de Lamport ci-dessous ( pseudo code) . ( On a ``(i,j) < < (k,l) si i<k ou si (i=k et j<l))``.**

```java
class Backery implements Lock {
    boolean flag[];
    Label [] label;
    
    public Bakery(int n){
        flag=new boolean [n];
        label=new Label[n];
    }
    
    for (int i=0; i<n; i++){
        flag[i]=false; label[i]=0;
    }
    
    public void lock(){
        int i= ThreadId.get();
        flag[i]=true;
        label[i]= max (label[0],label[1],...label[n-1])+1;
        
        while (Il existe k avec flag[k]&&( label[k],k)<< (label[i],i));
    }

    public unlock(){
        flag[ThreadId.get()]=false;
    }
}
```

**a).  Si la thread i a écrit ``label[i]`` à l’instant t et la thread j écrit ``label[j]`` à l’instant ``t′ > t``, a-t-on toujours ``labelt[i]<labelt'[j]`` ?**

Oui car ``label[i]`` est incrémenté à chaque fois qu'une thread entre en section critique donc on a toujours ``labelt[i] < labelt'[j]``.

**b). Si la thread i a écrit ``label[i]`` à l’instant t et que à cet instant ``flag[j] = false`` et la thread j écrit ``label[j]`` à l’instant ``t′ > t``, a-t-on toujours ``labelt[i]<labelt'[j]``**

- Condition Initiale : Au moment t, la thread i écrit label[i], et à cet instant flag[j] = false.
- Ensuite, à un instant t' > t, la thread j écrit label[j].
- Lorsque la thread i écrit label[i] à l'instant t, elle se prépare à entrer en section critique et flag[j] = false signifie que la thread j ne se prépare pas à entrer en section critique.
- Lorsque la thread j décide d'entrer en section critique, elle met flag[j] = true

Donc la réponse est oui car même si ``flag[j] == false``, lorsque i a écrit ``label[i]``, on aura toujours ``labelt[i] < labelt'[j]`` lorsque j écrit ensuite ``label[j]``.

**c). Si la thread i a écrit label[i] à l’instant t, combien de thread peuvent entrer en section critique après t et avant que la thread i n’entre en section critique. Donnez un exemple d’exécution où la thread laisse passer le plus grand nombre de thread avant de pouvoir rentrer en SC.**

- La thread i a écrit label[i] à l'instant t et se prépare à entrer en section critique mais elle doit attendre que toutes les threads avec des labels inférieurs terminent leur exécution en section critique.
- Si d'autres threads commencent le processus pour entrer en section critique après t mais avant que i entre, on leur attribue des labels supérieurs à celui de i.

Exemple d'exécution avec 4 threads :

- à l'instant t, la thread 1 écrit.
- avant l'instant t, les threads 0, 2 et 3 ont déjà écrits leurs labels mais tous leurs flags étaient false donc elles n'attendaient pas activement pour entrer en section critique. 
- à t+1, les threads 0, 2 et 3 mettent leurs flags à true et se préparent à entrer en section critique.
- même si la thread 1 a écrit son label avant les autres, elle devra attendre que les threads 0, 2 et 3 terminent leur exécution en section critique avant de pouvoir y entrer elle même.
