#ifndef SATURND_H
#define SATURND_H

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <assert.h>
#include <string.h>
#include <poll.h>
#define _GNU_SOURCE      
#include <signal.h>
#include <limits.h>
#include <stdint.h>
#include <endian.h>
#include <sys/wait.h>

#include "daemon.h"
#include "client-request.h"
#include "utils/command_line.h"
#include "timing.h"
#include "utils/read_requests.h"

#endif
