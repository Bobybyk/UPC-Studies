#include "Func.h";

fifo create_fifo( size_t capacite_init, size_t taille_elem) {
	fifo f = malloc(sizeof(struct  file));
	assert(f != NULL);
	f->first = malloc(capacite_init * taille_elem);
	assert (first != NULL);
	f->occupe = f->first;
	f->libre = f->first;
	f->last = decale(f->first, capacite_init * taille_elem);
	return f;
}

void delete_fifo(fifo f) {
	free(f->first);
	free(f);
}

int empty_fifo(fifo f) {
	if (f->first == NULL) {
		return 1;
	} else {
		return 0;
	}
}

void *get_fifo(fifo f, void *element) {
	memmove(element, f->first, sizeof(f));
	f->occupe-=1;
}