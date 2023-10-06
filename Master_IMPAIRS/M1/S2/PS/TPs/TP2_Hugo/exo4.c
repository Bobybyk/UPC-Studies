#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

void child(char *argv[]);
void parent(char *argv[]);

int main(int argc, char *argv[]) {

    (void)argc;

    int pid = fork();
    switch(pid) {

        case -1:
            perror("fork");
            exit(EXIT_FAILURE);

        case 0:     //child
            child(argv);
            break;

        default:    //parent 
            parent(argv);
            break;
    }

    return EXIT_SUCCESS;
}

void child(char *argv[]) {

    char *args[] = {argv[1], NULL};
    execvp(argv[1], args);

    perror("exec");
    exit(EXIT_FAILURE);
}

void parent(char *argv[]) {
    int status;
    wait(&status);

    if(status == 0) {

        char *args[] = {argv[2], NULL};
        execvp(argv[2], args);

    } else {

        char *args[] = {argv[3], NULL};
        execvp(argv[3], args);

    }
}
