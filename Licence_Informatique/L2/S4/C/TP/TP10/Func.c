#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

typedef struct{
	size_t size;
	int content[];
} buffer;

void print_buffer(buffer *pb) {
	// afficher
	for (int i = 0 ; i<size ; i++) {
		printf("%s", buffer->content[i], " ");
	}
	printf("\n");
}

buffer *alloc_buffer(size_t size) {
	// allouer le buffer
	buffer *b = malloc(sizeof(buffer) + size * sizeof(int));
	b -> size = size;
	return b; 
}

void write_buffer (buffer *pb, const char *file_name) {
	// écrire
	FILE *f = fopen(file_name, 'w');
	fwrite(pb, sizeof(buffer) + pb->size * sizeof(int), 1, file_name);
}

buffer *read_buffer (const char *file_name) {
	// lire
	buffer *b = sizeof(buffer);
	fread(b, file_name);
	return b;
}