#include <stdlib.h>

typedef struct element element;
struct element {
int val;
element *next;
element *previous;
};

element *cons_list() {
	return malloc(sizeof(element));
}	

void add_fst_list(element * L,int v) {
	int tmp;
	tmp = L->next->val;
	L->next->val = v;
	L->next->previous->val  = L->val;
	L->next->next->val = tmp;
}

void add_lst_list(element * L,int v) {
	int tmp;
	tmp = L->previous->val;
	L->previous->val = val;
	L->previous->next->val = L->val;
	L->previous->previous->val = tmp;
}

int isempty_list(element * L) {
	if (L->next == L) {
		return 1;
	} 
	return 0;
}

int del_fst_list(element * L) {
	if (L != NULL) {
		
	}
}

int main() {
	return 0;
}