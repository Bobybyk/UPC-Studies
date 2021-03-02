#include<stdio.h>
#include<stdlib.h>

void affiche (int t[], int a, int b) {
	for (int i = a; i < b - 1; i++) {
		printf ("%d, ", t[i]);
	}
	printf("%d\n", t[b - 1]);
}

int *build_tab (int a, int n) {
	int *t;
	t = malloc (n * sizeof (int));
	for (int i = 0; i < n; i++) {
		t[i] = a + i;
	}
	printf  ("t : %p : ", t);// TEST
	affiche (t, 0, 15);// TEST
	return t;// WARNING!
}

int main (void) {
	printf ("\nbuild tab\n");
	int *tab = build_tab (0, 15);
	printf ("tab : %p\n", tab);
	//affiche (tab, 0, 15);    
	// SEG_FAULT!6return 0;7
} 