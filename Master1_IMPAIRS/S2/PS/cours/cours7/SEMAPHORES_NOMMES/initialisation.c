#include <semaphore.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <unistd.h>
#include "panic.h"
#include "prefix_slash.h"
#include "data_sem.h"


/* le programme (re)crée les semaphores nommées et le shared 
 * memory object
 * le programme doit être lancé avant le lancement de 
 * lecteurs/ecrivains */


int main(int argc, char **argv){
  if(argc != 4){
    fprintf(stderr,"usage : %s memory_object "
	    "sem_send sem_rec \n", argv[0]);
    exit(1);
  }
  /* ajouter / au début de noms de semaphores */
  char *sem_send_name = prefix_slash(argv[2]);
  char *sem_rec_name = prefix_slash(argv[3]);
  /* ajouter / au debut de nom de shared memory object */
  char *mem_name = prefix_slash(argv[1]);
  
  /* detruire les deux semaphores nommes.
   * On les recontruira immediatement apres.  
   * Ces objets sont persistants donc il vaut mieux les reconstruire
   * de rien à  chaque fois au lieu d'utiliser les semaphores avec 
   * le contenu indeterminé.
*/ 
  sem_unlink(sem_send_name);
  sem_unlink(sem_rec_name);
  shm_unlink(mem_name);

  /* creer et ouvrir les deux semaphores nommes */
  sem_t *sem_send=sem_open(sem_send_name, O_CREAT ,
			   S_IRUSR | S_IWUSR, 0);
  if( sem_send == SEM_FAILED)
    PANIC_EXIT("sem_open send");

  sem_t *sem_rec=sem_open(sem_rec_name, O_CREAT ,
			  S_IRUSR | S_IWUSR, 1);
  if( sem_rec == SEM_FAILED)
    PANIC_EXIT("sem_open rec");

  /* recréer le shared memory objet */
  int fd = shm_open(mem_name, O_CREAT | O_RDWR, S_IWUSR | S_IRUSR);
  if( fd == -1)
    PANIC_EXIT("shm_open");
  
  /* ramener la taille de shared memory object a sizeof(mess)
   */
  if( ftruncate( fd, sizeof(mess) ) == -1)
    PANIC_EXIT("ftruncate");
}
