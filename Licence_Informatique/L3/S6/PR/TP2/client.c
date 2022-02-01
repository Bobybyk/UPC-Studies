#include <sys/socket.h>
#include <netinet/in.h> 

int main(int argc, char **argv) {
	
	struct sockaddr_in sockaddr;

	sockaddr.sin_family = AF_INET;
	sockaddr.sin_port = htons(13);

	inet_aton("192.168.70.237",sockaddr.sin_port); 

	int socket_fd = socket(AF_INET, SOCK_STREAM, 0);

	int err = connect(socket_fd, (struct sockaddr *) &sockaddr, sizeof(sockaddr));
	
	return 0;

}