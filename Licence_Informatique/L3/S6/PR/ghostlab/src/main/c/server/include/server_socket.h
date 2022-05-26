#ifndef SERVER_SOCKET_H
#define SERVER_SOCKET_H

#include "dependencies.h"
#include "game.h"
#include "player.h"
#include "requests.h"
#include "send_responses.h"
#include "multicast.h"

//initializes the server's attributes
void server_socket_init(int , int);

//accepts an incoming connection and returns its socket file descriptor
int server_socket_accept(int);

//sends the list of existing games to the player, then waits for incoming commands
void *server_socket_connection_prompt(void *arg);

//this funcion acts as a lobby when the player jsut connected, waiting for the messages that can be sent by the player when it is not in a game
struct client *server_socket_receive_newpl_regis(int fd);

void *server_socket_during_game(void *arg);
void *server_socket_game_ended(void *arg);

#endif
