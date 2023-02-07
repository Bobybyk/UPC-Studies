#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <time.h>
#include <sys/wait.h>

int child();
void parent();

int main(int argc, char  *argv[]) {

    (void) argc;
    (void) argv;

    for(int i=0; i<10; i++) {
        int pid = fork();

        if(pid < 0) {

            perror("fork");
            goto error;

        } else if(pid == 0) {
            exit(child());
        }
    }

    parent();
    
    return EXIT_SUCCESS;

error:
    return EXIT_FAILURE;
}

int child() {

    int pid = getpid();
    srand(time(NULL) * pid);

    int random = rand() % 100;

    return random;
}

void parent() {

    int pid = getpid();
    srand(time(NULL) * pid);

    int random = rand() % 100;

    int closest_pid = 0;
    int closest_nbr = 101;

    for(int i=0; i<10; i++) {
        int status;
        int child_pid = waitpid(-1, &status, 0);

        int ret;

        if(WIFEXITED(status)) {
            ret = WEXITSTATUS(status);
        } else {
            fputs("A child crashed",stderr);
            exit(EXIT_FAILURE);
        }

        if(abs(random - ret) < abs(closest_nbr - ret)) {
            closest_nbr = ret;
            closest_pid = child_pid;
        }
    }

    printf("%d a gagné avec le nombre %d, mon nombre était %d\n", closest_pid, closest_nbr, random);
}