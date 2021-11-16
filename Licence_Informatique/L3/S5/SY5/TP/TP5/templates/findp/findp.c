#define _POSIX_C_SOURCE 200809L
#define _DEFAULT_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <dirent.h>
#include <string.h>
#include <libgen.h>

struct stat stat_buffer;

/* Alloue un nouveau buffer avec malloc(), y stocke la concaténation des trois
 * chaînes passées en arguments, et renvoie un pointeur vers le nouveau
 * buffer. Renvoie NULL en cas d'échec de l'allocation. */
char * concat_strings(const char * str1, const char * str2, const char * str3) {
  /* TODO[1] : Allouer un buffer de la bonne taille, y stocker la concaténation
   * de str1, str2 et str3, et renvoyer un pointeur vers ce buffer */
}

/*  parcourt récursivement le répertoire de référence path_name pour y
 *  chercher les fichiers de nom (de base) target_name;
 *  retourne le nombre de fichiers trouvés */
int process_dir(char * path, char * target_name) {
  DIR *dir;
  int nb_matches = 0;

  /* TODO[2] : parcourir les entrées du répertoire */
  
  /* TODO[2] : pour chaque entrée, tester si son nom de base coïncide
   * avec celui cherché */

  /* TODO[3] : si l'entrée considérée est un répertoire, y poursuivre récursivement 
   * la recherche */

  return nb_matches;
}



int main(int argc, char ** argv) {
  char *path;
  char *target;
  int nb_matches;

  switch (argc) {
    case 2:
      path = ".";
      target = argv[1];
      break;
    case 3:
      path = argv[1];
      target = argv[2];
      break;
    default:
      dprintf(STDERR_FILENO, "usage: %s path target\n", argv[0]);
      exit(EXIT_FAILURE);
  }

  /* TODO[2] : vérifier que le chemin est valide et désigne un répertoire */
  
  /* Lancer la recherche */
  nb_matches = process_dir(path, target);

  /* TODO[2] : terminer avec EXIT_SUCCESS ou EXIT_FAILURE selon les cas */
  exit(EXIT_SUCCESS);
}
