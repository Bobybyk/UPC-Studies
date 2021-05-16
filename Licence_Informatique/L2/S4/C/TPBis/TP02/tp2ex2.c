#include<stdio.h>
#include<stdlib.h>
#include<time.h>

enum etat {
    VALIDEE, ENCOURS, EXPEDIEE
};
struct comm {
    int numeroC;
    int prix_exp;
    int prix_prod;
    enum etat etatC;
};

typedef struct comm commande;

commande comAlea(int num) {
    // cree une commande avec les champs init de facon random
    int prixExp = rand() % 20 +1;
    int prixProd = rand() % 2000 +1;
    int etatNum = rand() % 3 +1;
    enum etat e;
    if (etatNum == 1) {
        e = VALIDEE;
    } else if (etatNum == 2) {
        e = ENCOURS;
    } else {
        e = EXPEDIEE;
    }
    commande com = {.numeroC = num, .prix_exp = prixExp, .prix_prod = prixProd, .etatC = e};
    return com;
}

void afficheCommande(commande c) {
    // affiche une commande
    printf("Commande numero %d\n", c.numeroC);
    printf("Prix expedition : %d, prix production : %d\n", c.prix_exp, c.prix_prod);
    printf("Etat : ");
    switch(c.etatC) {
        case VALIDEE : printf("Validee\n\n"); break;
        case ENCOURS : printf("En cours\n\n"); break;
        case EXPEDIEE : printf("Expediee\n\n"); break;
    };
}

void afficheExpediee(int taille, commande T[]) {
    // affiche toutes les commandes expediees du tableau
    for (int i = 0; i<taille; i++) {
        if (T[i].etatC == EXPEDIEE) {
            afficheCommande(T[i]);
        }
    }
}

int nbCEC(int taille, commande T[]) {
    // renvoie le nombre de commandes en cours dans le tableau
    int enCours = 0;
    for (int i = 0; i<taille; i++) {
        if (T[i].etatC == ENCOURS) {
            enCours += 1;
        }
    }
    return enCours;
}

int coutVal(int taille, commande T[]) {
    // renvoie le cout total d'expedition pour les commandes VALIDEE
    int cout = 0;
    for (int i = 0; i<taille; i++) {
        if (T[i].etatC == VALIDEE) {
            cout += T[i].prix_exp;
        }
    }
    return cout;
}

int main() {
    srand(time(NULL));
    commande NBC[10];
    for (int i = 0; i<10; i++) {
        NBC[i] = comAlea(i);
        afficheCommande(NBC[i]);
    }
    //afficheExpediee(10, NBC);
    //printf("Nb de commandes en cours : %d\n", nbCEC(10, NBC));
    printf("Cout total d'expedition pour les commandes validees : %d\n", coutVal(10, NBC));
    return 1;
}
