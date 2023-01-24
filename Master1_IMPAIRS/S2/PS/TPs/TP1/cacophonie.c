#include <stdio.h>

int cacophonie() {
    char bruit[8];
    int f = fork();

    if (f == 0) {
        for (int i = 0 ; i < 1000 ; i++) {
            sprintf(bruit, "ouaf!");
        }
    }
    else if (f > 0) {
        for (int i = 0 ; i < 1000 ; i++) {
            sprintf(bruit, "miaou!");
        }
    } else {
        printf("%s", "error");
    }
}

int main() {
    int p2 = fork();
    printf("p2 : %d", getpid(), ", ");
    pause();
    exit(0);
    if (p2 == 0) {
        int p3 = fork();
        printf("p3 : %d", getpid(), ", ");
        pause();
        exit(0);
        if (p3 == 0) {
            int p7 = fork();
            printf("p7 %d: ", getpid(), ", ");
            pause();
            exit(0);
        }
        else if (p3 > 0) {
            int p4 = fork();
            printf("p4 : %d", getpid(), ", ");
            pause();
            exit(0);
            if (p4 == 0) {
                int p6 = fork();
                printf("p6 : %d", getpid(), ", ");
                pause();
                exit(0);
            }
            else if (p4 > 0) {
                int p5 = fork();
                printf("p5 : %d", getpid(), ", ");
                pause();
                exit(0);
            }
        }
    } 
    else if (p2 > 0) {
        int p7 = fork();
        printf("p7 : %d", getpid(), ", ");
        pause();
        exit(0);
    }
    else {
        printf("%s", "error");
    }
}