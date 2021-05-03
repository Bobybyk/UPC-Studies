#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

typedef struct{
int x,y;
}paire;

int main() {
	int a ;
	void *pt = &a;
	*((int *)pt) = 42;
	*((int *)pt) = ((*((int *)pt)) * (*((int *)pt)));
	printf("%s\n", a);

	paire b;
	pt = &b;

	((paire *)pt)->y+=1;
	return 0;
}