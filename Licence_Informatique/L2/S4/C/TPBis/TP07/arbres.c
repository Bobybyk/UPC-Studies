#include<assert.h>
#include "./afficheur.h"

node *const_tree(int val, node *left, node *right) {
    node *n = malloc(sizeof(node));
    assert(n != NULL);

    n->val = val;
    n->left = left;
    n->right = right;

    return n;
}

void free_tree(node *t) {
    if (t == NULL) return;
    free_tree(t->left);
    free_tree(t->right);
    free(t);
}

int size_tree(node *t) {
    if (t == NULL) return 0;
    return 1 + size_tree(t->left) + size_tree(t->right);
}

int sum_tree(node *t) {
    if (t == NULL) return 0;
    return t->val + sum_tree(t->left) + sum_tree(t->right);
}

int depth_tree(node *t) {
    if (t == NULL) return 0;
    int a = 1 + depth_tree(t->left);
    int b = 1 + depth_tree(t->right);
    if (a<b) {
        return b;
    } else {
        return a;
    }
}

void print_abr(node *t) {
    if (t == NULL) return;
    print_abr(t->left);
    printf(" %d ", t->val);
    print_abr(t->right);
}

node *insert_abr(node *t, int val) {
    if (t == NULL) {
        t = const_tree(val, NULL, NULL);
        return t;
    }

    if (val < t->val) {
        t->left = insert_abr(t->left, val);
        return t;
    } else {
        t->right = insert_abr(t->right, val);
        return t;
    }
}

node *search_abr(node *t, int val) {
    if (t == NULL) return NULL;
    else if (t->val == val) return t;
    else if (val < t->val) return search_abr(t->left, val);
    else return search_abr(t->right, val);
}

node *max_abr(node *t) {
    if (t == NULL) return NULL;
    else if (t->right == NULL) return t;
    else return max_abr(t->right);
}

node *min_abr(node *t) {
    if (t == NULL) return NULL;
    else if (t->left == NULL) return t;
    else return min_abr(t->left);
}

int check_abr(node *t) {
    if (t == NULL) return 1;
    else if (t->left != NULL && t->val < t->left->val) {
        return 0;
    } else if (t->right != NULL && t->val > t->right->val) {
        return 0;
    }
    int res = check_abr(t->left) + check_abr(t->right);
    return res == 2;
}

int main() {
    node *t;

    t = const_tree(1,const_tree(3, NULL, NULL),
                const_tree(6, const_tree(4, NULL, NULL), NULL));

    pretty_print(t);
    //free_tree(t);
    printf("==== EXERCICE 3 ====\n");
    printf("size of tree : %d\n", size_tree(t));
    printf("sum of all val : %d\n", sum_tree(t));
    printf("depth : %d\n", depth_tree(t));

    printf("\n==== EXERCICE 5 ====\n");
    node *n = NULL;
    int vals[10] = {8, 3, 1, 2, 6, 4, 7, 10, 14, 13};
    for (int i = 0; i<10; i++) {
        n = insert_abr(n, vals[i]);
    }
    pretty_print(n);
    printf("\n");
    print_abr(n);
    printf("\n");

    printf("\n==== EXERCICE 6 ====\n");
    node *s = search_abr(n, 6);
    printf("on trouve bien 6 : %d\n", s->val);

    printf("\n==== EXERCICE 7 ====\n");
    node *min = min_abr(n);
    node *max = max_abr(n);
    printf("max : %d\n", max->val);
    printf("min : %d\n", min->val);
    printf("ABR : %d\n", check_abr(n));
    printf("pas ABR : %d\n", check_abr(t));
    return 0;
}
