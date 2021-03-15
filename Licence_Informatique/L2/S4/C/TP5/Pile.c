#include<stdlib.h>
#include <assert.h>
#include <stdio.h>

typedef struct pile_amortie {
	int occupation;
	int capacite;
	int *elements;
} pile_amortie;

pile_amortie *alloue_pile_amortie() {
	pile_amortie *p = malloc(sizeof(pile_amortie));
	if (p == NULL) {
		return NULL;
	}
	p->occupation = 1;
	p->capacite = 1;
	p->elements = malloc(sizeof(int) * p->capacite);
	return p;
}

void libere_pile_amortie(pile_amortie *pile) {
	free(pile->elements);
	free(pile);
}

int empile_pile_amortie(pile_amortie *pile, int n) {
	if (pile->occupation == pile->capacite) {
		int * new_elements = realloc(pile->elements, 2*(pile->capacite*sizeof(int)));
		if (new_elements == NULL) {
			return -1;
		}
		pile->elements = new_elements;
		pile->capacite *= 2;
	}
	pile->elements[pile->occupation] = n;
	pile->occupation += 1;
	return 0;
}

int depile_pile_amortie(pile_amortie *pile, int*e) {
	if (pile->occupation )
}

int main() {
	pile_amortie *p = alloue_pile_amortie();
	printf("%s\n", empile_pile_amortie(p, 5));
	return 0;
}