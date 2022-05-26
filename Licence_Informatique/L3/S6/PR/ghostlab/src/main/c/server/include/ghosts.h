#ifndef GHOSTS_H
#define GHOSTS_H

#include "dependencies.h"
#include "game.h"

#define GHOST_VALUE 50
#define GHOST_MOVE_FREQUENCY 30000000	// 30 sec as micro secondes

void *ghosts_move(void *arg);

#endif
