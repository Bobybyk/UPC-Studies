#include<stdio.h>
#include<stdlib.h>

int main(int argc, char **argv) {
    if (argc != 3) {
        printf("WRONG SYNTAX : ./fill.c N nameOfFile \n");
        return 1; // pb : il doit y avoir 2 args (nom du prgm et nomdu fichier)
    }
    FILE *flot = fopen(argv[2], "a+");
    int n =atoi(argv[1]);
    char *str = "Hello !";
    for (int i = 0; i<n; i++) {
        fputs(str, flot);
    }
    fclose(flot);

    return 0;
}
