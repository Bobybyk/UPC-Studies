#include<stdio.h>
#include<string.h>
#include<ctype.h>

int nbr_words(const char *s) {
    if (strlen(s) == 0) return 0;
    int nb = 0;
    if (!isspace(s[0])) {
        nb = 1;
    }

    for (int i = 1; s[i] != '\0'; i++) {
        if (!isspace(s[i]) && isspace(s[i-1])) {
            nb += 1;
        }
    }
    return nb;
}

int main() {
    char *s1 = "a bbb cccc dddd eee fff";
    char *s2 = "dsgljdkjslfj";
    char *s3 = "    ";
    char *s4 = "";
    char *s5 = "   1\n 2 3 4     5   \n 6  ";
    printf("6 mots : %d\n", nbr_words(s1));
    printf("1 mot : %d\n", nbr_words(s2));
    printf("0 mot : %d\n", nbr_words(s3));
    printf("0 mot : %d\n", nbr_words(s4));
    printf("6 mots : %d\n", nbr_words(s5));

    return 0;
}
