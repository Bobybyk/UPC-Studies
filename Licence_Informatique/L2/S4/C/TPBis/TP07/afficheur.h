#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

typedef struct node node;
struct node {
  int val;
  node *left;
  node *right;
};

void print_head (int depth, int addr) {
    if (depth > 1) {
	int pre = addr / 2;
	print_head (depth - 1, pre);
	printf ("%s", (pre % 2) != (addr % 2) ? "|    " : "     ");
	return;
    }
    if (depth == 1)
	printf ("     ");
}

void pretty_rec (node *t, int depth, int addr) {
    if (t == NULL) {
	print_head (depth, addr);
	printf ("|----N\n");
	return;
    }
    pretty_rec (t -> right, depth + 1, 2 * addr  + 1);
    print_head (depth, addr);
    char c =
	(depth == 0) ? '-' : '|';
    printf ("%c----%d\n", c, t -> val);
    pretty_rec (t -> left, depth + 1, 2 * addr);
}

// fonction principale d'affichage.
void pretty_print (node *t) {
    pretty_rec (t, 0, 0);
}
