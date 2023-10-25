#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "mppsh-parsing.h"
#define MAIN
#include "mppsh-exec.h"

#define BLEU   "\033[94;1m"
#define ROUGE  "\033[91;1m"
#define NORMAL "\033[00m"
#define PROMPT "mpsh" 

char *argv[MAX_TOKENS];
char ligne[MAX_LINE];
int argc;

int main() {

  /* boucle principale */
  while(1) {
	  examine_bg();
    /* affichage de l'invite de commande */
    dprintf(STDOUT_FILENO, "%s%s-%s $ %s", BLEU, PROMPT,getcwd(NULL,NULL), NORMAL);

    /* DONE : lecture de la ligne de commande */
    int ret = read(0,ligne,MAX_LINE);
    memset(ligne+ret,0,1);
    if(ret < 0) {
	    perror("read line");
	    return EXIT_FAILURE;
    }

    /* découpage selon les espaces */
    argc = line_to_tokens(ligne, argv);
    if (argc == 0) continue;

    /* TODO  : exécution de la commande */
    char* streams[2];
    int flag;
    argc = parse_redirections(argv,streams,&flag);
    if(is_builtin(argv[0])) {
	    ret = exec_builtin(argc,argv,streams,flag);
	    if(ret == 1)
		    break;
    } else {
	    if(strcmp(argv[argc-1],"&") == 0) {
		    argv[--argc] = NULL; 
		    ret = exec_external(argc,argv,streams,flag,1);
	    }else
		    ret = exec_external(argc,argv,streams,flag,0);
    }
  }
  return 0;
}
