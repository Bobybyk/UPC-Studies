#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <sys/stat.h>

/*
 * Prend deux paramètres src et dest, et copie le fichier de nom src vers le fichier 
 * de nom dest sans utiliser de tampons ni les appels système read() et write().
 * Attention aux tailles relatives des fichiers -- penser à ftruncate().
*/
void map_cp(char *src, char *dest) {
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
        addr_dest[i] = addr_src[i];
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

/*
 * Projette en mémoire (avec mmap()) un fichier 
 * dont le nom lui est passé en paramètre, puis affiche son contenu 
 * sur sa sortie standard sans appel à read().
*/
void projection(char *src) {
    int fd = open(src, O_RDONLY);
    if (fd == -1) {
        perror("open");
        exit(1);
    }
    struct stat st;
    if (fstat(fd, &st) == -1) {
        perror("fstat");
        exit(1);
    }
    char *addr = mmap(NULL, st.st_size, PROT_READ, MAP_PRIVATE, fd, 0);
    if (addr == MAP_FAILED) {
        perror("mmap");
        exit(1);
    }
    if (write(STDOUT_FILENO, addr, st.st_size) == -1) {
        perror("write");
        exit(1);
    }
    if (munmap(addr, st.st_size) == -1) {
        perror("munmap");
        exit(1);
    }
    if (close(fd) == -1) {
        perror("close");
        exit(1);
    }
}

int main() {
    projection("ex1.c");
    map_cp("ex1.c", "ex1_copy.c");
}