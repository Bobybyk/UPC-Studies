#include<stdio.h>

int copy(FILE *fsrc, FILE *fdst) {
    rewind(fsrc); // get to the beginnig of the source
    fseek(fdst, 0, SEEK_END); // get to the end of the destination

    // write the contents of the file
    char ch;
    while ( (ch = fgetc(fsrc)) != EOF) {
        int r = fputc(ch, fdst);
        if (r == EOF) return -1;
    }

    return 0;
}

int main(int argc, char** argv) {
    FILE *s = fopen("test1", "r");
    FILE *d = fopen("test2", "r+");

    copy(s, d);

    return 0;
}
