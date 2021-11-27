// Nom : Le Franc
// Prénom : Matthieu
// N°Etudiant : 71800858

#include<stdio.h>

#define DEG 6
typedef struct{
int degre;
int coef[DEG];
}poly;

void print_monome(int coeff, int degre){
    printf("%+dx^%d", coeff, degre );
}

void print_poly(poly p) {
	for (int i = 0 ; i < DEG ; i++) {
        if (p.coef[i] != 0) {
            print_monome(p.coef[i], i);
        }	
	}  
    printf("\n");  
}

poly add(poly p, poly q) {
    poly tmp = { .degre = 4, .coef = {0, 0, 0, 0, 0, 0}};
    for (int i = 0 ; i < DEG ; i++) {
        tmp.coef[i] = p.coef[i]+q.coef[i];
    }
    return tmp;
}

int main() {
    poly p = {.degre = 4, .coef = {2, 0, -3, 0, 7, 0}};
    poly q;
    q.degre = 4;
    q.coef[0] = 3;
    q.coef[1] = 1;
    q.coef[2] = 0;
    q.coef[3] = -6;
    q.coef[4] = -7;
    q.coef[5] = 0;
    print_poly(p);
    print_poly(q);
    print_poly(add(p, q));
    return 0;
}