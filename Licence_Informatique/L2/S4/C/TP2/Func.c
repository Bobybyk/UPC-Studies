#include<stdio.h>

int f(int);
int g(int); 

int main(void) {
	printf("%d\n", f(20));
	return 0;	
}

int f(int n) {
	if (n == 1) {
		return 2;
	}
	return 2*g(n-1);
}

int g(int n) {
	if (n == 1) {
		return 1;
	}
	return 3*f(n/2);
}