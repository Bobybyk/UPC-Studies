#ifndef LABS_PARSER_H
#define LABS_PARSER_H

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdbool.h>
#include <fcntl.h>

#define BUFFER_SIZE 64

typedef struct labyrinth {
   u_int16_t width;
   u_int16_t height;
   int** cells;
} labyrinth;

labyrinth* parse_lab(char* filename);
void labyrinth_free(struct labyrinth *lab);

bool is_valid(labyrinth *l);

void debug_lab(labyrinth *l);

#endif
