#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <stdio.h>

int main(int argc, char **argv) {
	uint32_t to_rev = 0x01020304;
	uint32_t rev = htonl(to_rev);
	char byteTab[8];

	if (to_rev == rev) {
		printf("big endian");
	} else {
		printf("little endian\n");
	}

	uint32_t w = 0xaabbccdd;
	char * ww = (char *) &w;

	for (int i = 0 ; i<4 ; i++) {
		printf("%x = %hx\n", ww[0]);
	}

/*	printf("%x\n", to_rev);
	printf("%x\n", rev);*/

	return 0;
}