# TP3

## Exercice 3 : introduction aux verrous

* Écrire un programme qui ouvre un fichier `compteur` et y écrit l'entier
  0 (représenté sous format textuel, en base 10). Il doit ensuite, 100000
  fois d'affilée, lire l'entier `n` contenu dans le fichier puis le
  remplacer par l'entier `n + 1`
* Modifier le programme pour qu'il crée *deux* processus, chacun
  incrémentant le compteur. Que constatez-vous?
* Modifier votre programme pour qu'il corrige le problème à l'aide de
  l'appel système `flock`, en réfléchissant au type de *lock* qu'il
  faudra utiliser, et à sa portée.

Voici un exemple de programme en langage C pour incrémenter un compteur stocké dans un fichier nommé `compteur`:

```c
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

int main() {
    int fd = open("compteur", O_RDWR | O_CREAT, 0644);
    if (fd == -1) {
        perror("open");
        exit(1);
    }

    // Écrire 0 dans le fichier compteur
    char buf[16];
    sprintf(buf, "%d", 0);
    if (write(fd, buf, strlen(buf)) == -1) {
        perror("write");
        exit(1);
    }

    // Incrémenter le compteur 100000 fois
    lseek(fd, 0, SEEK_SET);
    for (int i = 0; i < 100000; i++) {
        // Lire l'entier n contenu dans le fichier
        if (read(fd, buf, 16) == -1) {
            perror("read");
            exit(1);
        }
        int n = atoi(buf);

        // Réécrire n+1 dans le fichier
        lseek(fd, 0, SEEK_SET);
        sprintf(buf, "%d", n + 1);
        if (write(fd, buf, strlen(buf)) == -1) {
            perror("write");
            exit(1);
        }
    }

    if (close(fd) == -1) {
        perror("close");
        exit(1);
    }

    return 0;
}
```

Pour ajouter deux processus incrémentant le compteur, on peut utiliser la fonction **fork()** pour créer un processus fils qui exécute le même code que le processus père :

```c
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/file.h>
#include <string.h>
#include <sys/wait.h>

int main() {

    // Créer/ouvrir le fichier compteur
    int fd1 = open("compteur", O_RDWR | O_CREAT, 0644);
    if (fd1 == -1) {
        perror("open");
        exit(1);
    }

    int fd2 = open("compteur", O_RDWR | O_CREAT, 0644);
    if (fd2 == -1) {
        perror("open");
        exit(1);
    }

    // Écrire 0 dans le fichier compteur
    char buf[16];
    sprintf(buf, "%d", 0);
    if (write(fd1, buf, strlen(buf)) == -1) {
        perror("write");
        exit(1);
    }

    // Créer un processus fils
    pid_t pid = fork();

    if (pid == -1) {
        perror("fork");
        exit(1);
    }

    if (pid == 0) {
        // Code exécuté par le processus fils
        for (int i = 0; i < 100000; i++) {
            // Verrouiller le fichier
            flock(fd1, LOCK_EX);

            // Lire l'entier n contenu dans le fichier
            lseek(fd1, 0, SEEK_SET);
            if (read(fd1, buf, 16) == -1) {
                perror("read");
                exit(1);
            }
            int n = atoi(buf);

            // Réécrire n+1 dans le fichier
            lseek(fd1, 0, SEEK_SET);
            sprintf(buf, "%d", n + 1);
            if (write(fd1, buf, strlen(buf)) == -1) {
                perror("write");
                exit(1);
            }

            // Déverrouiller le fichier
            flock(fd1, LOCK_UN);
        }
    } else {
        // Code exécuté par le processus père
        for (int i = 0; i < 100000; i++) {
            // Verrouiller le fichier
            flock(fd2, LOCK_EX);

            // Lire l'entier n contenu dans le fichier
            lseek(fd2, 0, SEEK_SET);
            if (read(fd2, buf, 16) == -1) {
                perror("read");
                exit(1);
            }
            int n = atoi(buf);

            // Réécrire n+1 dans le fichier
            lseek(fd2, 0, SEEK_SET);
            sprintf(buf, "%d", n + 1);
            if (write(fd2, buf, strlen(buf)) == -1) {
                perror("write");
                exit(1);
            }
            // Déverrouiller le fichier
            flock(fd2, LOCK_UN);
        }

        // Attendre que le processus fils se termine
        if (wait(NULL) == -1) {
            perror("wait");
            exit(1);
        }

        if (close(fd1) == -1) {
            perror("close");
            exit(1);
        }
        if (close(fd2) == -1) {
            perror("close");
            exit(1);
        }
    } return 0;
}
```

Le problème avec ce code est que les deux processus peuvent essayer de modifier le fichier en même temps, ce qui peut conduire à des résultats imprévisibles. 

Pour éviter cela, on peut utiliser l'appel système **flock()** pour verrouiller le fichier pendant que chaque processus effectue son incrémentation. 

Voici la version modifiée du programme :

```c
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/file.h>

int main() {
    int fd = open("compteur", O_RDWR | O_CREAT, 0644);
    if (fd == -1) {
        perror("open");
        exit(1);
    }

    // Écrire 0 dans le fichier compteur
    char buf[16];
    sprintf(buf, "%d", 0);
    if (write(fd, buf, strlen(buf)) == -1) {
        perror("write");
        exit(1);
    }

    // Créer un processus fils
    pid_t pid = fork();

    if (pid == -1) {
        perror("fork");
        exit(1);
    }

    if (pid == 0) {
        // Code exécuté par le processus fils
        for (int i = 0; i < 100000; i++) {
            // Verrouiller le fichier
            flock(fd, LOCK_EX);

            // Lire l'entier n contenu dans le fichier
            lseek(fd, 0, SEEK_SET);
            if (read(fd, buf, 16) == -1) {
                perror("read");
                exit(1);
            }
            int n = atoi(buf);

            // Réécrire n+1 dans le fichier
            lseek(fd, 0, SEEK_SET);
            sprintf(buf, "%d", n + 1);
            if (write(fd, buf, strlen(buf)) == -1) {
                perror("write");
                exit(1);
            }

            // Déverrouiller le fichier
            flock(fd, LOCK_UN);
        }
    } else {
        // Code exécuté par le processus père
        for (int i = 0; i < 100000; i++) {
            // Verrouiller le fichier
            flock(fd, LOCK_EX);

            // Lire l'entier n contenu dans le fichier
            lseek(fd, 0, SEEK_SET);
            if (read(fd, buf, 16) == -1) {
                perror("read");
                exit(1);
            }
            int n = atoi(buf);

            // Réécrire n+1 dans le fichier
            lseek(fd, 0, SEEK_SET);
            sprintf(buf, "%d", n + 1);
            if (write(fd, buf, strlen(buf)) == -1) {
                perror("write");
                exit(1);
            }
            // Déverrouiller le fichier
            flock(fd, LOCK_UN);
        }

        // Attendre que le processus fils se termine
        if (wait(NULL) == -1) {
            perror("wait");
            exit(1);
        }

        if (close(fd) == -1) {
            perror("close");
            exit(1);
        }
    } return 0;
}
```