#include<stdio.h>
#include<stdlib.h>
#include<time.h>


// declare the method
// this is not needed if you define the method before calling it in the main method
void stat(int, int);

int main(void) {
    // initialize the generated pseudo random number
    // only need to do it once before using the rand method
    srand(time(NULL));

    signed int a = rand();

    printf("Le nombre aleatoire %d est ", a);
    if (a%2 == 0) printf("pair.\n");
    else printf("impair.\n");

    printf("\n\n");

    printf("Methode stat avec m = 4 et n = 2.\n");
    printf("Doit afficher une erreur.\n");
    stat(4,2);

    printf("\n\n");

    printf("Methode stat avec m = 2 et n = 4.\n");
    printf("Les nombres doivent etre entre 0 et 57.\n");
    stat(24, 45);

    return 0;
}

/* Tire des nb aleatoires entre 0 et n+m/2 tant qu'ils sont inf ou egaux a n et les affiche.
 * Affiche le nb de nb tires, et les poucentages de nb entre 0 et m (strictement) et entre m et n (inclus tous les deux).
 */
void stat(int m, int n) {
    if (m > n) {
        printf("Error : %d est plus grand que %d.", m, n);
        return;
    }

    int k = n + m/2; // k est un int donc cast implicite : on a la partie entiere

    int nbTire = 0;
    int nbTireStrictementInfAm = 0;

    // on tire des nombres
    int a = rand() % k;
    while (a <= n) {
        printf("%d ", a);
        nbTire += 1;
        if (a < m) {
            nbTireStrictementInfAm += 1;
        }
        a = rand() % k;
    }
    printf("\n");

    printf("On a tire %d nombres.\n", nbTire);

    if (nbTire == 0) { return; } // pour eviter les divisions avec 0 dans le calcul des pourcentages


    // calcul et affichage des pourcentages
    int percentInf = nbTireStrictementInfAm * 100 / nbTire;
    int percentSup = 100 - percentInf;

    printf("%d pourcents inf a %d, %d pourcents sup ou egal a %d.\n", percentInf, m, percentSup, m);

}

