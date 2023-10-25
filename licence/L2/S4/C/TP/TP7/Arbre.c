#include <stdlib.h>
#include <assert.h>
#include <stddef.h>
#include <stdio.h>

#include "affichage.h"


node *cons_tree(int val, node *left, node *right) {
	node *n = malloc(sizeof(node));
	assert(n);
	n->left = left;
	n->right = right;
	n->val = val;
}

void free_tree(node *t) {
	if (t != NULL) {
		free_tree(t->left);
		free_tree(t->right);
		free(t);
	}

}

int size_tree(node *t) {
	if (t == NULL) {
		return 0;
	}
	return size_tree(t->left) + size_tree(t->right) + 1;
}

int sum_tree(node *t) {
	if (t == NULL) {
		return 0;
	}
	return sum_tree(t->left) + size_tree(t->right) + t->val;
}

int depth_tree(node *t) {
	if (t == NULL) {
		return 0;
	}
	return size_tree(t->left) > size_tree(t->right) ? size_tree(t->left) + 1 : size_tree(t->right) + 1; 
}

void print_abr(node *t) {
	if (t == NULL) {
		printf("arbre vide\n");
	}
	if (t->left != NULL) {
		print_abr(t->left);
	}
	printf("%s\n", t->val);
	if (t->right != NULL) {
		print_abr(t->right);
	}
		
}

node *insert_abr(node *t, int val) {
	if (t == NULL) {
		return cons_tree(val, NULL, NULL);
	}
	if (val < t->val) {
		t->left = insert_abr(t->left, val);
	}
	if (val > t->val) {
		t->right = insert_abr(t->right, val);
	}
	return t;
}

node *search_abr(node *t, int val) {
	
}

int main() {
	node *t;
	t = cons_tree (1, cons_tree (3, NULL, NULL), cons_tree (6, cons_tree (4, NULL, NULL), NULL));
	return 0;
}