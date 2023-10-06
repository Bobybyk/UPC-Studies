#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <pthread.h>
#include <sys/wait.h>

/*
 * Un programme qui crée une variable var de type int partagée 
 * (via un mmap() anonyme ou l'utilisation d'un segment de mémoire partagée) 
 * et l'initialise à 0 avant de créer un processus fils.
 * 
 * Chaque processus incrémente var 100000 fois, 
 * puis le processus père affiche la valeur finale. 
 * Fais en sorte que celle-ci vaille bien 200000 
 * (grâce à un mutex, sans utiliser le type sigatomic_t).
 * 
 * Pour s'assurer que seule la section critique est protégée, 
 * et que les deux processus s'exécutent bien en alternance, 
 * ajoute des affichages intermédiaires (chaque fois qu'un des processus 
 * fait passer var à un multiple de mille, il affiche son pid et la valeur de var).
 */
int main() {
    pthread_mutex_t *mutex = mmap(NULL, sizeof(pthread_mutex_t), PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);

    if (pthread_mutex_init(mutex, NULL) != 0) {
        perror("pthread_mutex_init");
        exit(1);
    }

    int *var = mmap(NULL, sizeof(int), PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
    if (var == MAP_FAILED) {
        perror("mmap");
        exit(1);
    }
    *var = 0;
    pid_t pid = fork();
    if (pid == -1) {
        perror("fork");
        exit(1);
    }
    if (pid == 0) {
        // fils
        for (int i = 0; i < 100000; i++) {
            int ret = pthread_mutex_lock(mutex);
            if (ret != 0) {
                perror("pthread_mutex_lock");
            }
            *var += 1;
            ret = pthread_mutex_unlock(mutex);
            if (ret != 0) {
                perror("pthread_mutex_unlock");
            }
        }
    } else {
        // père
        wait(NULL);
        printf("var = %d\n", *var);
    }
}