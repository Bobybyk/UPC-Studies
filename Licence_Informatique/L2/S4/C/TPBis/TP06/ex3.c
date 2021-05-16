#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#include<assert.h>

int word_len(char *w) {
    int len = 0;
    int i = 0;
    while (w[i] != '\0' && !isspace(w[i])) {
        len += 1;
        i++;
    }
    return len;
}

char *extract_word(char *w, int *pl) {
    *pl = word_len(w);
    char *r = malloc((*pl +1) * sizeof(char));
    assert(r != NULL);

    for (int i = 0; i<*pl; i++) {
        r[i] = w[i];
    }
    r[*pl] = '\0';

    return w;
}

void print_string(const char *w) {
    for (int i=0; w[i] != '\0'; i++) {
        printf("%c", w[i]);
    }
}

int main() {
    printf("==== EXERCICE 3.1 ======\n");
    char *s1 = " abc d";
    char *s2 = "aaaa\n";
    printf("length 3 : %d\n", word_len(s1+1));
    printf("length 4 : %d\n", word_len(s2));
    printf("==== EXERCICE 3.2 ======\n");
    printf("extraire 'abc' : ");
    int len;
    char *e = extract_word(s1+1, &len);
    print_string(e);
    printf(" ; length : %d", len);
    printf("\n");
    return 0;
}
