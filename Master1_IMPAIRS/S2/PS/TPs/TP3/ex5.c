#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

#define BUFSIZE 1

int main() {

    // Créer/ouvrir le fichier compteur
    int fd = open("files/train.data", O_RDWR | O_CREAT, 0644);
    if (fd == -1) {
        perror("open");
        exit(1);
    }

    int i = 0;
    char buf[BUFSIZE];

    do {
        read(fd, buf, BUFSIZE);
        printf("%i", i);
        i++;
    } while (i < 1023 && buf[0] == '0');

    if (i < 1024 && buf[0] != '0') {
        buf[0] = '0';
        if (write(fd, buf, i) == -1) {
            perror("write");
            exit(1);
        }
        printf("Reserved place on %i\n", i);
    } else {
        printf("Not enough space\n");
    }

    close(fd);

}