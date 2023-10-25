#include "labs_parser.h"

labyrinth* parse_lab(char* filename) {
   unsigned int width;
   unsigned int height;

   // Read filename line by line
   FILE* fp = fopen(filename, "r");
   if (fp == NULL) {
      perror("Error opening file");
      return NULL;
   }

   // Read first line and parse width and height
   char* line = NULL;
   size_t len = 0;
   getline(&line, &len, fp);
   sscanf(line, "%d %d", &width, &height);
   labyrinth* l = malloc(sizeof(labyrinth));
   l->width = width;
   l->height = height;
   l->cells = malloc(sizeof(int*) * height);
   unsigned int i = 0;
   while (getline(&line, &len, fp) != -1) {
      l->cells[i] = malloc(sizeof(int) * width);
      if (strlen(line) != width + 1) return NULL;
      for (unsigned int j = 0; j < width; j++) {
         l->cells[i][j] = line[j] - '0';
      }
      i++;
   }
   if (i != height) return NULL;
   fclose(fp);
   return l;
}

void labyrinth_free(struct labyrinth *lab) {
	for(int i=0;i<lab->height;i++) {
		free(lab->cells[i]);
	}
	free(lab->cells);
	free(lab);
}

// bool is_valid(labyrinth *l) {
// TODO
// }

void debug_lab(labyrinth *l) {
   if (!l) {
      puts("Invalid labyrinth");
      return;
   }

   printf("Labyrinth %dx%d\n", l->width, l->height);
   printf("┌");
   for (int i = 0; i < 2*l->width; i++) {
      printf("─");
   }
   printf("┐\n");
   for (int i = 0; i < l->height; i++) {
      printf("│");
      for (int j = 0; j < l->width; j++) {
         if (l->cells[i][j])
            printf("██");
         else
            printf("  ");
      }
      printf("│\n");
   }
   printf("└");
   for (int i = 0; i < 2*l->width; i++) {
      printf("─");
   }
   printf("┘\n");
   printf("\n");
}
