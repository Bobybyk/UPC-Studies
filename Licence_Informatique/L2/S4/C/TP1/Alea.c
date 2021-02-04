#include <stdio.h> // printf
#include <stdlib.h> // pour rand
#include <time.h> // pour time

int main() {
	srand(time(NULL));
	int x = rand();
	if (x%2 == 0) {
		printf("x est pair : %d\n", x);
	}
	if (x%2 == 1) {
		printf("x est impair : %d\n", x);
	}
	return 0;
}

int stat() {

}