#include<stdio.h>

int g(int);
int f(int);


int g(int n) {
    if (n == 1) {
        return 1;
    } else {
        return 3 * f(n/2);
    }
}

int f(int n) {
    if (n ==1) {
        return 2;
    } else {
        return 2 * g(n-1);
    }
}

int main() {
    printf("f(20) = %d\n", f(20));
    return 1;
}
