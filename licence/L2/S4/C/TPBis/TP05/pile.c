#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include<string.h>

typedef struct pile_amortie {
    int occupation;
    int capacite;
    int *elements;
} pile_amortie;

void affiche_pile(pile_amortie *pile) {
    printf("capacite : %d\n", pile->capacite);
    printf("occupation : %d\n", pile->occupation);
    for (int i = 0; i<pile->occupation; i++) {
        printf("%d ", pile->elements[i]);
    }
    printf("\n\n");
}

pile_amortie *alloue_pile_amortie() {
    pile_amortie *pile = malloc(sizeof(pile_amortie));
    assert(pile != NULL);
    int *new_elements = malloc(sizeof(int));
    assert(new_elements != NULL);
    pile->occupation=0;
    pile->capacite=1;
    pile->elements=new_elements;
    return pile;
}

void libere_pile_amortie(pile_amortie *pile) {
    free(pile->elements);
    free(pile);
}

int empile_pile_amortie(pile_amortie *pile, int n) {
    if (pile->capacite == pile->occupation+1) {
    // il faut etendre le tableau
        int *new_elements = realloc(pile->elements, (pile->capacite*2)*sizeof(int));
        // ne pas faire p = realloc(p, ...) car si realloc echoue, on pert l'adresse 
        // de p et on a une fuite de memoire

        if (new_elements == NULL) {
            return -1; // l'allocation echoue
        }
        //free(pile->elements); // free l'ancien tableau
        pile->elements = new_elements; // ajouter le nouveau
        pile->capacite *= 2;
    }

    // ajouter l'element sur la pile
    pile->elements[pile->occupation] = n;
    pile->occupation ++;
    return 0;
}

int depile_pile_amortie(pile_amortie *pile, int *e) {
    if (pile->occupation <= 0) {
        return -1;
    }
    *e = pile->elements[pile->occupation -1];
    pile->occupation -= 1;

    if (pile->capacite > 2 && pile->occupation*4 <= pile->capacite) {
        int* new_elements = realloc(pile->elements, pile->capacite/2);
        if (new_elements == NULL) {
            return -1;
        }
        pile->elements = new_elements;
        pile->capacite = pile->capacite/2;
    }
    return 0;
}

pile_amortie *copie_pile_amortie(pile_amortie *pile) {
    pile_amortie *copie = malloc(sizeof(pile_amortie));
    int *new_elements = malloc(pile->capacite * sizeof(int));
    if (copie == NULL || new_elements == NULL) {
        return NULL;
    }
    memmove(new_elements, pile->elements, pile->capacite * sizeof(int));
    copie->elements = new_elements;
    copie->capacite = pile->capacite;
    copie->occupation = pile->occupation;
    return copie;
}

int main() {
    printf("==== ALLOUER ====\n");
    pile_amortie *pile = alloue_pile_amortie();
    affiche_pile(pile);
    printf("==== EMPILER ====\n");
    for (int i = 0; i<10; i++) {
        empile_pile_amortie(pile, i);
        affiche_pile(pile);
    }
    int e;
    printf("==== DEPILER ====\n");
    for (int i = 0; i<10; i++) {
        depile_pile_amortie(pile, &e);
        printf("depile : %d\n", e);
        affiche_pile(pile);
    }

    printf("==== LIBERER ====\n OK\nQ");
    libere_pile_amortie(pile);
    return 0;
}
