#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <sys/stat.h>

char cesar(char c, int offset) {
  if(c>='a' && c<='z')
    return (char) ('a' + ( (c-'a'+offset)%26 ));
  if (c>='A' && c<='Z')
    return (char) ('A' + ( (c-'A'+offset)%26 ));
  return c;
}

/*
 * Projette en mémoire le contenu du fichier de nom src,
 * supposé ne contenir que des lettres, et décale toutes les lettres 
 * de la même valeur, en suivant le code de César, dans le fichier dest.
 * Utilise la fonction cesar() ci-dessous.
 */
void decale(char *src, char *dest) {
    int fd_src = open(src, O_RDONLY);
    if (fd_src == -1) {
        perror("open");
        exit(1);
    }
    int fd_dest = open(dest, O_RDWR | O_CREAT | O_TRUNC, 0666);
    if (fd_dest == -1) {
        perror("open");
        exit(1);
    }
    struct stat st;
    if (fstat(fd_src, &st) == -1) {
        perror("fstat");
        exit(1);
    }
    if (ftruncate(fd_dest, st.st_size) == -1) {
        perror("ftruncate");
        exit(1);
    }
    char *addr_src = mmap(NULL, st.st_size, PROT_READ, MAP_PRIVATE, fd_src, 0);
    if (addr_src == MAP_FAILED) {
        perror("mmap");
        exit(1);
    }
    char *addr_dest = mmap(NULL, st.st_size, PROT_WRITE, MAP_SHARED, fd_dest, 0);
    if (addr_dest == MAP_FAILED) {
        perror("mmap");
        exit(1);
    }
    for (int i = 0; i < st.st_size; i++) {
        addr_dest[i] = cesar(addr_src[i], 3);
    }
    if (munmap(addr_src, st.st_size) == -1) {
        perror("munmap");
        exit(1);
    }
    if (munmap(addr_dest, st.st_size) == -1) {
        perror("munmap");
        exit(1);
    }
    if (close(fd_src) == -1) {
        perror("close");
        exit(1);
    }
    if (close(fd_dest) == -1) {
        perror("close");
        exit(1);
    }
}

int main() {
    decale("ex2.c", "bonjour.out");
    return 0;
}