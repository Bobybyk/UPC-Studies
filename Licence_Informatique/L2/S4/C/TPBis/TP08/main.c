#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

typedef struct element element;
struct element {
    int val;
    element *previous;
    element *next;
};

element *const_list() {
    element *e = malloc(sizeof(element));
    assert(e != NULL);
    return e;
}

int isempty_list(element *L) {
    return L->next == NULL || L->previous == NULL;
}

void add_first_list(element *L, int v) {
    element *e = malloc(sizeof(element));
    assert(e != NULL);

    e->val = v;

    if (L->next == NULL) {
        e->next = L;
        e->previous = L;
        L->next = e;
        L->previous = e;
    } else {
        e->next = L->next;
        e->next->previous = e;
        e->previous = L;
        e->previous->next = e;
    }
}

void add_last_list(element *L, int v) {
    if (isempty_list(L)) {
        add_first_list(L, v);
        return;
    }
    element *e = malloc(sizeof(element));
    assert(e != NULL);

    e->val = v;
    element *tmp = L->next;
    while (tmp->next != L) {
        tmp = tmp->next;
    }

    e->previous = tmp;
    e->previous->next = e;
    e->next = L;
    e->next->previous = e;
}


int len_list(element *L) {
    if (isempty_list(L)) return 0;
    element *tmp = L->next;
    int len = 0;
    while(tmp != L) {
        len += 1;
        tmp = tmp->next;
    }
    return len;
}

void print_list(element *L) {
    if (isempty_list(L)) {
        printf("list vide");
        return;
    }
    element *tmp = L->next;
    while(tmp != L) {
        printf("%d ", tmp->val);
        tmp = tmp->next;
    }
    printf("\n");
}

int del_first_list(element *L) {
    if (isempty_list(L)) {
        printf("Erreur : list vide\n");
        return 0;
    }

    element *e = L->next;
    int v = e->val;

    if (L->next->next != L) {
        L->next = L->next->next;
        L->next->previous = L;
    } else {
        L->next = NULL;
    }

    free(e);
    return v;
}

int del_last_list(element *L) {
    if (isempty_list(L)) {
        printf("Erreur : list vide\n");
        return 0;
    }

    element *e = L->previous;
    int v = e->val;
    if (L->previous->previous == L) {
        L->previous = NULL;
        L->next = NULL;
        printf("pb");
    } else {
        L->previous = L->previous->previous;
        L->previous->next = L;
    }

    free(e);
    return v;
}

void free_list(element *L) {
    if(! isempty_list(L)) {
        element *tmp = L->next;
        element *suppr;
        while (tmp != L) {
            suppr = tmp;
            tmp = tmp->next;
            free(suppr);
        }
    }
    free(L);
}


int main(int argsc, char *argv[]) {
    element *L = const_list();
    element *M = const_list();

    for (int i = 1; i<argsc; i++) {
        int a = atoi(argv[i]);
        add_first_list(M, a);
        add_last_list(L, a);
    }

    printf("List L : \n");
    printf("longeur : %d\n", len_list(L));
    print_list(L);

    printf("\nList M : \n");
    print_list(M);
    printf("longeur : %d\n", len_list(M));

    printf("\nsuppr le premier el de M : \n");
    printf("supprime : %d\n", del_first_list(M));
    print_list(M);

    printf("\nsuppr le dernier el de M : \n");
    printf("supprime : %d\n", del_last_list(M));
    print_list(M);

    printf("\nsuppr le premier el de L : \n");
    printf("supprime : %d\n", del_first_list(L));
    print_list(L);

    printf("\nsuppr le dernier el de L : \n");
    printf("supprime : %d\n", del_last_list(L));
    print_list(L);

    printf("\nfree L\n");
    free_list(L);
    return 0;
}
