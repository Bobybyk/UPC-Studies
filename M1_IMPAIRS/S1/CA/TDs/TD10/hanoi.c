#include <stdio.h>
#include <stdlib.h>

void mvt(int src, int tgt) {
  printf("%d --> %d\n", src, tgt);
}

void hanoi(int nb_disques, int src, int tgt) {
  if (nb_disques <= 0)
    return;
  int itm = 6 - src - tgt;
  hanoi(nb_disques - 1, src, itm);
  mvt(src, tgt);
  hanoi(nb_disques - 1, itm, tgt);
}

int main(int argc, char *argv[]) {
  int nb_disques = 3;
  if (argc > 1) {
    nb_disques = atoi(argv[1]);
  }
  hanoi(nb_disques, 1, 3);
  return 0;
}
