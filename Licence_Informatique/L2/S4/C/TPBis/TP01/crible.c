#include<stdio.h>

void crible(int);

int main(void) {
    crible(30);
    return 0;
}

void crible(int sup) {
    // creation du tableau
    int tab[sup];
    for (int i =0; i<sup; i++) {
        tab[i] = i;
    }

    // crible
    for (int i = 2; i<sup; i++) {
        // si le nombre n'est deja efface
        if (tab[i] != 0) {
            // on efface ses multiples dans les suivants
            printf("%d ", i);
            for (int j = i+1; j<sup; j++) {
                if (tab[j] % i == 0) {
                    tab[j] = 0;
                }
            }
        
        }
    }
}
