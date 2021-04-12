#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

int main(int argc, char const *argv[]) {
	FILE *f = fopen(argv[1],"w");
	assert(f != NULL);
	for(int i=0;i<atoi(argv[2]);i++)
		fputs("une ligne\n",f);
	return 0;
}