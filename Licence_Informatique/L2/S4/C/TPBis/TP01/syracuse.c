#include<stdio.h>

void syracuse(int);
void testCollatz(int);

int main(void) {
    syracuse(27);
    printf("\n\n");
    syracuse(32);
    printf("\n\n");

    testCollatz(20);
    return 0;
}

void syracuse(int m) {
    int depart = m;
    int tpsVol = 0;
    while (m != 1) {
        //printf("%d \n", m);
        tpsVol += 1;
        if (m%2 == 0) {
            m /= 2;
        } else {
            m = (3 * m) + 1;
        }
    }
    printf("%d : %d\n", depart, tpsVol);
}

void testCollatz(int n) {
    for (int i = 1; i<n+1; i++) {
        syracuse(i);
    }
}
