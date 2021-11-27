#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include<string.h>

#define affiche_nbr(a) printf("%p (%lu)\n", a, (unsigned long) a)

char charA = 'a';
char charB = 'b';

int int1 = 1;
int int2 = 2;

int *p1 = &int1;
int *p2 = &int2;

void f1(int *p) {
    int int5 = 7;
    char charE = 'A';

    printf("valeur a l'adresse p : %d\n", *p);
    printf("valeur de l'adresse p : %p\n", p);
    printf("valeur de l'int : %d\n", int5);
    printf("adresse de l'int : %p\n", &int5);
    printf("valeur du char : %c\n", charE);
    printf("adresse du char : %p\n", &charE);
}

int main() {
    char charC = 'c';
    char charD = 'd';
    int int3 = 3;
    int int4 = 4;
    int *p3 = &int3;
    int *p4 = &int4;

    printf("==== Affichage de adresses des char ====\n");
    printf("%p\n", &charA);
    printf("%p\n", &charB);
    printf("%p\n", &charC);
    printf("%p\n", &charD);
    printf("==== Affichage des adresses des int ====\n");
    printf("%p\n", &int1);
    printf("%p\n", &int2);
    printf("%p\n", &int3);
    printf("%p\n", &int4);
    printf("==== Affichage des adresses des pointeurs ====\n");
    printf("%p\n", &p1);
    printf("%p\n", &p2);
    printf("%p\n", &p3);
    printf("%p\n", &p4);
    printf("==== Affichage de sizeof(int) ====\n");
    printf("\%zu\n", sizeof(int));

    printf("==== f1 ====");
    f1(p3);
    return 0;
}

