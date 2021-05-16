#include<stdio.h>

void affiche_tab(int t[], int n) {
    for (int i = 0; i<n; i++) {
        printf("%d, ", t[i]);
    }
    printf("\n");
}

void swap(int* pa, int* pb) {
    int tmp = *pa;
    *pa = *pb;
    *pb = tmp;
}

void sort(int t[], int start, int end) {
    // trie un tableau d'entiers entre start(inclus) et end(exclus) avec la methode du tri a bulles
    if (start == end) return;
    for (int i = start; i<end-1; i++) {
        if (t[i] > t[i+1]) {
            swap(&t[i], &t[i+1]);
        }
    }
    sort(t, start, end-1);
}

void sort_point(int *start, int *end) {
    // trie un tableau d'entiers entre start(inclus) et end(exclus) avec la methode du tri a bulles
    if (start == end) return;
    int *tmp = start;
    while(tmp != end) {
        if (*tmp > *(tmp+1)) {
            swap(tmp, (tmp+1));
        }
        tmp += 1;
    }
    sort_point(start, end-1);
}

int main(void) {
    int t[] = {6,5,4,3,2,1};
    sort(t, 0, 6);
    affiche_tab(t, 6);
    int t2[] = {5,4,6,7,83,23,45,2,12,65,765};
    sort(t2, 0, 11);
    affiche_tab(t2, 11);


    int t3[] = {6,5,4,3,2,1};
    sort_point(&t3[0], &t3[4]);
    affiche_tab(t3, 5);
    int t4[] = {5,4,6,7,83,23,45,2,12,65,765};
    sort_point(&t4[0], &t4[10]);
    affiche_tab(t4, 11);

    printf("\n");
    printf("trier entre deux points du tableau seulement\n");
    int tab[] = {3,8,1,50,3,9,0,4,5,6,-7,9};
    affiche_tab(tab, 12);
    sort_point(&tab[3],&tab[10]);
    affiche_tab(tab, 12);
    return 0;
}
