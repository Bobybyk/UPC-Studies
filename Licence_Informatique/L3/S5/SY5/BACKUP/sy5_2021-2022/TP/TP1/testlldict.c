/* testlldict.c */

#include <stdio.h>

#include "lldict.h"


int main(int argc, char *argv[argc]) {
	lldict* dic = 0;

	printf("Will add in dictionary.\n");

	dic = lldadd(dic, 0, 5);
	dic = lldadd(dic, 1, 10);
	dic = lldadd(dic, -1, 15);
	dic = lldadd(dic, 2, 20);
	dic = lldadd(dic, 3, 30);
	lldshw(dic);

	printf("Will fail to add in dictionary.\n");

	dic = lldadd(dic, 0, 10);
	lldshw(dic);

	printf("Will modify values in dictionary.\n");

	dic = lldmod(dic, 2, 0);
	lldshw(dic);
	dic = lldmod(dic, -1, 100);
	lldshw(dic);
	dic = lldmod(dic, 3, 333);
	lldshw(dic);

	printf("Will delete keys in dictionary.\n");
	dic = llddel(dic, 1);
	lldshw(dic);
	dic = llddel(dic, -1);
	lldshw(dic);
	dic = llddel(dic, 3);
	lldshw(dic);

	printf("Will fail to delete key in dictionary.\n");
	dic = llddel(dic, 3);
	lldshw(dic);

	printf("Will lookup keys in dictionary, default value is -10000.\n");
	printf("key: 2, val: %d\n", lldlkp(dic, -10000, 2));
	printf("key: 0, val: %d\n", lldlkp(dic, -10000, 0));
	printf("key: 13, val: %d\n", lldlkp(dic, -10000, 13));
}
