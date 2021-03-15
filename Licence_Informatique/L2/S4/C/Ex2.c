#include <stdlib.h>
#include <assert.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>

int nbr_words(const char *s) {
	int x = 0;
	for(int i = 0; s[i] != '\0'; i++) {
		if (!(isspace(s[i])) && isspace(s[i-1])){
			x++;
		}
	}
	return x;
}	

int word_len(char *w) {
	int x = 0;
	for(int i = 0; w[i] != '\0' && !(isspace(w[i])); i++) {
			x++;
	}
	return x;
}

char *extract_word(const char *w, int *pl) {
	char *word = malloc(word_len(w) * sizeof(char));
	strncpy(word, w, word_len(w));

	*pl = word_len(w);

	return world;

}

int main() {
	const char *s = " a aa ba a bbbb";
	char *w = " abc d";
	printf("%d\n", nbr_words(s));
	printf("%d\n", word_len(w+1));
	return 0;
}