#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>

void child();
void parent();

int main(int argc, char *argv[]) {

    (void) argc;
    (void) argv;

    for(int i = 0; i<5; i++) {
        int pid = fork();

        if(pid < 0) {
            perror("fork");
            goto error;
        } else if (pid == 0) {
            child();
            exit(EXIT_SUCCESS);
        }
    }

    parent();

    return EXIT_SUCCESS;

error:
    return EXIT_FAILURE;
}

void child() {
    int pid = getpid();
    printf("%d: I am alive !\n", pid);
    sleep(60);
} 

void parent() {
    int status;
    char cause[50]; 

    for(int i=0; i<5; i++) {
        int child_pid = waitpid(-1, &status, 0);

        if(WIFEXITED(status)) {
            strcpy(cause, "died peacefully in its sleep");
        } else if(WIFSIGNALED(status)) {
            strcpy(cause, "was killed by a signal");
        }

        printf("The process %d %s\n", child_pid, cause);
    }
}
