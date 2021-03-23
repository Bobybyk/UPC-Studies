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
	array *t = malloc(sizeof(array));
	t->ptr = malloc(sizeof(int) * n);
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

int array_insert(array *t, int index, int valeur) {
	array *tmp = malloc(sizeof(array));
	assert(tmp != NULL);
	tmp->ptr = malloc(sizeof(int) * (t->size + 1));
	assert(tmp->ptr != NULL);
	for (int i = 0 ; i < tmp->size ; i++) {
		if (i == index) {
			tmp->ptr[i] = valeur;
			printf("Hello\n");
		}
		else {
			tmp->ptr[i] = t->ptr[i];
		}	
		printf("OK\n");
	}
	t = realloc(tmp->ptr, sizeof(int) * (t->size+1));
	t->ptr = tmp->ptr;
	t->size = tmp->size;
	free(tmp);
	return 1;
}

int main() {
	array *t = malloc(sizeof(array));
	int tab[10] = {0,1,2,3,4,5,6,7,8,9};
	t->ptr = tab;
	t->size = 10;

	//array_print(t); 
	printf("------\n");
	//array_print(array_init(5));
	array_print(t);
	array_insert(t, 2, 18);
	array_print(t);
	return 0;
}