#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

int main(int argc, char const *argv[]) {

	int k = atoi(getenv("LIG"));

	for(int i=1;i<argc;i++) {
		FILE *f = fopen(argv[i],"r");
		assert(f != NULL);
		int c = fgetc(f);
		while(c != EOF) {
			printf("%c",c);
			c = fgetc(f);
		}
		fclose(f);
	}

	return 0;
}