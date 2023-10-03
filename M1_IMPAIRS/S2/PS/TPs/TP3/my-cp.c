#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>

#define BUFSIZE 1024

void my_cat() {
    char buf[BUFSIZE];
    ssize_t bytes_read;
    while ((bytes_read = read(STDIN_FILENO, buf, BUFSIZE)) > 0) {
        write(STDOUT_FILENO, buf, bytes_read);
    }
}

int main(int argc, char *argv[]) {
    int fd_in, fd_out;

    if (argc != 3) {
        fprintf(stderr, "Usage: %s <source> <destination>\n", argv[0]);
        return 1;
    }

    // Open the input file for reading
    fd_in = open(argv[1], O_RDONLY);
    if (fd_in < 0) {
        perror(argv[1]);
        return 1;
    }

    // Open the output file for writing, creating it if necessary
    fd_out = open(argv[2], O_WRONLY | O_CREAT | O_TRUNC, 0666);
    if (fd_out < 0) {
        perror(argv[2]);
        close(fd_in);
        return 1;
    }

    // Redirect the standard input and output to the input and output files
    if (dup2(fd_in, STDIN_FILENO) < 0) {
        perror("dup2");
        close(fd_in);
        close(fd_out);
        return 1;
    }
    if (dup2(fd_out, STDOUT_FILENO) < 0) {
        perror("dup2");
        close(fd_in);
        close(fd_out);
        return 1;
    }

    // Call my_cat() to copy the input file to the output file
    my_cat();

    // Close the input and output files and restore the standard input and output
    close(fd_in);
    close(fd_out);
    dup2(STDIN_FILENO, STDIN_FILENO);
    dup2(STDOUT_FILENO, STDOUT_FILENO);

    return 0;
}