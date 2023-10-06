#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <string.h>

#define BUFSIZE 1

int is_regular_file(const char *path) {
    struct stat path_stat;
    if (stat(path, &path_stat) != 0) {
        return 0;  // File does not exist or is not accessible
    }
    return S_ISREG(path_stat.st_mode);
}

int files_equal(char *file1, char *file2) {
    
    int fd1 = open(file1, O_RDONLY);
    int fd2 = open(file2, O_RDONLY);
    
    char buf1[BUFSIZE], buf2[BUFSIZE];
    
    int bytes_read1, bytes_read2, line_num = 1;

    do {
        bytes_read1 = read(fd1, buf1, BUFSIZE);
        bytes_read2 = read(fd2, buf2, BUFSIZE);
        for (int i = 0; i < bytes_read1; i++) {
            if (buf1[i] == '\n') {
                line_num++;
            }
        }
        if (bytes_read1 != bytes_read2 || memcmp(buf1, buf2, bytes_read1) != 0) {
            printf("Files differ at line : %d\n", line_num);
            return 0;
        }
    } while (bytes_read1 > 0 && bytes_read2 > 0);

    close(fd1);
    close(fd2);

    return 1;
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        printf("Usage: %s file1 file2\n", argv[0]);
        return 1;
    }

    if (!is_regular_file(argv[1]) || !is_regular_file(argv[2])) {
        printf("Invalid file reference\n");
        return 1;
    }

    files_equal(argv[1], argv[2]);

    return 0;
}