#include <stdio.h>
#include <sys/mman.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>

void raye_multiples(int *map, int n, int p) {

}

void affiche(int *map, int n) {

}

/*
 * crée un tableau initial en mémoire partagée et renvoie 
 * l'adresse en mémoire en cas de succès, MAP_FAILED en cas d'échec.
 */
int *initialisation(int n) {
    int *tab = mmap(NULL, sizeof(int)*(n+1), PROT_READ | PROT_WRITE, MAP_ANONYMOUS | MAP_SHARED, 0, 0);
    if(tab == MAP_FAILED) {
        perror("Use of mmap in initialisation()");
    }
    for(int i = 0 ; i <= n ; i += 1) {
        tab[i] = 1;
    }
    return tab;
}

int main() {
    return 0;
}