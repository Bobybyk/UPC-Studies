#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

int main(int argc, char **argv) {

	if(argc != 2) {
		printf("Wrong usage: missing file name\n");
		return EXIT_FAILURE;
	}

	struct sockaddr_in address_sock;
	address_sock.sin_family = AF_INET;
	address_sock.sin_port = htons(12345);
	inet_aton("127.0.0.1",&address_sock.sin_addr);

	int sock = socket(PF_INET,SOCK_STREAM,0);
	int ret = connect(sock,(struct sockaddr *) &address_sock,sizeof(address_sock));
	assert(ret >= 0);

	int file_fd = open(argv[1],O_RDONLY);
	assert(file_fd >= 0);

	char buf[100];
	int len = lseek(file_fd,0,SEEK_END);
	lseek(file_fd,0,SEEK_SET);

	ret = recv(sock,buf,100,0);
	assert(ret >= 0);

	buf[ret -1] = '\0';
	printf("%s\n",buf);

	int msg_count = (len % 100 == 0 ? len / 100 : (len / 100) + 1);
	sprintf(buf,"%d\n",msg_count);
	ret = send(sock,buf,strlen(buf),0);

	printf("msg count: %d\n",msg_count);

	for(int i=0;i<msg_count;i++) {
		ret = read(file_fd,buf,100);
		assert(ret >= 0);
		
		ret = send(sock,buf,ret,0);
		assert(ret >= 0);
	}
	ret = recv(sock,buf,100,0);
	assert(ret >= 0);
	buf[ret-1] = '\0';
	printf("%s\n",buf);

	close(sock);

	return EXIT_SUCCESS;
}
