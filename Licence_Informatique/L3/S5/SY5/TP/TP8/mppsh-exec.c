#include <unistd.h>
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>
#include <stdio.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "mppsh-exec.h"

#define NB_BUILTINS 5
char *builtins [NB_BUILTINS] = {
  "cd",
  "echo",
  "exit",
  "clear",
  "jobs"
};

#define RED "\033[1;31m"
#define NO_COLOR "\033[0m"

/* recouvre le processus courant avec argv après redirection de l'entrée
 * et de la sortie standard */
void execute(char **argv, char *ref_in, char *ref_out, int flag) {
	int fd_out, fd_in; 
	int err = 0; 

  /* DONE: redirection de l'entrée si ref_in non nul */
	if(ref_in != NULL) {
		fd_in = open(ref_in,O_RDONLY);

		if(fd_in < 0) {
			perror("open in");
			return;
		}
		err = dup2(fd_in,0);
		if(err < 0) {
			perror("dup2 in");
			return;
		}
	}

  /* DONE : redirection de la sortie si ref_out non nul */
	if(ref_out != NULL) {
		fd_out = open(ref_out,O_WRONLY | flag);

		if(fd_out < 0) {
			perror("open out");
			return;
		}
		err = dup2(fd_out,1);
		if(err < 0) {
			perror("dup2 out");
			return;
		}
	}

  /* DONE : recouvrement */
	execvp(argv[0],argv);
	dprintf(2,"%s%s%s\n",RED,"No such command",NO_COLOR);
	return;
}

/* lance l'exécution d'une commande externe, avec redirections éventuellement,
 * à l'avant-plan si bg==0, à l'arrière-plan sinon */
int exec_external(int argc, char **argv, char *in_out[2], int flag, int bg) {

	int status;

	if(bg == 0) {

		switch(fork()) {
			case -1:
				perror("fork");
				return -1;
			case 0: //fils
				execute(argv,in_out[0],in_out[1],flag);
				exit(-1);
			default: //pere
				wait(&status);
				break;
		}
	} else {
		printf("Launching task in background\n");
		int pid = fork();
		switch(pid) {
			case -1:	//error
				perror("fork");
				return -1;
			case 0: 	//fils
				setsid();
				execute(argv,in_out[0],in_out[1],flag);
				exit(-1);
				break;
			default:	//pere
				if(nb_jobs < MAX_JOBS) {
					pid_jobs[nb_jobs++] = pid;
				}
				break;
		}

	}
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
	int fd_out = 1, err;
	if(in_out[1] != NULL) {
		fd_out = open(in_out[1],O_WRONLY | flag);
		if(fd_out < 0) {
			perror("open");
			return -1;
		}	
	}

	if(strcmp(argv[0],"exit") == 0) {
		return 1;	
	} else if(strcmp(argv[0],"cd") == 0) {
		err = chdir(argv[1]);
		if(err < 0) {
			perror("cd");
			return -1;
		}
	} else if(strcmp(argv[0],"echo") == 0) {
		dprintf(fd_out,"%s\n",argv[1]);
	} else if(strcmp(argv[0],"jobs") == 0) {
		if(nb_jobs == 0) {
			printf("No running jobs\n");
			return 0;
		}
		for(int i =0;i<nb_jobs;i++)
			printf("%d\n",pid_jobs[i]);
	} else if(strcmp(argv[0],"clear") == 0) {
		write(1,"\e[1;1H\e[2J\n",strlen("\e[1;1H\e[2J"));
	}
	return 0;
}

/* examen des jobs au début de chaque tour de boucle */
void examine_bg () {

  /* TODO : à compléter */
	int status;
	for(int i=0;i<nb_jobs;i++) {
		waitpid(pid_jobs[i],&status, WNOHANG | WUNTRACED);
		if(WIFEXITED(status)) {
			printf("Job #%d has terminated\n",pid_jobs[i]);
			for(int j=i;j<nb_jobs-1;j++) {
				pid_jobs[i] = pid_jobs[i+1];
			}
			memset(&pid_jobs[--nb_jobs],0,sizeof(int));
		}
	}
}
