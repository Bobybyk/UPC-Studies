#include "MultiEnsemble.h"
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

struct node{
	int val;
	unsigned num;
	struct node *next;
};

mset new_node(int val, unsigned num) {
	node *n = malloc(sizeof(node));
	assert(n != NULL);
	n->val = val;
	n->num = num;
	n->next = NULL;
	return n;
}

mset add_val(int val, unsigned num, mset m) {
	
	if (m == NULL) {
		return new_node(val, num);
	}
	if (val == m->val) {
		m->num+=num;
		return m;
	}
	if (m->val > val) {
		mset newNode = new_node(val, num);
		newNode->next = m;
		return newNode;
	} 
}

mset build(int *values, size_t size) {
	node *n
	for (int i = 0 ; i<size ; i++) {
		add_val(values[i], size, n);
	}
	return n;
}

void print_mset(mset m, short verbose) {
	int cmp = 1;
	if (verbose == NULL) { 
		for (int i = 0 ; i<m->num ; i++) {
			if (m->next->val == m->val) {
				cmp+=1;
			} else {
				printf("%s", m->val, "(", cmp, ") ");
				cmp = 1;
			}
		}
	} else {
		for (int i = 0 ; i<m->num ; i++) {
			printf("%s\n", m->val, " ");
		}
	}
}

int main() {
	int* vals = malloc(sizeof(int)*6);
	vals[0] = 5;
	vals[1] = 4;
	vals[2] = 7;
	vals[3] = 11;
	vals[4] = 1;
	vals[5] = 2;
	short v;
	print_mset(build(vals, 6), v);
	return 0;
}