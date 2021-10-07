#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <fcntl.h>
#include <string.h>
#include <getopt.h>

#include <stdio.h>

#define BUFFER_SIZE (1024*1024)

int do_wc(int in_fd, int out_fd, int write_newlines, int write_bytes, const char * in_filename);
int print_size(int fd, size_t size);

/* ./my-wc [-lc] [fichier] compte le nombre de retours à la ligne et/ou d'octets dans le
   fichier indiqué, ou à défaut sur l'entrée standard. */
int main(int argc, char * argv[]) {
  int result;
  char * in_filename = NULL;
  int write_newlines = 0;
  int write_bytes = 0;
  
  int in_fd = STDIN_FILENO;

  int opt;
  while ((opt = getopt(argc, argv, "cl")) != -1) {
    /* TODO: Gérer les options -c et -l */
  }
  /* TODO: si ni -c ni -l n'a été donnée, faire en sorte d'afficher les deux */

  /* TODO: tester si un nom de fichier a été donné (cf. man getopt -> optind) et dans ce
     cas, le mettre dans in_filename */

  /* TODO: si un nom de fichier autre que "-" a été donné, ouvrir le fichier correspondant */

  /* TODO: appeler do_wc avec les bons arguments */

 cleanup_return:
  /* TODO: fermer le fichier d'entrée. Ne pas oublier de tester s'il y a eu une erreur */
  return result;
}

/* Lit in_fd et compte le nombre de lignes et/ou d'octets lus. Affiche le résultat sur
   out_fd, suivi de in_filename si différent de NULL, le tout sous formes de colonnes
   séparées par une tabulation. Renvoie 0 en cas de succès et 1 en cas d'erreur. */
int do_wc(int in_fd, int out_fd, int write_newlines, int write_bytes, const char * in_filename) {
  int result;
  char * buffer = NULL;
  size_t newlines_count = 0;
  size_t bytes_count = 0;

  /* TODO: allouer un buffer */
   
  while(1) {
    /* TODO: lire l'entrée et compter le nombre de retours à la ligne et d'octets */
  }
  
  /* TODO: afficher ce qu'il faut */
  
  /* TODO: tout a fonctionné : mettre à jour result en conséquence */
  
 cleanup_return:
  /* TODO: libérer le buffer */
  return result;
}

/* Renvoie le nombre d'octets écrits, ou -1 en cas d'erreur. */
int print_size(int fd, size_t size) {
  /* TODO: Utiliser snprintf() avec le format %zu. */
}
