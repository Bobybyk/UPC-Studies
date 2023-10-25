#include<stdio.h>
#include <stdlib.h> // pour rand
#include <time.h> // pour time

#define NBC 5

enum etat {VALIDEE, ENCOURS, EXPEDIEE} ;

struct comm {
	int numeroC;
	int prix_exp;
	int prix_prod;
	enum etat etatC;
};

typedef struct comm commande;

commande comAlea(int num) {
    srand(time(NULL));
    commande com = {
        .numeroC = num,
        .prix_exp = (rand() % 20) + 1,
        .prix_prod = (rand() % 2000) + 1,
        .etatC = (rand() % 2)
    };
    return com;
}

void affCommande(commande c) {
	printf("Numéro de commande : %d\n", c.numeroC);
	printf("Prix d'expédition : %d\n", c.prix_exp);
	printf("Prix de prodution : %d\n", c.prix_prod);
	switch(c.etatC) {
		case 0: printf("VALIDEE\n"); break;
		case 1: printf("ENCOURS\n"); break;
		case 2: printf("EXPEDIEE\n"); break;
	}
}

int main() {
	commande tab[NBC];
	for (int i = 0 ; i < NBC ; i++) {
		tab[i] = comAlea(i);
	}
	affCommande(tab[1]);
	affCommande(tab[2]);
	return 0;
}