#include <assert.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdint.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <time.h>
#include <unistd.h>

int main(int argc, char **argv) {

	int fd = open("entete", O_WRONLY | O_CREAT | O_TRUNC, 0644);
	assert(fd != -1);

	u_int16_t entete[6];

	srand(time(NULL));

	entete[0] = htons((rand() % (65535)));
	entete[1] = htons(1 << 8); //0000000100000000

	/*
	 * Normalement lire arguments
	 */
	entete[2] = 0;
	entete[3] = 0;
	entete[4] = 0;
	entete[5] = 0;
	
	int count;
	
	for (int i = 0 ; i<6 ; i++) {
		count = write(fd, &entete[i], 2);
		assert(count != -1);
	}

	close(fd);

	return count > 0 ? 0 : -1;
}