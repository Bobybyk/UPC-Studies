#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>
#include <sys/file.h>

#define LOCKFILE "/tmp/lonely.lock"
#define MAX 10

int main() {
    int fd = open(LOCKFILE, O_RDWR|O_CREAT, 0600);
    if (fd == -1) {
        perror("Failed to open lockfile");
        return 1;
    }
    int rc = flock(fd, LOCK_EX|LOCK_NB);
    if (rc == -1) {
        if (errno == EWOULDBLOCK) {
            printf("Another instance is already running.\n");
            return 127;
        } else {
            perror("Failed to lock file");
            return 1;
        }
    }

    char pid_str[10];
    sprintf(pid_str, "%d\n", getpid());
    write(fd, pid_str, strlen(pid_str));

    int count = 0;
    while (count < MAX) {
        printf("je suis seul\n");
        sleep(5);
        count++;
    }

    close(fd);
    unlink(LOCKFILE);
    return 0;
}