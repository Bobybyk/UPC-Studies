#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/file.h>
#include <string.h>
#include <sys/wait.h>

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