#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "mppsh-parsing.h"
#define MAIN
#include "mppsh-exec.h"

#define BLEU   "\033[94;1m"
#define ROUGE  "\033[91;1m"
#define NORMAL "\033[00m"
#define PROMPT "mpsh $ " 

char *argv[MAX_TOKENS];
char ligne[MAX_LINE];
int argc;

int main() {

  /* boucle principale */
  while(1) {
    /* affichage de l'invite de commande */
    dprintf(STDOUT_FILENO, "%s%s%s", BLEU, PROMPT, NORMAL);

    /* TODO : lecture de la ligne de commande */

    /* découpage selon les espaces */
    argc = line_to_tokens(ligne, argv);
    if (argc == 0) continue;

    /* TODO  : exécution de la commande */

  }

  return 0;
}
