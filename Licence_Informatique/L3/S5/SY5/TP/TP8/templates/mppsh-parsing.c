#include <fcntl.h>
#include <string.h>
#include "mppsh-parsing.h"

/* découpe une chaîne de caractères selon les (blocs d')espaces,
 * stocke dans tokens les pointeurs vers les différents mots, 
 * et renvoie le nombre de mots */
int line_to_tokens(char *line, char **tokens) {
  int i = 0;
  memset(tokens, 0, MAX_TOKENS*sizeof(char*));

  while (line != NULL) {
    // découpage au niveau des espaces et de la fin de ligne
    tokens[i] = strsep(&line, " \t\n");	
    // incrémentation de i seulement si le mot trouvé est non vide
    if (*tokens[i] != '\0') i++;		
  }
  tokens[i] = NULL;

  return i;
}

/* retourne le premier connecteur (; && ||) s'il y en a, NULL sinon */
char **parse_connector(char **tokens) {
  for (; *tokens; tokens++) 
    if (!strcmp(*tokens, ";") || !strcmp(*tokens, "&&") || !strcmp(*tokens, "||"))
      return tokens;
  return NULL;
}

/* décalage des tokens (de gap cases vers la gauche) 
 * retourne le nombre de valeurs décalées */
int shift(char **tokens, int gap) {
  int i, j;
  for(i = 0; tokens[i+gap]; i++) tokens[i] = tokens[i+gap];
  for(j = 0; j<gap; j++) tokens[i+j] = NULL;
  return i;
}


/* stocke dans in et out les noms des fichiers de redirection, si des
 * redirections sont définies,
 * décale le cas échéant les arguments à droite de ces définitions 
 * et retourne (le nouvel) argc;
 * ne gère pas les éventuelles définitions concurrentes (du genre 
 * >> out1 >out2). */
int parse_redirections(char **tokens, char *in_out[2], int *flag) {
  int i = 0, j;
  in_out[0] = in_out[1] = NULL;
  *flag = 0;

  while (tokens[i]) {
    switch (tokens[i][0]) {
      case '>' :
        if (tokens[i][1] == '>') {
          *flag = O_APPEND;
          tokens[i]++; /* pour le cas ">>out" */
        }
      case '<' :
        j = (tokens[i][0]=='<') ? 0 : 1;
        if (tokens[i][1] != '\0') {
          in_out[j] = tokens[i]+1;
          shift(tokens+i, 1);
        }
        else {
          if (tokens[i+1] == NULL) return -1;
          in_out[j] = tokens[i+1];
          shift(tokens+i, 2);
        }
        break;
      default :
        i++;
    }
  }
  return i;
}     

