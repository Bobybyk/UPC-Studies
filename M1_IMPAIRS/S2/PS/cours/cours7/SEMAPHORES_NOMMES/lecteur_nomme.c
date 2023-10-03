#include <semaphore.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <unistd.h>
#include "panic.h"
#include "prefix_slash.h"
#include "data_sem.h"

int main(int argc, char **argv){
  if(argc != 4){
    fprintf(stderr,"usage : %s memory_object"
	    "sem_send sem_rec \n", argv[0]);
    exit(1);
  }

    /* ajouter / au début de noms de semaphores */
  char *sem_send_name = prefix_slash(argv[2]);
  char *sem_rec_name = prefix_slash(argv[3]);
  /* ajouter / au debut de nom de shared memory object */
  char *mem_name = prefix_slash(argv[1]);


  /* ouvrir les deux semaphores nommes */
  sem_t *sem_send=sem_open(sem_send_name, 0 );
  if( sem_send == SEM_FAILED)
    PANIC_EXIT("sem_open send");

  sem_t *sem_rec=sem_open(sem_rec_name, 0);
  if( sem_rec == SEM_FAILED)
    PANIC_EXIT("sem_open rec");

  /* ouvrir le shared memory objet */
  int fd = shm_open(mem_name,  O_RDWR, 0);
  if( fd == -1)
    PANIC_EXIT("shm_open");

  /* projection de shared memory object en mémoire */
  mess *buf;
  buf = mmap(NULL, sizeof(mess), PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0);
  if( buf == MAP_FAILED)
    PANIC_EXIT("mmap");

  while( 1 ){
    int i;
    
    // attendre jusqu'a ce que le semaphore sem_send soit > 0
    // si sem_wait interrompu
    // par un signal revenir tout de suite à sem_wait
    while( (i = sem_wait( sem_send )) == -1 && errno == EINTR)
      ;
    
    /* traiter erreur (différent de EINTR) de sem_wait */
    if( i == -1  )
      PANIC_EXIT("sem_send");


    /* ecrire le message recu sur le terminal avec le pid de
     * l'ecrivain
     */
    printf("envoye par : %u \n%s\n",
	   (unsigned)buf->pid, buf->msg);

    /* lever le semaphore sem_rec */
    if( sem_post(sem_rec) == -1){
      PANIC("sem_rec");
      shm_unlink(mem_name);
      exit(1);
    }
  }	
}
