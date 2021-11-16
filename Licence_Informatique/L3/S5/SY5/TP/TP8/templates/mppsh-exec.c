#include <unistd.h>
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>
#include <stdio.h>
#include "mppsh-exec.h"

#define NB_BUILTINS 4
char *builtins [NB_BUILTINS] = {
  "cd",
  "echo",
  "exit",
  "jobs"
};


/* recouvre le processus courant avec argv après redirection de l'entrée
 * et de la sortie standard */
void execute(char **argv, char *ref_in, char *ref_out, int flag) {

  /* TODO : redirection de l'entrée si ref_in non nul */

  /* TODO : redirection de la sortie si ref_out non nul */

  /* TODO : recouvrement */

}

/* lance l'exécution d'une commande externe, avec redirections éventuellement,
 * à l'avant-plan si bg==0, à l'arrière-plan sinon */
int exec_external(char **argv, char *in_out[2], int flag, int bg) {

   /* TODO[1] : gérer uniquement le cas bg = 0 */

  return 0;
}


int is_builtin(char *cmd) {
  for (int i = 0 ; i < NB_BUILTINS; i++) {
    if (strcmp(builtins[i], cmd) == 0)
      return 1;
  }
  return 0;
}

/* gère l'exécution des commandes internes, éventuellement avec
 * redirection, toujours à l'avant-plan */
int exec_builtin(int argc, char **argv, char *in_out[2], int flag) {

  /* TODO : à compléter */

  return 0;
}

/* examen des jobs au début de chaque tour de boucle */
void examine_bg () {

  /* TODO : à compléter */

}

