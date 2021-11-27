#include<stdio.h>
#include<stdlib.h>
#include<time.h>

#define M 4
#define N 6

void aff_tab(int[], int);
int max(int[], int);

int main(void) {
    srand(time(NULL));

    int tab[M];

    for (int i=0; i<M; i++) {
        tab[i] = rand() % (N+1);
    }

    aff_tab(tab, M);

    printf("L'indice du max est %d.\n", max(tab, M));
    return 0;

}

/* Affiche le tableau passe en parametres
 */
void aff_tab(int tab[], int size) {
    for (int i = 0; i<size; i++) {
        printf("%d ", tab[i]);
    }
    printf("\n");
}


/* Renvoie l'indice de l'element max du tableau.
 * Si plusieurs elements max, renvoie le plus petit indice.
 * Renvoie -2 si le tableau est vide.
 */
int max(int tab[], int size) {
    if (size == 0) return -1;

    int indice = 0;
    int max = tab[0];

    for (int i = 0; i < size; i++) {
        if (tab[i] > max) {
            indice = i;
        }
    }
    return indice;
}
