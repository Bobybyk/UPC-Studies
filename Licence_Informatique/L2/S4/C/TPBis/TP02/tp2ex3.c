#include<stdio.h>
#include<assert.h>
#include<math.h>

#define NBC 7

struct frac {
    long int num;
    long int den;
};
typedef struct frac fraction;

fraction build(long int n, long int d) {
    // renvoie une nouvelle fraction
    assert(d != 0); // si d == 0, programme termine et affiche une erreur
    fraction f = {.num = n, .den = d};
    return f;
}

int eq(fraction f, fraction g) {
    // renvoie 1 si les fractions sont egales, 0 sinon
    return f.num * g.den == f.den * g.num;
}

int isInteger(fraction f) {
    // renvoie 1 si la fraction est un entier, 0 sinon
    return f.num % f.den == 0;
}

fraction sum(fraction f, fraction g) {
    // renvoie la somme des deux fractions
    int n = f.num * g.den + f.den * g.num;
    int d = f.den * g.den;
    fraction somme = {.num = n, .den = d};
    return somme;
}

fraction sub(fraction f, fraction g) {
    // renvoie la substraction des deux fractions
    int n = f.num * g.den - f.den * g.num;
    int d = f.den * g.den;
    fraction somme = {.num = n, .den = d};
    return somme;
}

fraction mul(fraction f, fraction g) {
    // renvoie la multiplicatio des fractions
    fraction multi = {.num = f.num*g.num, .den = f.den*g.den};
    return multi;
}

long pgcd(long a, long b) {
    // renvoie le pgcd de a et de b
    int x = a;
    int y = b;
    while(y != 0) {
        int r = x % y;
        x = y;
        y = r;
    }
    return x;
}

fraction reduce(fraction f) {
    // renvoie la fraction sous sa forme irreductible
    int p = pgcd(f.num, f.den);
    fraction reduit = {.num = f.num/p, .den = f.den/p};
    if (reduit.den < 0) {
        reduit.den *= -1;
        reduit.num *= -1;
    }
    return reduit;
}

struct point {
    fraction x;
    fraction y;
};
typedef struct point point;

point build_point(fraction a, fraction b) {
    point p = {.x = a, .y = b};
    return p;
}

int eqp(point p1, point p2) {
    // renvoie 1 si les coordonnees des points sont egales, 0 sinon
    return eq(p1.x, p2.x) && eq(p1.y, p2.y);
}

double dist(point p1, point p2) {
    fraction y = sub(p2.y, p1.y);
    fraction x = sub(p2.x, p1.x);
    double a = y.num / y.den;
    double b = x.num / x.den;
    return sqrt(sqrt(a) + sqrt(b));
}

int main() {
    //build(1, 0); // to test the error

    fraction ex_fraction[NBC];
    ex_fraction[0] = build(1, 1);
    ex_fraction[1] = build(1, 2);
    ex_fraction[2] = build(2, 4);
    ex_fraction[3] = build(-9 ,3);
    ex_fraction[4] = build(8, -20);
    ex_fraction[5] = build(-5, -1);
    ex_fraction[6] = build(1, -3);


    printf("1/1 egal 2/4 ? : %d\n", eq(ex_fraction[0], ex_fraction[2]));
    printf("1/2 egal 2/4 ? : %d\n", eq(ex_fraction[1], ex_fraction[2]));

    printf("1/1 entier ? : %d\n", isInteger(ex_fraction[0]));
    printf("2/4 entier ? : %d\n", isInteger(ex_fraction[2]));
    printf("-5/-1 entier ? : %d\n", isInteger(ex_fraction[5]));

    fraction somme = sum(ex_fraction[1], ex_fraction[2]);
    printf("Somme de 1/2 et 2/4 = %ld/%ld\n", somme.num, somme.den); // %ld to format a long int
    fraction subt = sub(ex_fraction[1], ex_fraction[6]);
    printf("Soustraction de 2/4 - 1/-3 = %ld/%ld\n", subt.num, subt.den);
    fraction multi = mul(ex_fraction[4], ex_fraction[6]);
    printf("Multiplication de 8/-20 par 1/-3 = %ld/%ld\n", multi.num, multi.den);

    printf("Test de la reduction de fractions : \n");
    fraction fractions_reduites[NBC];
    for (int i = 0; i<NBC; i++) {
        fractions_reduites[i] = reduce(ex_fraction[i]);
        printf("%ld/%ld => %ld/%ld\n", ex_fraction[i].num, ex_fraction[i].den,
                                    fractions_reduites[i].num, fractions_reduites[i].den);
    }

    printf("\n\n");

    point points[NBC];
    points[0] = build_point(ex_fraction[1], ex_fraction[2]);
    points[1] = build_point(ex_fraction[2], ex_fraction[1]);
    points[2] = build_point(ex_fraction[3], ex_fraction[4]);
    points[3] = build_point(ex_fraction[4], ex_fraction[5]);
    points[4] = build_point(ex_fraction[5], ex_fraction[6]);
    points[5] = build_point(ex_fraction[1], ex_fraction[6]);
    points[6] = build_point(ex_fraction[1], ex_fraction[4]);
    
    printf("test eq points : \n");
    printf("OUI : %d\n", eqp(points[0], points[0]));
    printf("OUI : %d\n", eqp(points[0], points[1]));
    printf("NON : %d\n", eqp(points[2], points[3]));

    return 1;
}
