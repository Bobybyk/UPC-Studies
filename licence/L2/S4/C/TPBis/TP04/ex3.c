#include<stdio.h>
#include<stdlib.h>
#include<assert.h>


typedef struct array {
    int *ptr;
    int size;
} array;

void array_print(array *t) {
    for (int i = 0; i<(*t).size; i++) {
        printf("%d ", (*t).ptr[i]);
    }
    printf("\n");
}

array * array_init(int n) {
    array *a = malloc(sizeof(array));
    int *t = malloc(n * sizeof(int));
    if (t == NULL || a == NULL) {
        printf("Probleme d'allocation memoire");
        return NULL;
    }
    (*a).ptr = t;
    (*a).size = n;
    return a;
}

void array_destroy(array *t) {
    free(t);
}

int array_get(array *t, int index) {
    assert(index < (*t).size); // make sure we are inside the allocated memory
    return (*t).ptr[index];
}

void array_set(array *t, int index, int valeur) {
    assert(index < (*t).size);
    (*t).ptr[index] = valeur;
}

int array_insert(array *t, int index, int valeur) {
    assert(index <= (*t).size);

    int newSize = (*t).size +1;
    int *p = malloc(newSize * sizeof(int));
    if (p == NULL) {
        return 0;
    }

    for (int i = 0; i<index; i++) {
        p[i] = (*t).ptr[i];
    }
    p[index] = valeur;
    for (int i = index+1; i<newSize; i++) {
        p[i] = (*t).ptr[i-1];
    }
    free((*t).ptr);

    (*t).ptr = p;
    (*t).size = newSize;
    return 1;
}

void array_erase(array *t, int index) {
    assert(index < (*t).size);
    t -> size -= 1;
    for (int i = index; i<(*t).size; i++) {
        (*t).ptr[i] = (*t).ptr[i+1];
    }

}

int main() {
    printf("test array_init et array_print\n");
    array t = * array_init(10);
    array_print (&t);
    printf("\n");

    printf("test array_set\n");
    for (int i = 0; i < t.size; i++) {
        array_set(&t, i, i);
    }
    array_print(&t);
    printf("\n"); // 0 1 2 3 4 5 6 7 8 9

    printf("test array_get\n");
    for (int i = 0; i < t.size; i++) {
        printf ("%d ", array_get (&t, i));
    }
    printf ("\n"); // 0 1 2 3 4 5 6 7 8 9

    printf("test array_insert\n");
    array_insert (&t, 3, 42);
    array_print (&t);
    printf("\n");// 0 1 2 42 3 4 5 6 7 8 9
    array_insert (&t, 11, 43);
    array_print (&t);
    printf("\n"); // 0 1 2 42 3 4 5 6 7 8 9 43

    printf("test array_erase\n");
    array_erase (&t, 11);
    array_print (&t);
    printf("\n"); // 0 1 2 42 3 4 5 6 7 8 9
    array_erase (&t, 3);
    array_print (&t);
    printf("\n"); // 0 1 2 3 4 5 6 7 8 9
    array_erase (&t, 0);
    array_print (&t);
    printf("\n"); // 1 2 3 4 5 6 7 8 9

    return 0;
}
