#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#include<assert.h>

typedef struct {
    int nbr;
    char **words;
} w_index;

void free_index(w_index *pi) {
    for (int i = 0; i<pi->nbr; i++) {
        free(pi->words[i]);
    }
    free(pi->words);
    free(pi);
}

int size_words(w_index *pi) {
    int len = 0;
    for (int i = 0; i<pi->nbr; i++) {
        len += strlen(pi->words[i]);
    }
    return len;
}

char *concat_words(w_index *pi) {
    if (pi->nbr == 0) return "";
    int len = size_words(pi) + pi->nbr - 1; // il faut compter les espaces a ajouter entre les mots
    char *str = malloc(len * sizeof(char));
    assert(str != NULL);
    int j = 0;
    for (int i = 0; i<pi->nbr; i++) {
        for (int k = 0; pi->words[i][k] != '\0'; k++) {
            str[j] = pi->words[i][k];
            j++;
        }
        str[j] = ' ';
        j++;
    }
    return str;
}

int main() {
    char *s1 = malloc(5 * sizeof(char));
    char *s2 = malloc(6 * sizeof(char));
    char *s3 = malloc(4 * sizeof(char));
    char *s4 = malloc(2 * sizeof(char));
    for (int i=0; i<4; i++) {
        s1[i] = 'a';
    }
    s1[4] = '\0';
    for (int i=0; i<5; i++) {
        s2[i] = 'b';
    }
    s2[5] = '\0';
    for (int i=0; i<3; i++) {
        s3[i] = 'c';
    }
    s3[3] = '\0';
    for (int i=0; i<1; i++) {
        s4[i] = 'd';
    }
    s4[1] = '\0';

    char **w = malloc(4 * sizeof(char*));
    w[0] = s1;
    w[1] = s2;
    w[2] = s3;
    w[3] = s4;

    w_index *index = malloc(sizeof(w_index));
    index->nbr = 4;
    index->words = w;

    printf("====== TEST SIZE WORDS ======\n");
    printf("shoud be 13 : %d\n", size_words(index));

    printf("====== TEST CONCAT WORDS =====\n");
    char *str = concat_words(index);
    for (int i=0; str[i] != '\0'; i++) {
        printf("%c",str[i]);
    }
    printf("\n");
    return 0;
}
