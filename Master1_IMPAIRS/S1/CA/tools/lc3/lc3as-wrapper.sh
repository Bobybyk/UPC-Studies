#!/bin/sh

BASE=`basename $1 .asm`

if [ x$BASE = x$1 ]; then
   echo "Input is not an .asm !"
   exit 1
fi

# run the real lc3-as
$0.real "$@" || exit 1

# create the derived .mem

grep -q .ORIG $1 && echo "Warning: ignoring .ORIG for $BASE.mem"

echo "v2.0 raw" > $BASE.mem
hexdump -s2 -v -e '/1 "%02x" /1 "%02x "' $BASE.obj >> $BASE.mem
echo >> $BASE.mem
