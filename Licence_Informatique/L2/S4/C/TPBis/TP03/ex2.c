#include<stdio.h>

void minmax(int n, int t[], int *pmin, int *pmax) {
    // donne les indices du plus petit et du plus grand element du tableau t de taille n
    int min = t[0];
    int max = t[0];
    *pmin = t[0];
    *pmax = t[0];
    for (int i= 0; i<n; i++) {
        if (t[i] < min) {
            *pmin = i;
            min = t[i];
        }
        if (t[i] > max) {
            *pmax = i;
            max = t[i];
        }
    }
}

void occurences(int n, int t[], int e, int *pocc, int *first) {
    // donne le nombre d'occurences de e, et le premier indice qui contient e
    *pocc = 0;
    int vu = 0;
    for (int i = 0; i<n; i++) {
        if (t[i] == e) {
            *pocc += 1;
            if (vu == 0) {
                vu = 1;
                *first = i;
            }
        }
    }
}

void occurences_bis(int n, int t[], int e, int *pocc, int **first) {
    // donne le nombre d'occurences de e, et le premier indice qui contient e sous forme de pointeur vers la
    // premiere case du tableau aui contient e
    *pocc = 0;
    int vu = 0;
    for (int i = 0; i<n; i++) {
        if (t[i] == e) {
            *pocc += 1;
            if (vu == 0) {
                vu = 1;
                *first = &t[i];
            }
        }
    }
}

int main(void) {
    int t[] = {1, 45, 3, 4, 5, 0, 4};
    printf("Doit afficher : min -> 5 et max -> 1\n");
    int min, max;
    minmax(6, t, &min, &max);
    printf("min : %d\n", min);
    printf("max : %d\n", max);
    printf("\n");
    int t2[] = {1, 2, 4, 2, 2, 3, 4, 2, 4, 5, 2, 1, 2};
    int first, pocc;
    occurences(13, t2, 2, &pocc, &first);
    printf("Le premier index de 2 est : %d (doit afficher 1)\n", first);
    printf("Le nombre d'occurences de 2 est : %d (doit afficher 6)\n", pocc);

    printf("\n");
    int pocc2;
    int *first2;
    occurences_bis(13, t2, 2, &pocc2, &first2);
    printf("Au premier index, il y a la valeur : %d (doit afficher 2)\n", *first2);
    printf("Le nombre d'occurences de 2 est : %d (doit afficher 6)\n", pocc2);
    return 0;
}
