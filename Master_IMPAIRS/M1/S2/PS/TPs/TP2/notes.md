# TP2

## Exercice 1 

**Quel est l'effet de la ligne de commande (shell) `exec ls` ?**

La commande exec ls remplace le processus du shell actuel par le processus du programme ls. Cela signifie que le shell ne retournera pas à l'état précédent une fois que le programme ls sera terminé, car le shell aura été remplacé par ce dernier. Au lieu de cela, le résultat de l'exécution de ls sera affiché directement sur la sortie standard et le terminal sera fermé une fois que ls sera terminé.

**Programme executant la commande `ls` sans créer de nouveau processus**

```c
#include <unistd.h>

int main(void) {
  char *const args[] = {"ls", NULL};
  execvp("ls", args);
  return 0;
}
```

**Programme executant la commande `ls -l`**

```c
#include <unistd.h>

int main(void) {
  char *const args[] = {"ls", "-l", NULL};
  execvp("ls", args);
  return 0;
}
```

**Programme executant la commande `ls -l` puis affichant le message `exécution terminée`**

On ne peut pas afficher exécution terminée après l'exécution de la commande `ls -l` car `execvp` remplace complétement le processur actuel par celui de `ls`. Pour afficher le message, on doit créer un nouveau processus pour exécuter `ls -l` et attendre que celui-ci se termine avant d'afficher le message.

Ce programme utilise la fonction fork pour créer un nouveau processus qui exécutera la commande `ls -l`. Le processus parent attend la fin de l'exécution de ce processus avec la fonction wait et affiche le message "exécution terminée" une fois que le processus fils est terminé.

```c
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int main(void) {
  pid_t pid = fork();
  if (pid == 0) {
    char *const args[] = {"ls", "-l", NULL};
    execvp("ls", args);
  } else {
    int status;
    wait(&status);
    printf("exécution terminée\n");
  }
  return 0;
}
```

## Exercice 2

Ce programme ne fait rien de particulièrement utile ou intéressant. Il commence par écrire un caractère '*' sur la sortie standard en utilisant la fonction write. Ensuite, il appelle la fonction `execvp` en passant les arguments de la ligne de commande (contenus dans argv) pour remplacer le processus actuel par un autre programme.

Cependant, ce remplacement n'aura aucun effet car le nom du programme à exécuter (passé en premier argument à `execvp`) est celui du programme actuel, donc `execvp` ne fera rien.

Enfin, le programme appelle la fonction exit avec le code de retour 1, indiquant une erreur.

Dans l'ensemble, ce programme n'a pas de fonctionnalité utile et n'est pas très utile ou significatif.

```c
int main(int argc, char* argv[]) {
  write(STDOUT_FILENO, "*", 1);
  execvp(argv[0], argv);
  exit(1);
}
```

Ce programme crée un nouveau processus en utilisant la fonction fork, ce qui signifie que le programme sera exécuté deux fois : une fois par le processus parent et une fois par le processus fils.

Après la création du processus fils, les deux processus (parent et fils) attendent 1 seconde en utilisant la fonction sleep. Ensuite, les deux processus écrivent un caractère '*' sur la sortie standard en utilisant la fonction write.

Ensuite, les deux processus appellent la fonction execvp en passant les arguments de la ligne de commande (contenus dans argv) pour remplacer leur processus actuel par un autre programme. Cependant, comme c'était le cas pour le programme précédent, ce remplacement n'aura aucun effet car le nom du programme à exécuter (passé en premier argument à execvp) est celui du programme actuel.

Enfin, les deux processus appellent la fonction exit avec le code de retour 1, indiquant une erreur.

Dans l'ensemble, ce programme n'a pas de fonctionnalité utile, et peut être confusant car il peut sembler exécuter deux fois le même code, ce qui peut entraîner des comportements imprévisibles ou des erreurs.

```c
int main(int argc, char* argv[]) {
  fork();  
  sleep(1);
  write(STDOUT_FILENO, "*", 1);
  execvp(argv[0], argv);
  exit(1);
}
```
