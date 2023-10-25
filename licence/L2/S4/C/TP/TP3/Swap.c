#include<stdio.h>

void swap(int* pa, int* pb) {
    printf("pa : %p\n", pa);
    printf("pb : %p\n", pb);
    int *tmp = pa;
    pa = pb;
    pb = tmp;
    printf("pa : %p\n", pa);
    printf("pb : %p\n", pb);
}

void minmax(int n, int t[], int *pmin, int *pmax) {
    for (int i = 0 ; i < n ; i++) {
        if (t[i] < *pmin) {
            *pmin = i;
        }
        if (t[i] > *pmax) {
            *pmax = t[i];
        }
    }
    printf("pmin : %d\n",*pmin);
    printf("pmax : %d\n",*pmax);
}

void occurences(int n, int t[], int e, int *pocc, int *first) {
    
}

int main() {
    int x,y;
    x=5;
    y=6;
    printf("------------------------\n");
    printf("Valeur de x avant échange : %d\n",x);
    printf("Valeur de y avant échange : %d\n",y);
    swap(&x,&y);
    printf("Valeur de x après échange : %d\n",x);
    printf("Valeur de y après échange : %d\n",y);
    printf("------------------------\n");
    int tab[] = {1, 2};
    printf("Valeur de tab[0] avant échange : %d\n",tab[0]);
    printf("Valeur de tab[1] avant échange : %d\n",tab[1]);
    swap(&tab[0],&tab[1]);
    printf("Valeur de tab[0] après échange : %d\n",tab[0]);
    printf("Valeur de tab[1] après échange : %d\n",tab[1]);
    printf("------------------------\n");
    typedef struct{
        int a;
        int b;
    } point;
    point p={.a=5, .b=20};
    printf("Valeur de a avant échange : %d\n",p.a);
    printf("Valeur de b avant échange : %d\n",p.b);
    swap(&p.a,&p.b);
    printf("Valeur de a après échange : %d\n",p.a);
    printf("Valeur de b après échange : %d\n",p.b);
    printf("------------------------\n");
    int tabTri[] = {5, 18, 1, 7, 9, 10};
    int c, d = tabTri[0];
    minmax(6, tabTri, &c, &d);
    return 0;
}