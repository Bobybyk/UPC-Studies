# TP3

## Exercice 4 : exclusion mutuelle

* Écrire une commande `lonely` qui affiche `je suis seul`
  toutes les cinq secondes sur sa sortie standard.  Pour garantir
  qu'au maximum une instance de `lonely` s'exécute à tout instant,
  le programme devra créer (de façon exclusive, `O_EXCL`) un fichier
  `/tmp/lonely.lock`, dans lequel il écrira son *pid*. Si une
  autre instance est déjà en cours d'exécution, le programme retourne 127
  immédiatement.  Au bout de `MAX` affichages, le programme termine,
  sans oublier de supprimer le fichier `/tmp/lonely.lock`.
* Lancer, tuer, puis relancer votre programme. Quel est le problème?
* Modifier votre programme pour qu'il utilise un verrou exclusif de type BSD,
  en faisant toujours en sorte qu'il quitte immédiatement si une autre
  instance est en cours d'exécution.

Voici une implémentation possible de la commande `lonely` en langage C, utilisant un verrou exclusif (`O_EXCL`) pour garantir qu'une seule instance s'exécute à la fois, et s'arrêtant après un certain nombre d'affichages :

```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>

#define LOCKFILE "/tmp/lonely.lock"
#define MAX 10

int main() {
    int fd = open(LOCKFILE, O_RDWR|O_CREAT|O_EXCL, 0600);
    if (fd == -1) {
        if (errno == EEXIST) {
            printf("Another instance is already running.\n");
            return 127;
        } else {
            perror("Failed to create lockfile");
            return 1;
        }
    }
    char pid_str[10];
    sprintf(pid_str, "%d\n", getpid());
    write(fd, pid_str, strlen(pid_str));

    int count = 0;
    while (count < MAX) {
        printf("je suis seul\n");
        sleep(5);
        count++;
    }

    close(fd);
    unlink(LOCKFILE);
    return 0;
}
```

Lorsqu'on lance cette commande, le programme crée le fichier `/tmp/lonely.lock` en mode exclusif (`O_EXCL`), et y écrit son propre pid. Si le fichier existe déjà, cela signifie qu'une autre instance est déjà en cours d'exécution, et le programme s'arrête immédiatement en retournant la valeur 127.

Cependant, un problème peut survenir si l'exécution du programme est interrompue de manière abrupte (par exemple, en tuant le processus avec `kill` ou en interrompant le terminal avec `Ctrl-C`) : dans ce cas, le fichier `/tmp/lonely.lock` peut rester présent sur le système, et empêcher toute nouvelle exécution de `lonely`. Il est donc important de supprimer le fichier de verrouillage à la fin du programme, même en cas d'erreur ou d'exception.

Pour éviter ce problème, il est possible d'utiliser un verrou exclusif de type BSD, qui est automatiquement libéré lorsque le processus qui le détient se termine ou est interrompu. 

Voici une version modifiée du programme utilisant un tel verrou :

```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>
#include <sys/file.h>

#define LOCKFILE "/tmp/lonely.lock"
#define MAX 10

int main() {
    int fd = open(LOCKFILE, O_RDWR|O_CREAT, 0600);
    if (fd == -1) {
        perror("Failed to open lockfile");
        return 1;
    }
    int rc = flock(fd, LOCK_EX|LOCK_NB);
    if (rc == -1) {
        if (errno == EWOULDBLOCK) {
            printf("Another instance is already running.\n");
            return 127;
        } else {
            perror("Failed to lock file");
            return 1;
        }
    }

    char pid_str[10];
    sprintf(pid_str, "%d\n", getpid());
    write(fd, pid_str, strlen(pid_str));

    int count = 0;
    while (count < MAX) {
        printf("je suis seul\n");
        sleep(5);
        count++;
    }

    close(fd);
    unlink(LOCKFILE);
    return 0;
}
```

Dans cette version, le fichier de verrouillage est ouvert en mode non-exclusif, et le verrou est obtenu à l'aide de la fonction `flock`, en spécifiant l'option `LOCK_NB` pour éviter de bloquer le programme en cas de verrouillage impossible. Si le verrou n'a pas pu être obtenu, le programme s'arrête immédiatement en retournant la valeur 127. Dans tous les cas, le fichier de verrouillage est automatiquement libéré lorsque le processus se termine ou est interrompu.

Avec cette modification, le programme devrait fonctionner correctement même en cas d'interruption ou de terminaison abrupte, car le verrou sera automatiquement libéré.
