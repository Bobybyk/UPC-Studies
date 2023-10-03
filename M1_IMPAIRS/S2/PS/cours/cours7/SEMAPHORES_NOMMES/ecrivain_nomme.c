#include <semaphore.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <unistd.h>
#include "panic.h"
#include "prefix_slash.h"
#include "data_sem.h"

/* la fonction fabrique une chaine de caracteres
 * en concatenant i et mess
 */ 
char *do_message(const char *mes, unsigned i){
  static char buf[LEN];
  snprintf(buf, LEN, "%3u %s", i, mes);
  return buf;
}

int main(int argc, char **argv){
  if(argc != 5){
    fprintf(stderr,"usage : %s memory_object "
	    "sem_send sem_rec message\n", argv[0]);
    exit(1);
  }

  /* ouvrir les deux semaphores sem_send et sem_rec
   * les deux semaphores doivent deja exiter
   */
  char *sem_send_name = prefix_slash(argv[2]);
  sem_t *sem_send=sem_open(sem_send_name, 0 );
  if( sem_send == SEM_FAILED)
    PANIC_EXIT("sem_open send");
  
  char *sem_rec_name = prefix_slash(argv[3]);
  sem_t *sem_rec=sem_open(sem_rec_name, 0 );
  if( sem_rec == SEM_FAILED)
    PANIC_EXIT("sem_open rec");
  
  /* ajouter / au debut de nom de shared memory object*/ 
  char *mem_name = prefix_slash(argv[1]);
  /* ouvrir shared memory object */
  int fd = shm_open(mem_name,  O_RDWR, S_IWUSR | S_IRUSR);
  if( fd == -1)
    PANIC_EXIT("shm_open");
  

  /* recuper la longueur de shared memory object pour la 
     projetction en memoire */
  struct stat bufStat;
  if( fstat( fd, &bufStat ) == -1)
    PANIC_EXIT("fstat");

  //printf("client len = %d\n", (int) bufStat.st_size) ;  
  // projection de shared memory object en memoire
  struct mess *buf;

  buf = mmap(NULL, bufStat.st_size, PROT_READ|PROT_WRITE,
	     MAP_SHARED, fd, 0);
  if( buf == MAP_FAILED)
    PANIC_EXIT("mmap");


  /* ecrire N messages dans la memoire partagee*/
#define N  10
  for(unsigned j=0; j < N; j++){
    int i;
    //attendre que le semaphore sem_send soit positif
    while( (i = sem_wait( sem_rec )) == -1 && errno == EINTR)
      ;
    if( i == -1) /* si d'autres erreur de sem_wait */
      PANIC_EXIT("sem_wait");


    //mettre le message dans la memoire partagee
    buf->pid = getpid();

    char *mess = do_message(argv[4], j);
    size_t n = strlen( mess ) + 1;
    buf->len = n < LEN ? n : LEN;
    strncpy(buf->msg, mess, buf->len );
    buf->msg[LEN-1] = 0;/* to be sure that buf->mess is
			  * a nul terminating string*/
    
    /* lever le semaphore sem_sen */
    if( sem_post(sem_send) == -1){
      PANIC_EXIT("sem_wait");
      shm_unlink(mem_name);
      exit(1);
    }
  }	
}
