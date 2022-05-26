#ifndef READ_REQUESTS_H
#define READ_REQUESTS_h

#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <limits.h>
#include <unistd.h>
#include <endian.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#include "client-request.h"
#include "utils/send_responses.h"
#include "utils/command_line.h"
#include "timing.h"
#include "tasks.h"

//returns -1 if an error occured, 1 if the daemon must terminate, 0 otherwise
int read_request(int fd, Liste *liste);

#endif
