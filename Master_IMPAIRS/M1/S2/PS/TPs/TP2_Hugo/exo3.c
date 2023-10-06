#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main(int argc, char *argv[]) {

    int count = argc;
    int index = 1;

    if(strcmp(argv[0], "twice") == 0 || strcmp(argv[0], "./twice") == 0)
        count = 2;
    else if(strcmp(argv[0], "repeat") == 0 || strcmp(argv[0], "./repeat") == 0) {
        count = atoi(argv[1]);
        index = 2;
    } else {
        fputs("Unrecognized program name\n", stderr);
        exit(EXIT_FAILURE);
    }

    for(int i=0; i<count; i++) {

        int pid = fork();
        switch(pid) {

            case -1:
                perror("fork");
                exit(EXIT_FAILURE);

            case 0: //child
                execvp(argv[index], argv + index);
                fputs("An error occured\n", stderr);
                exit(EXIT_FAILURE);

            default : //parent
                wait(NULL);
                break;
        }

    }

    return 0;
}
