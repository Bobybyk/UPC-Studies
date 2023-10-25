TP nº2 : rappels de L3 : recouvrement et synchronisation père-fils
==================

**M1 Informatique - Programmation Système Avancée**


#### Exercice 1 : échauffement (tranquille)

* Quel est l'effet de la ligne de commande (shell) `exec ls`? Expliquer...

* Écrire un programme qui exécute la commande `ls` (sans
  créer de nouveau processus).

* Modifier votre programme pour qu'il exécute la commande `ls -l`.

* Modifier votre programme pour qu'il exécute la commande `ls -l` puis
  affiche le message `exécution terminée`.

_(indication : `ls -l` doit cette fois être exécutée *dans un processus
  fils*, et le processus principal doit attendre la fin de l'exécution du
  fils pour réaliser son propre affichage, puis se terminer et donc
  rendre la main au shell qui affichera alors son prompt)_


#### Exercice 2 : petite bombinette

Que pensez-vous des deux programmes suivants? (il n'est pas
nécessairement judicieux de les tester, réfléchissez-y à deux fois
d'abord...)

```
int main(int argc, char* argv[]) {
  write(STDOUT_FILENO, "*", 1);
  execvp(argv[0], argv);
  exit(1);
}
```

```
int main(int argc, char* argv[]) {
  fork();  
  sleep(1);
  write(STDOUT_FILENO, "*", 1);
  execvp(argv[0], argv);
  exit(1);
}
```

#### Exercice 3 : enchaînements non conditionnés

1. Écrire un programme `twice` tel que `./twice cmd arg1 .. argn` exécute
   deux fois consécutives la commande `cmd` avec ses arguments `arg1 ..
   argn`.

2. Écrire un programme `repeat` tel que `./repeat k cmd arg1 .. argn`
   exécute `k` fois consécutives la commande `cmd` avec ses arguments
   `arg1 .. argn`.


#### Exercice 4 : enchaînements conditionnés

Écrire un programme `si` qui se comporte (presque) comme la commande `if`
du **shell**. Votre programme prendra deux ou trois noms de commande en
paramètres : un test, une conséquence et optionnellement une alternative.
Il commencera par exécuter le test; si celui-ci retourne 0 (succès), il
exécutera la conséquence; sinon, si une alternative est proposée, il
exécutera cette dernière.
  
Votre programme ne doit créer qu'un seul processus fils. 

Par exemple, la commande `./si true emacs` doit exécuter `emacs`, tandis
que la commande `./si false emacs vi` doit exécuter `vi` (on rappelle que
les commandes `true` et `false` ne  font rien et retournent 0 et 1
respectivement).


#### Exercice 5 : devinette

Écrire un programme qui crée 10 processus, un père et ses fils. Chacun
tire une valeur aléatoire entre 1 et 100. Le père garde la sienne
secrète, tandis que les fils terminent en retournant leur tirage.  Le
père collecte les tirages de ses fils, puis annonce le vainqueur : le PID
du fils qui est le plus proche de sa valeur secrète.


#### Exercice 6 : `exit` ou signal?

Écrire un programme qui crée cinq processus fils; les fils affichent leur
pid puis se mettent en pause durant au maximum 1 minute jusqu'à réception
d'un signal, puis meurent. Le père doit annoncer les décès au fur et à
mesure en spécifiant pour chaque fils son `pid` et la cause de la mort
(signal reçu ou mort naturelle).

Tester le programme en tuant certains fils depuis un shell (avec la
commande `kill`).


#### Exercice 7 : zombies

On considère une commande `zombies` dont l'exécution provoque la
situation suivante :

```bash
poulalho@lulu:~$ ./zombies & 
poulalho@lulu:~$ sleep 5 ; ps j
   PPID     PID    PGID     SID TTY      STAT   UID   TIME COMMAND
1952538 1952539 1952539 1952539 pts/20   Ss    8159   0:00 -bash
1952539 1963818 1963818 1952539 pts/20   S     8159   0:00 zombies
1963818 1963822 1963818 1952539 pts/20   S     8159   0:00 zombies
1963822 1963823 1963818 1952539 pts/20   Z     8159   0:00 [sleep] <defunct>
1963822 1963824 1963818 1952539 pts/20   Z     8159   0:00 [sleep] <defunct>
1963822 1963825 1963818 1952539 pts/20   Z     8159   0:00 [sleep] <defunct>
1952539 1963861 1963861 1952539 pts/20   R+    8159   0:00 ps j
```

Dessiner la généalogie des processus créés par l'exécution de `zombies`
en précisant pour chaque processus son pid, la commande exécutée et son
état au bout des 5 secondes. Comment cet état peut-il s'expliquer?

La situation évolue ensuite de la manière suivante :

```bash
poulalho@lulu:~$ sleep 5 ; ps j
   PPID     PID    PGID     SID TTY      STAT   UID   TIME COMMAND
1952538 1952539 1952539 1952539 pts/20   Ss    8159   0:00 -bash
1952539 1963818 1963818 1952539 pts/20   S     8159   0:00 zombies
1952539 1963932 1963932 1952539 pts/20   R+    8159   0:00 ps j
```

Que s'est-il passé durant ces 5 dernières secondes?

Écrire un programme `zombies` ayant exactement le comportement observé
(et vérifier que c'est bien le cas).


