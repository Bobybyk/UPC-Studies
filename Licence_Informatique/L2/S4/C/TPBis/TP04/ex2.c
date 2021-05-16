#include<stdio.h>
#include<stdlib.h>

void affiche (int t[], int a, int b) {
    for (int i = a; i < b - 1; i++) {
        printf ("%d, ", t[i]);
    }
    printf("%d\n", t[b - 1]);
}

int build_tab (int a, int n) {
    int *t;
    t = malloc (n * sizeof (int));
    if (t == null) {
        return -1;
    }
    for (int i = 0; i < n; i++) {
        t[i] = a + i;
    }
    printf ("t : %p : ", t);
    affiche (t, 0, 15);
    return 0;
}

int main () {
    printf("==Question 1==\n");
    printf("Pas de pb de compilation, mais pb a l'execution car on sort des bornes du tableau\n");
    int t[] = {1, 2, 3, 4};
    affiche (t, 0, 4);
    affiche (t, 0, 6);
    affiche (t, -2, 4);


    printf("\n==Question 2==\n");
    printf ("\nbuild tab\n");
    int *tab = build_tab (0, 15);
    printf ("tab : %p\n", tab);
    affiche (tab, 0, 15);

    return 0;
}
