#include<stdio.h>
#include<stdlib.h>


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

int main() {
	array *a = {.ptr = pt, .size = 1};
	a* = malloc(1*sizeof(int));
	a->ptr
	array_print(a);
}