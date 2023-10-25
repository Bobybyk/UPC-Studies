mkdir -p build/utils
mkdir -p tasks/
mkdir -p /tmp/$USER/saturnd/pipes
mkfifo /tmp/$USER/saturnd/pipes/saturnd-request-pipe
mkfifo /tmp/$USER/saturnd/pipes/saturnd-reply-pipe
