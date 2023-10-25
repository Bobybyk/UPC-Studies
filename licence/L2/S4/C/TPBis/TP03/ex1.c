#include<stdio.h>

void swap(int* pa, int* pb) {
    int tmp = *pa;
    *pa = *pb;
    *pb = tmp;
}

typedef struct {
    int x;
    int y;
} point;



int main(void) {
    int x,y;
    x=5;
    y=6;
    printf("Valeur de x avant échange : %d\n",x);
    printf("Valeur de y avant échange : %d\n",y);
    swap(&x,&y);
    printf("Valeur de x après échange : %d\n",x);
    printf("Valeur de y après échange : %d\n",y);

    printf("\n");

    int t[] = {1, 2, 3, 4};
    printf("Valeur de t[0] avant échange : %d\n", t[0]);
    printf("Valeur de t[3] avant échange : %d\n", t[3]);
    swap(&t[0], &t[3]);
    printf("Valeur de t[0] après échange : %d\n", t[0]);
    printf("Valeur de t[3] après échange : %d\n", t[3]);

    printf("\n");

    point p = {.x = 1, .y = 2};
    printf("Valeur de x avant échange : %d\n",p.x);
    printf("Valeur de y avant échange : %d\n",p.y);
    swap(&p.x,&p.y);
    printf("Valeur de x après échange : %d\n",p.x);
    printf("Valeur de y après échange : %d\n",p.y);


    return 0;
}


