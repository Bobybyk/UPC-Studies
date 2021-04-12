#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

typedef struct{
	size_t size;
	int content[];
} buffer;

void print_buffer(buffer *pb) {
	for (int i = 0 ; i<size ; i++) {
		printf("%s", buffer->content[i], " ");
	}
	printf("\n");
}

buffer *alloc_buffer(size_t size) {
	buffer *b = malloc(sizeof(buffer) + size * sizeof(int));
	b -> size = size;
	return b; 
}

void write_buffer (buffer *pb, const char *file_name) {
	FILE *f = fopen(file_name, 'w');
	fwrite(pb, sizeof(buffer) + pb->size * sizeof(int), 1, file_name);
}

buffer *read_buffer (const char *file_name) {
	buffer *b = sizeof(buffer);
	fread(b, file_name);
	return b;
}