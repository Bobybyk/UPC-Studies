#ifndef DATA_H
#define DATA_H
struct mess{
  sem_t sem_send;/* nb of senders */
  sem_t sem_rec; /* 1 when ready to receive */
  pid_t  pid;
  size_t len;
  char msg[];
};
#endif

