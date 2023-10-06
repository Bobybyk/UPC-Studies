#include <pthread.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <unistd.h>
#include "panic.h"
#include "prefix_slash.h"
#include "memory.h"
#include "thread_error.h"
#include "open_memory.h"
#define LEN 256

int main(int argc, char **argv){
  if(argc != 2){
    fprintf(stderr,"usage : %s shared_memory_object\n", argv[0]);
    exit(1);
  }
  
  char *shm_name = prefix_slash(argv[1]);

  memory *mem = open_memory(shm_name);
  
  while( 1 ){
    int code;

    /*  mutex_lock */
    if( (code = pthread_mutex_lock(&mem->mutex) ) != 0 ){
      thread_error( __FILE__ , __LINE__ , code, "mutex_lock" );
    }
    
    /* attendre la nouvelle valeur de data */    
    while( ! mem->is_new_value )
      if( ( code = pthread_cond_wait( &mem->rcond, &mem->mutex) ) > 0 ){
	fprintf(stderr, "pid %u\n", (unsigned) getpid() );
	thread_error( __FILE__, __LINE__, code, "pthread_cond_wait" );
      }
    printf( "consumer %d received %d from %u\n", (int)getpid(),
	    mem->data, (unsigned) mem->pid);
    mem->is_new_value = false; /* les données consommées*/

    if(msync( mem, sizeof(mem), MS_SYNC) < 0)
      PANIC_EXIT("msync");
    
    if( ( code = pthread_mutex_unlock(&mem->mutex)) != 0)
      thread_error( __FILE__ , __LINE__ , code, "mutex_lock" );
    
    /* signaler un producer */ 
    if( ( code = pthread_cond_signal( &mem->wcond ) ) > 0 )
      thread_error( __FILE__ , __LINE__ , code, "mutex_lock" );
    
    sleep(1);
  }	
}
