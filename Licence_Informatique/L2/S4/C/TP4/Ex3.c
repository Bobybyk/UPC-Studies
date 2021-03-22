#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

typedef struct array {
	int *ptr;
	int size;
} array;

void array_print(array *t) {
	for (int i=*(t->ptr) ; i<(t->size) ; i++) {
		printf("%d\n", t->ptr[i]);
	}
	printf("\n");
}

array *array_init(int n) {
	array *t = malloc(sizeof(int) * (n+1));

	return t;
}

void array_destroy(array *t) {
	free(t);
}

int array_get(array *t, int index) {
	assert(index < t->ptr[index]);
	return t->ptr[index];
}

int array_set(array *t, int index, int valeur) {
	assert(index < (t->ptr[index]) );
	t->ptr[index] = valeur;
}

int main() {
	array *t = malloc(sizeof(array));
	int tab[10] = {0,1,2,3,4,5,6,7,8,9};
	t->ptr = tab;
	t->size = 10;

	array_print(t); 
	printf("------");
	array_print(array_init(5));
	return 0;
}