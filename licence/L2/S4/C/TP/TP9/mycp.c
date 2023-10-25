#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

int copie(FILE*, FILE*);

int main(int argc, char const *argv[]) {

	FILE *src = fopen(argv[1],"r");
	assert(src != NULL);

	FILE *dst = fopen(argv[2],"w");
	assert(dst != NULL);

	copie(src,dst);

	fclose(src);
	fclose(dst);
	
	return 0;
}

int copie(FILE *fsrc, FILE *fdst) {

	while((int c = fgetc(fsrc)) != EOF) {
		int ret = fputc(c,fdst);
		if(ret == EOF)
			return -1;
	}
	return 0;
}