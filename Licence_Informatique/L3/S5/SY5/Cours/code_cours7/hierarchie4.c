#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main(void){
  printf("père : pid = %d\n", getpid());

  switch(fork()) {
    case -1 :
      perror("fork1");
      exit(1);
    case 0 :
      printf("fils : pid = %d\n", getpid());
      exit(1);
    default:
      pause();
  }
  
  return 0;
}
