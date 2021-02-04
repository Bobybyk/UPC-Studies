#include <stdio.h> // printf
#include <stdlib.h> // pour rand
#include <time.h> // pour time

#define N 26 // [0 , 20[ : intervalle du tirage au sort
#define M 8 // taille du tableau
char alphabet[26] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};


// création d'un tableau de tailel M contenant des nombres aléatoires
int* creaTab() {
	srand(time(NULL));
	static int tab[M];
	for (int i = 0 ; i<M ; i++) {
		tab[i] = rand()%N;
	}
	return tab;
}


// affichage d'un tableau
void printTab(int* tab) {
	for (int i = 0 ; i<M ; i++) {
		printf("tableau aléatoire : %d", tab[i]);
		printf("\n");
	}
}

void printTabChar(char* tab) {
	for (int i = 0 ; i<M ; i++) {
		printf("tableau aléatoire : %d", tab[i]);
		printf("\n");
	}
}

// sélection du plus grand entier d'un tableau
int maxTab(int* tab) {
	int max = 0;
	for (int i = 0 ; i < M ; i++) {
		if (max < tab[i]) { 
			max = tab[i];
		}
	}
	return max;
}

char* creatTabChar() {
	srand(time(NULL));
	static char tab[M];

	for (int i = 0 ; i<M ; i++) {
		tab[i] = alphabet[rand()%N];
	}
	return tab;
}

int main() {
	int* tabInt = creaTab();
	printTab(tabInt);
	//printf("\nmax : %d", maxTab(tabInt));

	printf("\n");
	char* tabChar = creatTabChar();
	printTabChar(tabChar);
	return 0;
}