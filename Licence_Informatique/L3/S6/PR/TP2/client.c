#include <assert.h> 
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>

int main(int argc, char **argv) {
	
	struct sockaddr_in sockaddr;

	sockaddr.sin_family = AF_INET;
	sockaddr.sin_port = htons(13);

	assert(inet_aton("192.168.70.237", sockaddr.sin_port) != 0); 

	int socket_fd = socket(AF_INET, SOCK_STREAM, 0);

	assert(socket_fd >= 0);

	int ret = connect(socket_fd, (struct sockaddr *) &sockaddr, sizeof(struct sockaddr));

	if (ret != -1) {
		
	}
	
	return 0;

}